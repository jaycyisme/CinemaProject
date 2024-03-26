package cinema.Controller.Admin;

import cinema.DTO.Request.Movie.DeleteMovieRequest;
import cinema.DTO.Request.Movie.NewMovieRequest;
import cinema.DTO.Request.Movie.RemakeMovieRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Service.ServiceImpl.MovieServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/admin")
@RequiredArgsConstructor
public class MovieController {
    private final MovieServicesImpl movieServices;

    @PostMapping("addnewmovie")
    public ResponseEntity<MessageResponse> addNewMovie(@RequestBody NewMovieRequest request){
        return new ResponseEntity<>(movieServices.newMovie(request), HttpStatus.OK);
    }

    @DeleteMapping("deletemovie")
    public ResponseEntity<MessageResponse> deleteMovie(@RequestBody DeleteMovieRequest request) {
        return new ResponseEntity<>(movieServices.deleteMovie(request), HttpStatus.OK);
    }

    @PutMapping("remakemovie")
    public ResponseEntity<MessageResponse> remakeMovie(@RequestBody RemakeMovieRequest request){
        return new ResponseEntity<>(movieServices.remakeMovie(request), HttpStatus.OK);
    }
}
