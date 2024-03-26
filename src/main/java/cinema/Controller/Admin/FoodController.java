package cinema.Controller.Admin;

import cinema.DTO.Request.Food.DeleteFoodRequest;
import cinema.DTO.Request.Food.NewFoodRequest;
import cinema.DTO.Request.Food.RemakeFoodRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Service.ServiceImpl.FoodServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class FoodController {
    public final FoodServicesImpl foodServices;

    @PostMapping("addnewfood")
    public ResponseEntity<MessageResponse> addNewFood(@RequestBody NewFoodRequest request) {
        return new ResponseEntity<>(foodServices.newFood(request), HttpStatus.OK);
    }

    @PutMapping("remakefood")
    public ResponseEntity<MessageResponse> remakeFood(@RequestBody RemakeFoodRequest request) {
        return new ResponseEntity<>(foodServices.remakeFood(request), HttpStatus.OK);
    }

    @DeleteMapping("deletefood")
    public ResponseEntity<MessageResponse> deleteFood(@RequestBody DeleteFoodRequest request) {
        return new ResponseEntity<>(foodServices.deleteFood(request), HttpStatus.OK);
    }
}
