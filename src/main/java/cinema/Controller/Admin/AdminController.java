package cinema.Controller.Admin;

import cinema.DTO.Request.Cinema.DeleteCinemaRequest;
import cinema.DTO.Request.Cinema.NewCinemaRequest;
import cinema.DTO.Request.Cinema.RemakeCinemaRequest;
import cinema.DTO.Request.User.CreateUserRequest;
import cinema.DTO.Request.User.DeleteUserRequest;
import cinema.DTO.Request.User.GetUserRequest;
import cinema.DTO.Request.User.RemakeUserInformationRequest;
import cinema.DTO.Response.GetAllUserResponse;
import cinema.DTO.Response.GetUserResponse;
import cinema.DTO.Response.MessageResponse;
import cinema.Service.ServiceImpl.AdminServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminServicesImpl adminServices;

    @GetMapping("getalluser")
    public ResponseEntity<List<GetAllUserResponse>> listResponseEntity(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize
    ) {
        return new ResponseEntity<>(adminServices.getAllUser(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("getuserbyid")
    public ResponseEntity<GetUserResponse> getUserResponse(@RequestBody GetUserRequest id) {
        return new ResponseEntity<>(adminServices.getUserInformation(id), HttpStatus.OK);
    }

    @PostMapping("addnewuser")
    public ResponseEntity<MessageResponse> addNewUser (@RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(adminServices.addUser(request), HttpStatus.OK);
    }

    @PutMapping("remakeuser")
    public ResponseEntity<MessageResponse> remakeUser (@RequestBody RemakeUserInformationRequest request) {
        return new ResponseEntity<>(adminServices.remakeUser(request), HttpStatus.OK);
    }

    @DeleteMapping("deleteuser")
    public ResponseEntity<MessageResponse> deleteUser (@RequestBody DeleteUserRequest request){
        return new ResponseEntity<>(adminServices.deleteUser(request), HttpStatus.OK);
    }
}
