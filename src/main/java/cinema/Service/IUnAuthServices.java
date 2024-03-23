package cinema.Service;

import cinema.DTO.Request.GetMovieByCinemaAndRoomRequest;
import cinema.DTO.Request.GetMovieByCinemaRequest;
import cinema.DTO.Request.GetSeatRequest;
import cinema.DTO.Response.ListMovieReponse;
import cinema.DTO.Response.ListSeatResponse;

import java.util.List;

public interface IUnAuthServices {
    public List<ListMovieReponse> listMovie(Integer pageNumber, Integer pageSize);
    public List<ListMovieReponse> listMovieByCinema(GetMovieByCinemaRequest request, Integer pageNumber, Integer pageSize);
    public List<ListMovieReponse> listMovieByCinemaAndRoom(GetMovieByCinemaAndRoomRequest request, Integer pageNumber, Integer pageSize);
    public List<ListSeatResponse> listSeatByCinemaAndRoom(GetSeatRequest request, Integer pageNumber, Integer pageSize);
}
