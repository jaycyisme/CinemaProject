package cinema.Service.ServiceImpl;

import cinema.DTO.Request.ChangePasswordRequest;
import cinema.DTO.Request.CreateTicketRequest;
import cinema.DTO.Request.ScheduleRequest;
import cinema.DTO.Response.GetMovieScheduleResponse;
import cinema.DTO.Response.GetSeatByScheduleResponse;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.*;
import cinema.Repository.*;
import cinema.Service.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements IUserServices {

    private final UserRepo userRepo;

    private final MovieRepo movieRepo;

    private final ScheduleRepo scheduleRepo;

    private final CinemaRepo cinemaRepo;

    private final RoomRepo roomRepo;

    private final SeatRepo seatRepo;

    private final BillRepo billRepo;

    private final BillStatusRepo billStatusRepo;

    private final BillTicketRepo billTicketRepo;

    private final TicketRepo ticketRepo;

    private final FoodRepo foodRepo;

    private final BillFoodRepo billFoodRepo;


    private final PasswordEncoder passwordEncoder;




    private String generateCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }


    @Override
    public MessageResponse changePassword(ChangePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = "";
        if (authentication != null && authentication.isAuthenticated()) {
            loggedInUsername = authentication.getName();
        }
        User user = userRepo.findByUsername(loggedInUsername).get();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(user);
        return MessageResponse.builder().message("Successful Change Password").build();
    }



    @Override
    public GetMovieScheduleResponse getMovieScheduleInfor(ScheduleRequest request) {
        return scheduleRepo.getMovieScheduleBySchedule(request.getScheduleId());
    }

    @Override
    public GetSeatByScheduleResponse getSeatBySchedule(ScheduleRequest request) {
        return seatRepo.getSeatBySchedule(request.getScheduleId());
    }

    @Override
    public MessageResponse createBill() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = "";
        if (authentication != null && authentication.isAuthenticated()) {
            loggedInUsername = authentication.getName();
        }
        User user = userRepo.findByUsername(loggedInUsername).orElseThrow(() -> new RuntimeException("User not found"));

        Set<Promotion> promotions = user.getRankCustomer().getPromotions();
        Promotion promotion = promotions.isEmpty() ? null : promotions.iterator().next();
