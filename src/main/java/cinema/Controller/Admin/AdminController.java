package cinema.Controller.Admin;

import cinema.DTO.Request.GetUserRequest;
import cinema.DTO.Response.GetAllUserResponse;
import cinema.DTO.Response.GetUserResponse;
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
}
