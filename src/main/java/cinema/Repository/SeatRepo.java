package cinema.Repository;

import cinema.DTO.Response.GetSeatByScheduleResponse;
import cinema.DTO.Response.ListSeatResponse;
import cinema.Entity.Cinema;
import cinema.Entity.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer> {
    @Query("SELECT new cinema.DTO.Response.ListSeatResponse" +
            "(s.id, s.number, ss.nameStatus, s.line, r.id, s.isActive, st.nameType)" +
            "FROM Seat s JOIN Room r ON s.roomId = r.id " +
            "JOIN SeatStatus ss ON s.seatStatusId = ss.id " +
            "JOIN SeatType st ON s.seatTypeId = st.id " +
            "JOIN Cinema c ON r.cinemaId = c.id WHERE c.id = :cinemaId AND r.id = :roomId")
    Page<ListSeatResponse>findAllSeatByCinemaAndRoom (@Param("cinemaId") Integer cinemaId, @Param("roomId") Integer roomId, Pageable pageable);


    @Query("SELECT new cinema.DTO.Response.GetSeatByScheduleResponse" +
    "(s.number, st.nameType, stt.nameStatus)" +
            "FROM Seat s JOIN SeatType st ON s.seatTypeId = st.id " +
            "JOIN SeatStatus stt ON s.seatStatusId = stt.id " +
            "JOIN Room r ON s.roomId = r.id " +
            "JOIN Schedule sc ON sc.roomId = r.id WHERE sc.id = :scheduleId")
    GetSeatByScheduleResponse getSeatBySchedule (@Param("scheduleId") Integer scheduleId);
}
