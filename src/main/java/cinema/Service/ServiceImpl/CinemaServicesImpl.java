package cinema.Service.ServiceImpl;

import cinema.DTO.Request.DeleteCinemaRequest;
import cinema.DTO.Request.NewCinemaRequest;
import cinema.DTO.Request.RemakeCinemaRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.*;
import cinema.Repository.*;
import cinema.Service.ICinemaServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CinemaServicesImpl implements ICinemaServices {

    private final CinemaRepo cinemaRepo;

    private final RoomRepo roomRepo;

    private final ScheduleRepo scheduleRepo;

    private final SeatRepo seatRepo;

    private final TicketRepo ticketRepo;

    private final BillTicketRepo billTicketRepo;


    @Override
    public MessageResponse newCinema(NewCinemaRequest request) {
        Cinema cinema = new Cinema();
        cinema.setAddress(request.getAddress());
        cinema.setDescription(request.getDescription());
        cinema.setCode(request.getCode());
        cinema.setNameOfCinema(request.getNameOfCinema());
        cinema.setActive(request.getIsActive());

        cinemaRepo.save(cinema);
        return MessageResponse.builder().message("Add New Cinema Success").build();
    }

    @Override
    public MessageResponse deleteCinema(DeleteCinemaRequest request) {
        Optional<Cinema>cinemaOptional = cinemaRepo.findById(request.getCinemaId());
        if (cinemaOptional.isEmpty()) {
            return MessageResponse.builder().message("Cinema not found").build();
        }
        Cinema cinema = cinemaOptional.get();
        for (Room room : roomRepo.findAll()) {
            if (room.getCinema().equals(cinema)) {
                for (Seat seat : seatRepo.findAll()) {
                    if (seat.getRoom().equals(room)) {
                        for (Ticket ticket : ticketRepo.findAll()) {
                            if (ticket.getSeat().equals(seat)) {
                                for (BillTicket billTicket : billTicketRepo.findAll()) {
                                    if (billTicket.getTicket().equals(ticket)) {
                                        billTicketRepo.delete(billTicket);
                                    }
                                }
                                ticketRepo.delete(ticket);
                            }
                        }
                        seatRepo.delete(seat);
                    }
                }

                for (Schedule schedule : scheduleRepo.findAll()) {
                    if (schedule.getRoom().equals(room)) {
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
                roomRepo.delete(room);
            }
        }
//        billTicketRepo.deleteByTicket_Schedule_Room_Cinema(cinema);
//        ticketRepo.deleteBySeat_Room_Cinema(cinema);
//        ticketRepo.deleteBySchedule_Room_Cinema(cinema);
//        seatRepo.deleteByRoom_Cinema(cinema);
//        scheduleRepo.deleteByRoom_Cinema(cinema);

        cinemaRepo.delete(cinema);
        return MessageResponse.builder().message("Delete Cinema Success").build();
    }

    @Override
    public MessageResponse remakeCinema(RemakeCinemaRequest request) {
        Optional<Cinema>cinemaOptional = cinemaRepo.findById(request.getCinemaId());
        if (cinemaOptional.isEmpty()) {
            return MessageResponse.builder().message("Cinema not found").build();
        }
        Cinema cinema = cinemaOptional.get();
        cinema.setAddress(request.getAddress());
        cinema.setDescription(request.getDescription());
        cinema.setCode(request.getCode());
        cinema.setNameOfCinema(request.getNameOfCinema());
        cinema.setActive(request.getIsActive());

        cinemaRepo.save(cinema);
        return MessageResponse.builder().message("Remake New Cinema Success").build();
    }
}
