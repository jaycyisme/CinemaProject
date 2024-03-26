package cinema.Controller.UnAuth;

import cinema.DTO.Request.*;
import cinema.DTO.Response.ListMovieReponse;
import cinema.DTO.Response.ListSeatResponse;
import cinema.Service.ServiceImpl.UnAuthServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/unauth")
@RequiredArgsConstructor
public class UnAuthController {
    private final UnAuthServicesImpl unAuthServices;

    @GetMapping("/displayhighratemovie")
    public ResponseEntity<List<ListMovieReponse>> listMovie(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize
    ){
        return new ResponseEntity<>(unAuthServices.listMovie(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/displaymoviebycinema")
    public ResponseEntity<List<ListMovieReponse>> listMovieByCinema(
            @RequestBody GetMovieByCinemaRequest request,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize
            ) {
        return new ResponseEntity<>(unAuthServices.listMovieByCinema(request, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/displaymoviebycinemaandroom")
    public ResponseEntity<List<ListMovieReponse>> listMovieByCinemaAndRoom(
            @RequestBody GetMovieByCinemaAndRoomRequest request,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize) {
        return new ResponseEntity<>(unAuthServices.listMovieByCinemaAndRoom(request, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/displayseat")
    public ResponseEntity<List<ListSeatResponse>> listSeat(
            @RequestBody GetSeatRequest request,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize
    ){
        return new ResponseEntity<>(unAuthServices.listSeatByCinemaAndRoom(request, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/displayschedulebymovie")
    public ResponseEntity<List<String>> listSchedule (
            @RequestBody GetScheduleByMovieRequest request,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize
    ) {
        return new ResponseEntity<>(unAuthServices.listScheduleByMovie(request, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/displayscheduletimebymovieanddate")
    public ResponseEntity<List<String>> listSchedule (
            @RequestBody GetScheduleTimeByMovieAndDateRequest request,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize
    ) {
        return new ResponseEntity<>(unAuthServices.listScheduleTimeByMovieAndDate(request, pageNumber, pageSize), HttpStatus.OK);
    }

}
