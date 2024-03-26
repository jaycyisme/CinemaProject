package cinema.Service.ServiceImpl;

import cinema.DTO.Request.*;
import cinema.DTO.Response.ListMovieReponse;
import cinema.DTO.Response.ListSeatResponse;
import cinema.Repository.*;
import cinema.Service.IUnAuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UnAuthServicesImpl implements IUnAuthServices {

    private final MovieRepo movieRepo;

    private final CinemaRepo cinemaRepo;

    private final RoomRepo roomRepo;

    private final ScheduleRepo scheduleRepo;

    private final SeatRepo seatRepo;


    @Override
    public List<ListMovieReponse> listMovie(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ListMovieReponse> moviePage = movieRepo.findAllMovie(pageable);
        return moviePage.getContent();
    }

    @Override
    public List<ListMovieReponse> listMovieByCinema(GetMovieByCinemaRequest request, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ListMovieReponse> moviePage = movieRepo.findAllMovieByCinema(request.getCinemaId(), pageable);
        Set<Integer>movieIds = new HashSet<>();
        List<ListMovieReponse> uniqueMovies = new ArrayList<>();
        for (ListMovieReponse movieReponse : moviePage.getContent()) {
            if (!movieIds.contains(movieReponse.getMovieId())) {
                movieIds.add(movieReponse.getMovieId());
                uniqueMovies.add(movieReponse);
            }
        }

        return uniqueMovies;
    }

    @Override
    public List<ListMovieReponse> listMovieByCinemaAndRoom(GetMovieByCinemaAndRoomRequest request, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ListMovieReponse> moviePage = movieRepo.findAllMovieByCinemaAndRoom(request.getCinemaId(), request.getRoomId(), pageable);
        Set<Integer>movieIds = new HashSet<>();
        List<ListMovieReponse>uniqueMovies = new ArrayList<>();

        for (ListMovieReponse movieReponse : moviePage.getContent()) {
            if (!movieIds.contains(movieReponse.getMovieId())) {
                movieIds.add(movieReponse.getMovieId());
                uniqueMovies.add(movieReponse);
            }
        }

        return uniqueMovies;
    }

    @Override
    public List<ListSeatResponse> listSeatByCinemaAndRoom(GetSeatRequest request, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ListSeatResponse> seatPage = seatRepo.findAllSeatByCinemaAndRoom(request.getCinemaId(), request.getRoomId(), pageable);
        return seatPage.getContent();
    }

    @Override
    public List<String> listScheduleByMovie(GetScheduleByMovieRequest request, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<String> schedulePage = scheduleRepo.findDistinctDayMonthYearByMovieId(request.getMovieId(), pageable);
        return schedulePage;
    }

    @Override
    public List<String> listScheduleTimeByMovieAndDate(GetScheduleTimeByMovieAndDateRequest request, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<String> scheduleTimePage = scheduleRepo.findScheduleTimeByMovieAndDate(request.getMovieId(), request.getDate(), pageable);
        return scheduleTimePage;
    }
}
