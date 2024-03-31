package cinema.Service.ServiceImpl;

import cinema.DTO.Request.User.DeleteUserRequest;
import cinema.DTO.Request.User.GetUserRequest;
import cinema.DTO.Request.User.CreateUserRequest;
import cinema.DTO.Request.User.RemakeUserInformationRequest;
import cinema.DTO.Response.GetAllUserResponse;
import cinema.DTO.Response.GetUserResponse;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.*;
import cinema.Enums.RoleEnums;
import cinema.Repository.*;
import cinema.Service.IAdminServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServicesImpl implements IAdminServices {
    private final UserRepo userRepo;

    private final UserStatusRepo userStatusRepo;

    private final CinemaRepo cinemaRepo;

    private final RoomRepo roomRepo;

    private final SeatRepo seatRepo;

    private final ScheduleRepo scheduleRepo;

    private final TicketRepo ticketRepo;

    private final BillTicketRepo billTicketRepo;

    private final BillFoodRepo billFoodRepo;

    private final BillRepo billRepo;

    private final RoleRepo roleRepo;

    private final RankCustomerRepo rankCustomerRepo;

    private final RefreshTokenRepo refreshTokenRepo;

    private final PasswordEncoder passwordEncoder;

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

    @Override
    public MessageResponse addUser(CreateUserRequest request) {
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

        user.setConfirmCode(String.valueOf(randomCode));

        user.setPoint(0);

        for (Role role : roleRepo.findAll()) {
            if (role.getName().toLowerCase().equals("role_user")) {
                user.setRole(role);
                roleRepo.save(role);
            }
        }

        for (RankCustomer rankCustomer : rankCustomerRepo.findAll()) {
            if (rankCustomer.getPoint() == 0) {
                user.setRankCustomer(rankCustomer);
                rankCustomerRepo.save(rankCustomer);
            }
        }

        user.setActive(false);


//        UserStatus userStatus = userStatusRepo.findById(user.getUserStatusId()).get();
        for (UserStatus userStatus : userStatusRepo.findAll()) {
            if (userStatus.getName().toLowerCase().equals("inactive")) {
                user.setUserStatus(userStatus);
                userStatusRepo.save(userStatus);
            }
        }

        user.setRoleEnums(RoleEnums.USER);

        userRepo.save(user);
        return MessageResponse.builder().message("Create New User Success").build();
    }

    @Override
    public MessageResponse remakeUser(RemakeUserInformationRequest request) {
        User user = userRepo.findById(request.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        RankCustomer rankCustomer = rankCustomerRepo.findById(request.getRankCustomerId()).orElseThrow(() -> new RuntimeException("RankCustomer not found"));
        UserStatus userStatus = userStatusRepo.findById(request.getUserStatusId()).orElseThrow(() -> new RuntimeException("UserStatus not found"));
        Role role = roleRepo.findById(request.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));

        user.setPoint(request.getPoint());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRankCustomer(rankCustomer);
        rankCustomerRepo.save(rankCustomer);

        user.setUserStatus(userStatus);
        userStatusRepo.save(userStatus);

        user.setActive(request.getIsActive());

        user.setRole(role);
        roleRepo.save(role);

        user.setRoleEnums((RoleEnums) request.getRoleEnum());

        userRepo.save(user);

        return MessageResponse.builder().message("Remake User Information Success").build();
    }

    @Override
    public MessageResponse deleteUser(DeleteUserRequest request) {
        User user = userRepo.findById(request.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        for (Bill bill : billRepo.findAll()) {
            if (bill.getUser().equals(user)) {
                for (BillFood billFood : billFoodRepo.findAll()) {
                    if (billFood.getBill().equals(bill)) {
                        billFoodRepo.delete(billFood);
                    }
                }

                for (BillTicket billTicket : billTicketRepo.findAll()) {
                    if (billTicket.getBill().equals(bill)) {
                        billTicketRepo.delete(billTicket);
                    }
                }
                billRepo.delete(bill);
            }
        }

        for (RefreshToken refreshToken : refreshTokenRepo.findAll()) {
            if (refreshToken.getUser().equals(user)) {
                refreshTokenRepo.delete(refreshToken);
            }
        }

        userRepo.delete(user);
        return MessageResponse.builder().message("Delete User Success").build();
    }
}
