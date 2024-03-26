package cinema.Service;

import cinema.DTO.Request.Room.DeleteRoomRequest;
import cinema.DTO.Request.Room.NewRoomRequest;
import cinema.DTO.Request.Room.RemakeRoomRequest;
import cinema.DTO.Response.MessageResponse;

public interface IRoomServices {
    public MessageResponse newRoom(NewRoomRequest request);
    public MessageResponse remakeRoom(RemakeRoomRequest request);
    public MessageResponse deleteRoom(DeleteRoomRequest request);
}
