package cinema.Service.ServiceImpl;

import cinema.Config.ApplicationConfig;
import cinema.DTO.Request.Movie.DeleteMovieRequest;
import cinema.DTO.Request.Movie.NewMovieRequest;
import cinema.DTO.Request.Movie.RemakeMovieRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.*;
import cinema.Repository.*;
import cinema.Service.IMovieServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServicesImpl implements IMovieServices {

    private final MovieRepo movieRepo;

    private final RateRepo rateRepo;

    private final MovieTypeRepo movieTypeRepo;

    private final ScheduleRepo scheduleRepo;

    private final TicketRepo ticketRepo;

    private final BillTicketRepo billTicketRepo;

    private final ApplicationConfig config;


    @Override
    public MessageResponse newMovie(NewMovieRequest request) {
        Optional<Rate>rateOptional = rateRepo.findById(request.getRateId());
        Optional<MovieType>movieTypeOptional = movieTypeRepo.findById(request.getMovieTypeId());
        if (rateOptional.isEmpty()) {
            return MessageResponse.builder().message("Rate not found").build();
        }
        if (movieTypeOptional.isEmpty()) {
            return MessageResponse.builder().message("Movie type not found").build();
        }

        Movie movie = new Movie();
        movie.setMovieDuration(request.getMovieDuration());
        movie.setEndTime(request.getEndTime());
        movie.setPremiereDate(request.getPremiereDate());
        movie.setDescription(request.getDescription());
        movie.setDirector(request.getDirector());
        movie.setImage(request.getImage());
        movie.setHeroImage(request.getHeroImage());
        movie.setLanguage(request.getLanguage());

        MovieType movieType = movieTypeOptional.get();
//        movieType.setId(movie.getMovieTypeId());
        movie.setMovieType(movieType);
        movieTypeRepo.save(movieType);

        movie.setName(request.getName());

        Rate rate = rateOptional.get();
//        rate.setId(request.getRateId());
        movie.setRate(rate);
        rateRepo.save(rate);

        movie.setTrailer(request.getTrailer());
        movie.setActive(request.getIsActive());
        movie.setTicketSoldQuantity(request.getTicketSoldedQuantity());

        movieRepo.save(movie);
        return MessageResponse.builder().message("Add new movie success").build();
    }

    @Override
    public MessageResponse deleteMovie(DeleteMovieRequest request) {
        Optional<Movie>movieOptional = movieRepo.findById(request.getMovieId());
        if (movieOptional.isEmpty()) {
            return MessageResponse.builder().message("Movie not found").build();
        }

        Movie movie = movieOptional.get();
        for (Schedule schedule : scheduleRepo.findAll()) {
            if (schedule.getMovie().equals(movie)) {
                for (Ticket ticket : ticketRepo.findAll()) {
                    if (ticket.getSchedule().equals(schedule)) {
                        for (BillTicket billTicket : billTicketRepo.findAll()) {
                            if (billTicket.getTicket().equals(ticket)) {
                                billTicketRepo.delete(billTicket);
                            }
                        }
                        ticketRepo.delete(ticket);
                    }
                }
                scheduleRepo.delete(schedule);
            }
        }
        movieRepo.delete(movie);
        return MessageResponse.builder().message("Delete Movie Sucess").build();
    }

    @Override
    public MessageResponse remakeMovie(RemakeMovieRequest request) {
        Optional<Movie>movieOptional = movieRepo.findById(request.getMovieId());
        Optional<Rate>rateOptional = rateRepo.findById(request.getRateId());
        Optional<MovieType>movieTypeOptional = movieTypeRepo.findById(request.getMovieTypeId());
        if (movieOptional.isEmpty()) {
            return MessageResponse.builder().message("Movie not found").build();
        }
        if (rateOptional.isEmpty()) {
            return MessageResponse.builder().message("Rate not found").build();
        }
        if (movieTypeOptional.isEmpty()) {
            return MessageResponse.builder().message("Movie type not found").build();
        }

        Movie movie = movieOptional.get();


        movie.setMovieDuration(request.getMovieDuration());
        movie.setEndTime(request.getEndTime());
        movie.setPremiereDate(request.getPremiereDate());
        movie.setDescription(request.getDescription());
        movie.setDirector(request.getDirector());
        movie.setImage(request.getImage());
        movie.setHeroImage(request.getHeroImage());
        movie.setLanguage(request.getLanguage());

        MovieType movieType = movieTypeOptional.get();
//        movieType.setId(movie.getMovieTypeId());
        movie.setMovieType(movieType);
        movieTypeRepo.save(movieType);

        movie.setName(request.getName());

        Rate rate = rateOptional.get();
//        rate.setId(movie.getRateId());
        movie.setRate(rate);
        rateRepo.save(rate);

        movie.setTrailer(request.getTrailer());
        movie.setActive(request.getIsActive());
        movie.setTicketSoldQuantity(request.getTicketSoldedQuantity());

        BeanUtils.copyProperties(request, movie, config.getNullPropertyNames(request));

        movieRepo.save(movie);

        return MessageResponse.builder().message("Remake Movie Success").build();
    }
}
