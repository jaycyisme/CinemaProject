package cinema.Service;

import cinema.Entity.RankCustomer;
import cinema.Entity.Role;
import cinema.Entity.User;
import cinema.Entity.UserStatus;
import cinema.Repository.RankCustomerRepo;
import cinema.Repository.RoleRepo;
import cinema.Repository.UserRepo;
import cinema.Repository.UserStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServices implements IUserServices{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserStatusRepo userStatusRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private RankCustomerRepo rankCustomerRepo;
    @Autowired
    private JavaMailSender mailSender;

    private final Map<String, Integer> authenticationCode = new HashMap<>();

    public void authenticationEmail(String toEmail, String toSubject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("quoctuanisme0610@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(toSubject);

        mailSender.send(message);
        System.out.println("Succesful");
    }

    @Override
    public User userRegister(User user) {
        for (User existUser : userRepo.findAll()) {
            if (user.getEmail().toLowerCase().equals(existUser.getEmail()) || user.getPhoneNumber().equals(existUser.getPhoneNumber()) || user.getUsername().toLowerCase().equals(existUser.getUsername())) {
                return null;
            }
        }
        Optional<Role>roleOptional = roleRepo.findById(user.getRoleId());
        Optional<RankCustomer>rankCustomerOptional = rankCustomerRepo.findById(user.getRankCustomerId());

        if (roleOptional.isEmpty() || rankCustomerOptional.isEmpty()) {
            return null;
        }

        for (UserStatus userStatus : userStatusRepo.findAll()) {
            if (userStatus.getName().toLowerCase().equals("no active")) {
                user.setUserStatus(userStatus);
            }
        }

        Role role = roleRepo.findById(user.getRoleId()).get();
        user.setRole(role);

        RankCustomer rankCustomer = rankCustomerRepo.findById(user.getRankCustomerId()).get();
        user.setRankCustomer(rankCustomer);

        userRepo.save(user);
        int randomCode = (int) Math.floor(((Math.random() * 899999) + 100000));
        authenticationEmail(user.getEmail(), "Authentication Code", "Your authentication code is:" + String.valueOf(randomCode));

        authenticationCode.put(user.getUsername(), randomCode);

        return user;
    }



    public void authenticationAccept(String username, int code) {
        if (authenticationCode.containsKey(username)) {
            int storedCode = authenticationCode.get(username);
            if (storedCode == code) {
                for (User user: userRepo.findAll()) {
                    if (user.getUsername().toLowerCase().equals(username)) {
                        for (UserStatus userStatus: userStatusRepo.findAll()) {
                            if (userStatus.getName().toLowerCase().equals("active")) {
                                user.setUserStatus(userStatus);
                                userRepo.save(user);
                            }
                        }
                    }
                }
                authenticationCode.remove(username);
            }
        }
    }
}
