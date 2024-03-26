package cinema.Controller.User;

import cinema.DTO.Request.ChangePasswordRequest;
import cinema.DTO.Request.ScheduleRequest;
import cinema.DTO.Response.GetMovieScheduleResponse;
import cinema.DTO.Response.GetSeatByScheduleResponse;
import cinema.DTO.Response.MessageResponse;
import cinema.Service.ServiceImpl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserServicesImpl userServices;
    @PostMapping("/changepassword")
    public ResponseEntity<MessageResponse> createNewPassword(
            @RequestBody ChangePasswordRequest request
    ) {
        return ResponseEntity.ok(userServices.changePassword(request));
    }

    @GetMapping("/getmoviebyschedule")
    public ResponseEntity<GetMovieScheduleResponse> getMovieScheduleResponse (@RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(userServices.getMovieScheduleInfor(request));
    }

    @GetMapping("/getseatbyschedule")
    public ResponseEntity<GetSeatByScheduleResponse> getSeatByScheduleResponse (@RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(userServices.getSeatBySchedule(request));
    }

    @PostMapping("/createbill")
    public ResponseEntity<MessageResponse> createBill() {
        return ResponseEntity.ok(userServices.createBill());
    }
}
