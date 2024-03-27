package cinema.Controller.Admin;

import cinema.DTO.Request.Cinema.DeleteCinemaRequest;
import cinema.DTO.Request.Cinema.NewCinemaRequest;
import cinema.DTO.Request.Cinema.RemakeCinemaRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Service.ServiceImpl.CinemaServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/admin")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaServicesImpl cinemaServices;

    @PostMapping("addnewcinema")
    public ResponseEntity<MessageResponse> addNewCinema (@RequestBody NewCinemaRequest request) {
        return new ResponseEntity<>(cinemaServices.newCinema(request), HttpStatus.OK);
    }

    @DeleteMapping("deletecinema")
    public ResponseEntity<MessageResponse> deleteCinema (@RequestBody DeleteCinemaRequest request){
        return new ResponseEntity<>(cinemaServices.deleteCinema(request), HttpStatus.OK);
    }

    @PutMapping("remakecinema")
    public ResponseEntity<MessageResponse> remakeCinema (@RequestBody RemakeCinemaRequest request) {
        return new ResponseEntity<>(cinemaServices.remakeCinema(request), HttpStatus.OK);
    }

    @GetMapping("/getrevenue")
    public ResponseEntity<?> getRevenueListByCinema(@RequestParam int cinemaId, int year){
        List<Object[]> objects = cinemaServices.getRevenueListByCinema(cinemaId,year);
        return ResponseEntity.ok().body(objects);
    }
}