//        Bill existBill = billRepo.findByUser(user);
//        if (existBill.getBillStatus().equals(1)) {
//            return MessageResponse.builder().message("Your bill already exist").build();
//        }

        Bill existBill = billRepo.findByUserAndBillStatusId(user, 1);
        if (existBill != null) {
            return MessageResponse.builder().message("Your bill already exists").build();
        }


        Bill bill = new Bill();
        bill.setTotalMoney(0);
        bill.setTradingCode(generateCode());
        // Lấy thời gian hiện tại
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Chuyển đổi từ LocalDateTime sang Date
        Date currentDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        bill.setCreateTime(currentDate);
        bill.setUser(user);
        bill.setName("Bill of " + user.getName());
        bill.setUpdateTime(currentDate);
        bill.setPromotion(promotion);
        bill.setBillStatus(billStatusRepo.findById(1).orElse(null));
        bill.setActive(true);
        billRepo.save(bill);
        return MessageResponse.builder().message("Create Bill Success").build();
    }


    @Override
    public MessageResponse createBillTicket(CreateTicketRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = "";
        if (authentication != null && authentication.isAuthenticated()) {
            loggedInUsername = authentication.getName();
        }
        User user = userRepo.findByUsername(loggedInUsername).orElseThrow(() -> new RuntimeException("User not found"));

        Schedule schedule = scheduleRepo.findById(request.getScheduleId()).orElseThrow(() -> new RuntimeException("Schedule not found"));
        Seat seat = seatRepo.findById(request.getSeatId()).orElseThrow(() -> new RuntimeException("Seat not found"));
        Bill bill = billRepo.findByUserAndBillStatusId(user, 1);

        if (bill == null) {
            return MessageResponse.builder().message("Bill not found").build();
        }

        if (bill.getBillStatusId() == 2) {
            return MessageResponse.builder().message("Please Create New Bill").build();
        }

        if (seat.isActive() == false) {
            return MessageResponse.builder().message("Seat is not available").build();
        }


        Ticket ticket = new Ticket();
        ticket.setCode(generateCode());
        ticket.setSchedule(schedule);


        seat.setActive(false);
        ticket.setSeat(seat);
        seatRepo.save(seat);

        if(seat.getSeatType().getId() == 1){
            ticket.setPriceTicket(schedule.getPrice());
        } else if (seat.getSeatType().getId() == 2) {
            ticket.setPriceTicket(schedule.getPrice() * 2);
        }
        ticket.setActive(true);
        ticketRepo.save(ticket);

        BillTicket billTicket = new BillTicket();
        billTicket.setQuantity(0);
        billTicket.setBill(bill);
        billTicket.setTicket(ticket);
        billTicketRepo.save(billTicket);

        double billTicketPrice = ticket.getPriceTicket();
        bill.setTotalMoney(bill.getTotalMoney() + billTicketPrice);
        billRepo.save(bill);

        return MessageResponse.builder().message(String.valueOf(bill.getTotalMoney())).build();
    }


    @Override
    public MessageResponse cancelBillTicket(Integer ticketId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = "";
        if (authentication != null && authentication.isAuthenticated()) {
            loggedInUsername = authentication.getName();
        }
        User user = userRepo.findByUsername(loggedInUsername).orElseThrow(() -> new RuntimeException("User not found"));

        Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));

        for (BillTicket billTicket : billTicketRepo.findAll()) {
            if (billTicket.getTicket().equals(ticket)) {
                billTicketRepo.delete(billTicket);
            }
        }
        ticketRepo.delete(ticket);

        Bill bill = billRepo.findByUser(user);
        bill.setTotalMoney(bill.getTotalMoney() - ticket.getPriceTicket());
        billRepo.save(bill);

        Seat seat = seatRepo.findById(ticket.getSeatId()).get();
        seat.setActive(true);
        seatRepo.save(seat);

        return MessageResponse.builder().message("Cancel Succesfull").build();
    }


    @Override
    public MessageResponse createBillFood(Integer foodId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = "";
        if (authentication != null && authentication.isAuthenticated()) {
            loggedInUsername = authentication.getName();
        }
        User user = userRepo.findByUsername(loggedInUsername).orElseThrow(() -> new RuntimeException("User not found"));

        Food food = foodRepo.findById(foodId).orElseThrow(() -> new RuntimeException("Food not found"));
        Bill bill = billRepo.findByUserAndBillStatusId(user, 1);

        BillFood billFood = new BillFood();
        billFood.setQuantity(0);
        billFood.setBill(bill);
        billFood.setFood(food);
        billFoodRepo.save(billFood);

        double billFoodPrice = food.getPrice();
        bill.setTotalMoney(bill.getTotalMoney() + billFoodPrice);
        billRepo.save(bill);
        return MessageResponse.builder().message(String.valueOf(bill.getTotalMoney())).build();
    }

    @Override
    public MessageResponse cancelBillFood(Integer billFoodId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = "";
        if (authentication != null && authentication.isAuthenticated()) {
            loggedInUsername = authentication.getName();
        }
        User user = userRepo.findByUsername(loggedInUsername).orElseThrow(() -> new RuntimeException("User not found"));

        BillFood billFood = billFoodRepo.findById(billFoodId).orElseThrow(() -> new RuntimeException("Bill Food not found"));
        billFoodRepo.delete(billFood);

        Bill bill = billRepo.findByUser(user);
        bill.setTotalMoney(bill.getTotalMoney() - billFood.getFood().getPrice());
        billRepo.save(bill);

        return MessageResponse.builder().message("Cancel Succesfull").build();
    }
}

