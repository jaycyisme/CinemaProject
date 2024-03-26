package cinema.Service;

import cinema.DTO.Request.Movie.DeleteMovieRequest;
import cinema.DTO.Request.Movie.NewMovieRequest;
import cinema.DTO.Request.Movie.RemakeMovieRequest;
import cinema.DTO.Response.MessageResponse;

public interface IMovieServices {
    public MessageResponse newMovie(NewMovieRequest request);
    public MessageResponse deleteMovie(DeleteMovieRequest request);
    public MessageResponse remakeMovie(RemakeMovieRequest request);

}
