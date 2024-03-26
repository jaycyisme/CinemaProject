package cinema.Service;

import cinema.DTO.Request.Cinema.DeleteCinemaRequest;
import cinema.DTO.Request.Cinema.NewCinemaRequest;
import cinema.DTO.Request.Cinema.RemakeCinemaRequest;
import cinema.DTO.Response.MessageResponse;

public interface ICinemaServices {
    public MessageResponse newCinema (NewCinemaRequest request);
    public MessageResponse deleteCinema(DeleteCinemaRequest request);
    public MessageResponse remakeCinema(RemakeCinemaRequest request);
}
