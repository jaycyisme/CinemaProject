package cinema.Controller.Admin;

import cinema.DTO.Request.Room.DeleteRoomRequest;
import cinema.DTO.Request.Room.NewRoomRequest;
import cinema.DTO.Request.Room.RemakeRoomRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Service.ServiceImpl.RoomServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/admin")
@RequiredArgsConstructor
public class RoomController {

    private final RoomServicesImpl roomServices;

    @PostMapping("addnewroom")
    public ResponseEntity<MessageResponse> addNewRoom(@RequestBody NewRoomRequest request) {
        return new ResponseEntity<>(roomServices.newRoom(request), HttpStatus.OK);
    }

    @PutMapping("remakeroom")
    public ResponseEntity<MessageResponse> remakeRoom(@RequestBody RemakeRoomRequest request) {
        return new ResponseEntity<>(roomServices.remakeRoom(request), HttpStatus.OK);
    }

    @DeleteMapping("deleteroom")
    public ResponseEntity<MessageResponse> deleteRoom(@RequestBody DeleteRoomRequest request) {
        return new ResponseEntity<>(roomServices.deleteRoom(request), HttpStatus.OK);
    }
}
