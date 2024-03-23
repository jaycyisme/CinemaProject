package cinema.Service.ServiceImpl;

import cinema.DTO.Request.ChangePasswordRequest;
import cinema.DTO.Response.ListMovieReponse;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.Movie;
import cinema.Entity.User;
import cinema.Repository.MovieRepo;
import cinema.Repository.UserRepo;
import cinema.Service.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements IUserServices {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;


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


}
