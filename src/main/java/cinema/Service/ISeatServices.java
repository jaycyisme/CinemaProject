package cinema.Service;

import cinema.DTO.Request.DeleteSeatRequest;
import cinema.DTO.Request.NewSeatRequest;
import cinema.DTO.Request.RemakeSeatRequest;
import cinema.DTO.Response.MessageResponse;

public interface ISeatServices {
    public MessageResponse addNewSeat(NewSeatRequest request);
    public MessageResponse remakeSeat(RemakeSeatRequest request);
    public MessageResponse deleteSeat(DeleteSeatRequest request);
}
