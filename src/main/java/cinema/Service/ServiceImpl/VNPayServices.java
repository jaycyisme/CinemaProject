package cinema.Service.ServiceImpl;

import cinema.Config.VNPayConfig;
import cinema.Entity.*;
import cinema.Repository.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
@RequiredArgsConstructor
public class VNPayServices {

    private final BillRepo billRepo;

    private final BillTicketRepo billTicketRepo;

    private final TicketRepo ticketRepo;

    private final ScheduleRepo scheduleRepo;

    private final SeatRepo seatRepo;

    private final MovieRepo movieRepo;

    private final RoomRepo roomRepo;

    private final CinemaRepo cinemaRepo;

    private final UserRepo userRepo;

    private final BillStatusRepo billStatusRepo;

    private final RankCustomerRepo rankCustomerRepo;

    private final PromotionRepo promotionRepo;

    private final JavaMailSender mailSender;


    public void authenticationEmail(String toEmail, String toSubject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("quoctuanisme0610@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(toSubject);

        mailSender.send(message);
        System.out.println("Succesful");
    }
    private String generateCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }




    public String getPay() throws UnsupportedEncodingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = "";
        if (authentication != null && authentication.isAuthenticated()) {
            loggedInUsername = authentication.getName();
        }
        User user = userRepo.findByUsername(loggedInUsername).orElseThrow(() -> new RuntimeException("User not found"));

        RankCustomer rankCustomer = rankCustomerRepo.findById(user.getRankCustomerId()).orElseThrow(() -> new RuntimeException("RankCustomer not found"));
        Promotion promotion = promotionRepo.findAllByRankCustomer(rankCustomer);

        int discount = promotion.getPercent();

        Bill bill = billRepo.findByUserAndBillStatusId(user, 1);
        long amount = (long) ((bill.getTotalMoney()-(bill.getTotalMoney()*discount/100))*100);

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
//        long amount = 10000*100;
        String bankCode = "NCB";

        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;

        bill.setBillStatus(billStatusRepo.findById(2).orElse(null));
        billRepo.save(bill);

        String movieName = "";
        String seatName = "";
        String schedule = "";
        String room = "";
        String cinema = "";

        for (BillTicket billTicket : billTicketRepo.findAll()) {
            if (billTicket.getBill().equals(bill)) {
                for (Ticket ticket : ticketRepo.findAll()) {
                    if (billTicket.getTicket().equals(ticket)) {
                        for (Schedule existschedule : scheduleRepo.findAll()) {
                            if (ticket.getSchedule().equals(existschedule)) {
                                for (Movie movie : movieRepo.findAll()) {
                                    if (existschedule.getMovie().equals(movie)) {
                                        movieName = movie.getName();
                                    }
                                }
                                schedule = existschedule.getStartAt().toString();
                                for (Room existRoom : roomRepo.findAll()) {
                                    if (existschedule.getRoom().equals(room)) {
                                        room = existRoom.getName();
                                        for (Cinema existCinema : cinemaRepo.findAll()) {
                                            if (existRoom.getCinema().equals(cinema)) {
                                                cinema = existCinema.getNameOfCinema();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        for (Seat seat : seatRepo.findAll()) {
                            if (ticket.getSeat().equals(seat)) {
                                seatName = seat.getNumber() + " - " + seat.getLine();
                            }
                        }
                    }
                }
            }
        }

        String emailContent = "Dear Customer,\n\n" +
                "Thank you for your payment. Your transaction was successful. Here are the details:\n\n" +
                "Trading Code: " + bill.getTradingCode() + "\n" +
                "Movie: " + movieName + "\n" +
                "Seat Name: " + seatName + "\n" +
                "Schedule: " + schedule + "\n" +
                "Room: " + room + "\n" +
                "Cinema: " + cinema + "\n\n" +
                "Enjoy your movie!\n\n" +
                "Best regards,\nYour Cinema Team";


        authenticationEmail(user.getEmail(), "Payment Successful", emailContent);


//        for (BillTicket existBillTicket : bill.getBillTickets()) {
//            for (Ticket existTicket : ticketRepo.findAll()) {
//                if (existBillTicket.getTicket().equals(existTicket)) {
//                    for (Seat existSeat : seatRepo.findAll()) {
//                        if (existTicket.getSeat().equals(existSeat)) {
//                            existSeat.setSeatStatusId(1);
//                        }
//                    }
//                }
//            }
//        }

//        Set<BillTicket> billTickets = bill.getBillTickets(); // Lấy tất cả các BillTicket của hóa đơn
//        for (BillTicket existBillTicket : billTickets) {
//            Ticket existTicket = existBillTicket.getTicket(); // Lấy vé từ BillTicket
//            Seat existSeat = existTicket.getSeat(); // Lấy ghế từ vé
//            existSeat.setSeatStatusId(1); // Cập nhật trạng thái ghế
//            seatRepo.save(existSeat); // Lưu thay đổi vào cơ sở dữ liệu
//        }

        return paymentUrl;
    }

    public int orderReturn(HttpServletRequest request){
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = VNPayConfig.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {

                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }
}
