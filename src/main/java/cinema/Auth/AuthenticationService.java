package cinema.Auth;

import cinema.Config.JwtService;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.*;
import cinema.Enums.RoleEnums;
import cinema.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;

    private final UserStatusRepo userStatusRepo;

    private final RoleRepo roleRepo;

    private final RankCustomerRepo rankCustomerRepo;

    private final RefreshTokenRepo refreshTokenRepo;

    private final JavaMailSender mailSender;

    private final Map<String, Integer> authenticationCode = new HashMap<>();

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public void authenticationEmail(String toEmail, String toSubject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("quoctuanisme0610@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(toSubject);

        mailSender.send(message);
        System.out.println("Succesful");
    }

    public MessageResponse register(RegisterRequest request) {
        Optional<User> existUsername = userRepo.findByUsername(request.getUsername());
        Optional<User> existEmail = userRepo.findByEmail(request.getEmail());
        if (!existUsername.isEmpty() || !existEmail.isEmpty()) {
            return MessageResponse.builder().message("Username or email is exist").build();
        }

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .roleEnums(RoleEnums.USER)
                .build();

        int randomCode = (int) Math.floor(((Math.random() * 899999) + 100000));
        authenticationEmail(request.getEmail(), "Authentication Code", "Your authentication code is:" + String.valueOf(randomCode));

        authenticationCode.put(request.getEmail(), randomCode);

        user.setConfirmCode(String.valueOf(randomCode));

        user.setPoint(0);

        for (Role role : roleRepo.findAll()) {
            if (role.getName().toLowerCase().equals("user")) {
                user.setRole(role);
            }
        }

        for (RankCustomer rankCustomer : rankCustomerRepo.findAll()) {
            if (rankCustomer.getPoint() == 0) {
                user.setRankCustomer(rankCustomer);
            }
        }

//        RankCustomer rankCustomer = rankCustomerRepo.findById(user.getRankCustomerId()).get();
//        for (User userCheck : userRepo.findAll()) {
//            if (userCheck.getPoint() == 0) {
//                // Tìm RankCustomer có điểm là 0
//                RankCustomer rankCustomer = rankCustomerRepo.findByPoint(0);
//                if (rankCustomer != null) {
//                    user.setRankCustomer(rankCustomer);
//                }
//            }
//
//            if (userCheck.getPoint() > 0 && userCheck.getPoint() < 100) {
//                // Tìm RankCustomer có điểm nằm trong khoảng từ 1 đến 99
//                RankCustomer rankCustomer = rankCustomerRepo.findByPointBetween(1, 99);
//                if (rankCustomer != null) {
//                    user.setRankCustomer(rankCustomer);
//                }
//            }
//
//            if (userCheck.getPoint() > 100 && userCheck.getPoint() < 500) {
//                RankCustomer rankCustomer = rankCustomerRepo.findByPointBetween(100, 499);
//                if (rankCustomer != null) {
//                    user.setRankCustomer(rankCustomer);
//                }
//            }
//
//            if (userCheck.getPoint() > 500 && userCheck.getPoint() < 1000) {
//                RankCustomer rankCustomer = rankCustomerRepo.findByPointBetween(500, 999);
//                if (rankCustomer != null) {
//                    user.setRankCustomer(rankCustomer);
//                }
//            }
//
//            if (userCheck.getPoint() > 1000 && userCheck.getPoint() < 2000) {
//                RankCustomer rankCustomer = rankCustomerRepo.findByPointBetween(1000, 1999);
//                if (rankCustomer != null) {
//                    user.setRankCustomer(rankCustomer);
//                }
//            }
//
//            if (userCheck.getPoint() > 2000 && userCheck.getPoint() < 5000) {
//                RankCustomer rankCustomer = rankCustomerRepo.findByPointBetween(2000, 4999);
//                if (rankCustomer != null) {
//                    user.setRankCustomer(rankCustomer);
//                }
//            }
//
//            if (userCheck.getPoint() >= 5000) {
//                RankCustomer rankCustomer = rankCustomerRepo.findByPointGreaterThanEqual(5000);
//                if (rankCustomer != null) {
//                    user.setRankCustomer(rankCustomer);
//                }
//            }
//        }

        user.setActive(false);


//        UserStatus userStatus = userStatusRepo.findById(user.getUserStatusId()).get();
        for (UserStatus userStatus : userStatusRepo.findAll()) {
            if (userStatus.getName().toLowerCase().equals("no active")) {
                user.setUserStatus(userStatus);
            }
        }

        user.setRoleEnums(RoleEnums.USER);

        userRepo.save(user);
        return MessageResponse.builder().message("Successful Registration").build();
    }

    public MessageResponse verifyUser(VerifyRequest request) {
        Optional<User> existEmail = userRepo.findByEmail(request.getEmail());
        if (existEmail.isEmpty()) {
            return MessageResponse.builder().message("User not found").build();
        }
        User user = userRepo.findByEmail(request.getEmail()).get();
        if (!request.getVerifyCode().equals(user.getConfirmCode())) {
            return MessageResponse.builder().message("Invalid verification code").build();
        }
        for (UserStatus userStatus: userStatusRepo.findAll()) {
            if (userStatus.getName().toLowerCase().equals("active")) {
                user.setUserStatus(userStatus);
                user.setActive(true);
                userRepo.save(user);
            }
        }
        authenticationCode.remove(request.getEmail());
        return MessageResponse.builder().message("Successful Authentication").build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.isActive()) {
            throw new RuntimeException("User is not yet verified");
        }
        if (!user.getUserStatus().getName().toLowerCase().equals("active")) {
            throw new RuntimeException("User status is not active");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(String.valueOf(AuthenticationResponse.builder().token(jwtToken).build()));
        refreshToken.setUser(user);
        refreshToken.setExpiredTime(new Date(System.currentTimeMillis() + 1000 * 60 * 24));
        refreshTokenRepo.save(refreshToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public MessageResponse forgotPassword(ForgotPasswordRequest request) {
        Optional<User> user = userRepo.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            return MessageResponse.builder().message("User not found").build();
        }
        int randomCode = (int) Math.floor(((Math.random() * 899999) + 100000));
        authenticationEmail(request.getEmail(), "Authentication Code", "Your authentication code is:" + String.valueOf(randomCode));

        authenticationCode.put(request.getEmail(), randomCode);

        user.get().setConfirmCode(String.valueOf(randomCode));
        userRepo.save(user.get());
        return MessageResponse.builder().message("The verification code has already sent to the email").build();
    }

    public MessageResponse createNewPassword(NewPassWordRequest request) {
        Optional<User> userOptional = userRepo.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            return MessageResponse.builder().message("User not found").build();
        }
        User user = userOptional.get();
        if (!request.getConfirmCode().equals(user.getConfirmCode())) {
            throw new RuntimeException("User is not yet verified");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);
        return MessageResponse.builder().message("Create successful new password").build();
    }
}
