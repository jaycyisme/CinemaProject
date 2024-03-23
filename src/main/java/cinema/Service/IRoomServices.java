package cinema.Service;

import cinema.DTO.Request.DeleteRoomRequest;
import cinema.DTO.Request.NewRoomRequest;
import cinema.DTO.Request.RemakeRoomRequest;
import cinema.DTO.Response.MessageResponse;

public interface IRoomServices {
    public MessageResponse newRoom(NewRoomRequest request);
    public MessageResponse remakeRoom(RemakeRoomRequest request);
    public MessageResponse deleteRoom(DeleteRoomRequest request);
}
