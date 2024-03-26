package cinema.Controller.Admin;

import cinema.DTO.Request.Seat.DeleteSeatRequest;
import cinema.DTO.Request.Seat.NewSeatRequest;
import cinema.DTO.Request.Seat.RemakeSeatRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Service.ServiceImpl.SeatServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class SeatController {
    private final SeatServicesImpl seatServices;

    @PostMapping("addnewseat")
    public ResponseEntity<MessageResponse> addNewSeat(@RequestBody NewSeatRequest request) {
        return new ResponseEntity<>(seatServices.addNewSeat(request), HttpStatus.OK);
    }

    @PutMapping("remakeseat")
    public ResponseEntity<MessageResponse> remakeSeat(@RequestBody RemakeSeatRequest request) {
        return new ResponseEntity<>(seatServices.remakeSeat(request), HttpStatus.OK);
    }

    @DeleteMapping("deleteseat")
    public ResponseEntity<MessageResponse> deleteSeat(@RequestBody DeleteSeatRequest request) {
        return new ResponseEntity<>(seatServices.deleteSeat(request), HttpStatus.OK);
    }
}
