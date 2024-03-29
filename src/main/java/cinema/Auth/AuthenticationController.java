package cinema.Auth;

import cinema.DTO.Response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(
            @RequestBody RegisterRequest request
    ) {
       return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/verifyuser")
    public ResponseEntity<MessageResponse> verify(
            @RequestBody VerifyRequest request
    ) {
        return ResponseEntity.ok(service.verifyUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<MessageResponse> forgotPassword(
            @RequestBody ForgotPasswordRequest request
    ) {
        return ResponseEntity.ok(service.forgotPassword(request));
    }

    @PostMapping("/createnewpassword")
    public ResponseEntity<MessageResponse> createNewPassword(
            @RequestBody NewPassWordRequest request
    ) {
        return ResponseEntity.ok(service.createNewPassword(request));
    }
}
