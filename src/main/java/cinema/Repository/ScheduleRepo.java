package cinema.Repository;

import cinema.DTO.Response.GetMovieScheduleResponse;
import cinema.Entity.Room;
import cinema.Entity.Schedule;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT DATE_FORMAT(s.startAt, '%d-%m-%Y') AS dayMonthYear " +
                    "FROM schedule s " +
                    "WHERE s.movieId = :movieId " +
                    "ORDER BY STR_TO_DATE(dayMonthYear, '%d-%m-%Y') ASC")
    List<String> findDistinctDayMonthYearByMovieId(@Param("movieId") int movieId, Pageable pageable);

    @Query("SELECT DISTINCT EXTRACT(HOUR FROM s.startAt), EXTRACT(MINUTE FROM s.startAt), EXTRACT(SECOND FROM s.startAt) " +
            "FROM Schedule s " +
            "WHERE s.movieId = :movieId AND DATE(s.startAt) = :date")
    List<String> findScheduleTimeByMovieAndDate(@Param("movieId") Integer movieId, @Param("date") LocalDate date, Pageable pageable);

    @Query("SELECT new cinema.DTO.Response.GetMovieScheduleResponse" +
    "(m.image, m.name, m.description, mt.movieTypeName, m.movieDuration, c.nameOfCinema, s.startAt, r.name)" +
            "FROM Schedule s JOIN Movie m ON s.movieId = m.id " +
            "JOIN Room r ON s.roomId = r.id " +
            "JOIN Cinema c ON r.cinemaId = c.id " +
            "JOIN MovieType mt ON m.movieTypeId = mt.id WHERE s.movieId = :movieId")
    GetMovieScheduleResponse getMovieScheduleBySchedule(@Param("movieId") Integer movieId);


    List<Schedule> findAllByRoom(Room room);
}

