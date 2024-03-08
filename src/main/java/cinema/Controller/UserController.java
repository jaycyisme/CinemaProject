package cinema.Controller;

import cinema.Entity.User;
import cinema.Service.UserServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping(value = "api/user")
public class UserController {
    @Autowired
    private UserServices userServices;


    @RequestMapping(value = "addnewuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addNewUser(@RequestBody String user) {
        Gson gson = new Gson();
        User newUser = gson.fromJson(user, User.class);
        return userServices.userRegister(newUser);
    }

    @RequestMapping(value = "acceptauthentication", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void acceptAuthentication(@RequestParam String username, int code) {
        userServices.authenticationAccept(username, code);
    }
}
