package cinema.Service;

import cinema.DTO.Request.DeleteMovieRequest;
import cinema.DTO.Request.NewMovieRequest;
import cinema.DTO.Request.RemakeMovieRequest;
import cinema.DTO.Response.MessageResponse;

public interface IMovieServices {
    public MessageResponse newMovie(NewMovieRequest request);
    public MessageResponse deleteMovie(DeleteMovieRequest request);
    public MessageResponse remakeMovie(RemakeMovieRequest request);

}
