package cinema.Service.ServiceImpl;

import cinema.DTO.Request.ChangePasswordRequest;
import cinema.DTO.Request.ScheduleRequest;
import cinema.DTO.Response.GetMovieScheduleResponse;
import cinema.DTO.Response.GetSeatByScheduleResponse;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.Bill;
import cinema.Entity.BillStatus;
import cinema.Entity.User;
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
        bill.setPromotion(null);
        bill.setBillStatus(billStatusRepo.findById(1).orElse(null));
        bill.setActive(true);
        billRepo.save(bill);
        return MessageResponse.builder().message("Create Bill Success").build();
    }

    //TODO tạo ticket với scheduleID và seatID
    //TODO cập nhật PriceTicket trong ticket
    //TODO xóa seat thì cập nhật lại giá
    //TODO cập nhật quantity trong Bill sau mỗi lần chọn seat
}
