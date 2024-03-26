package cinema.Service.ServiceImpl;

import cinema.DTO.Request.GetUserRequest;
import cinema.DTO.Response.GetAllUserResponse;
import cinema.DTO.Response.GetUserResponse;
import cinema.Entity.User;
import cinema.Repository.UserRepo;
import cinema.Service.IAdminServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServicesImpl implements IAdminServices {
    private final UserRepo userRepo;

    @Override
    public List<GetAllUserResponse> getAllUser(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepo.findAll(pageable);

        // Map User entities to GetAllUserResponse DTOs
        List<GetAllUserResponse> responses = userPage.getContent().stream()
                .map(user -> new GetAllUserResponse(user))
                .collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetUserResponse getUserInformation(GetUserRequest request) {
        Optional<User> existUser = userRepo.findById(request.getId());
        if (existUser.isEmpty()) {
            return null;
        }
        User user = existUser.get();
        return new GetUserResponse(user);
    }
}
