package cinema.Controller.User;

import cinema.Auth.NewPassWordRequest;
import cinema.DTO.Request.ChangePasswordRequest;
import cinema.DTO.Response.ListMovieReponse;
import cinema.DTO.Response.MessageResponse;
import cinema.Service.ServiceImpl.UserServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
