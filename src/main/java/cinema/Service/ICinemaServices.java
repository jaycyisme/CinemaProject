package cinema.Service;

import cinema.DTO.Request.Cinema.DeleteCinemaRequest;
import cinema.DTO.Request.Cinema.NewCinemaRequest;
import cinema.DTO.Request.Cinema.RemakeCinemaRequest;
import cinema.DTO.Response.MessageResponse;

import java.util.List;

public interface ICinemaServices {
    public MessageResponse newCinema (NewCinemaRequest request);
    public MessageResponse deleteCinema(DeleteCinemaRequest request);
    public MessageResponse remakeCinema(RemakeCinemaRequest request);
    List<Object[]> getRevenueListByCinema (int cinemaId, int year);
}
