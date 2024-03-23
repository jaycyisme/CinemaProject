package cinema.Service;

import cinema.DTO.Request.DeleteCinemaRequest;
import cinema.DTO.Request.NewCinemaRequest;
import cinema.DTO.Request.RemakeCinemaRequest;
import cinema.DTO.Response.MessageResponse;

public interface ICinemaServices {
    public MessageResponse newCinema (NewCinemaRequest request);
    public MessageResponse deleteCinema(DeleteCinemaRequest request);
    public MessageResponse remakeCinema(RemakeCinemaRequest request);
}
