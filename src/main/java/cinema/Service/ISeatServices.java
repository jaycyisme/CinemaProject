package cinema.Service;

import cinema.DTO.Request.Seat.DeleteSeatRequest;
import cinema.DTO.Request.Seat.NewSeatRequest;
import cinema.DTO.Request.Seat.RemakeSeatRequest;
import cinema.DTO.Response.MessageResponse;

public interface ISeatServices {
    public MessageResponse addNewSeat(NewSeatRequest request);
    public MessageResponse remakeSeat(RemakeSeatRequest request);
    public MessageResponse deleteSeat(DeleteSeatRequest request);
}
