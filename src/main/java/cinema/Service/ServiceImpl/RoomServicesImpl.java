package cinema.Service.ServiceImpl;

import cinema.DTO.Request.Room.DeleteRoomRequest;
import cinema.DTO.Request.Room.NewRoomRequest;
import cinema.DTO.Request.Room.RemakeRoomRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.*;
import cinema.Repository.*;
import cinema.Service.IRoomServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServicesImpl implements IRoomServices {
    private final CinemaRepo cinemaRepo;

    private final RoomRepo roomRepo;

    private final ScheduleRepo scheduleRepo;

    private final SeatRepo seatRepo;

    private final TicketRepo ticketRepo;

    private final BillTicketRepo billTicketRepo;

    @Override
    public MessageResponse newRoom(NewRoomRequest request) {
        Optional<Cinema>cinemaOptional = cinemaRepo.findById(request.getCinemaId());
        if (cinemaOptional.isEmpty()) {
            return MessageResponse.builder().message("Cinema not found").build();
        }
        Room room = new Room();
        room.setCapacity(request.getCapacity());
        room.setType(request.getType());
        room.setDescription(request.getDescription());

        Cinema cinema = cinemaOptional.get();
        room.setCinema(cinema);
        cinemaRepo.save(cinema);

        room.setCode(request.getCode());
        room.setName(request.getName());
        room.setActive(request.getIsActive());

        roomRepo.save(room);
        return MessageResponse.builder().message("Add New Room Success").build();
    }

    @Override
    public MessageResponse remakeRoom(RemakeRoomRequest request) {
        Optional<Room>roomOptional = roomRepo.findById(request.getRoomId());
        Optional<Cinema>cinemaOptional = cinemaRepo.findById(request.getCinemaId());
        if (roomOptional.isEmpty()) {
            return MessageResponse.builder().message("Room not found").build();
        }
        if (cinemaOptional.isEmpty()) {
            return MessageResponse.builder().message("Cinema not found").build();
        }

        Room room = roomOptional.get();

        room.setCapacity(request.getCapacity());
        room.setType(request.getType());
        room.setDescription(request.getDescription());

        Cinema cinema = cinemaRepo.findById(request.getCinemaId()).get();
        room.setCinema(cinema);
        cinemaRepo.save(cinema);

        room.setCode(request.getCode());
        room.setName(request.getName());
        room.setActive(request.getIsActive());

        roomRepo.save(room);
        return MessageResponse.builder().message("Remake Room Success").build();
    }

    @Override
    public MessageResponse deleteRoom(DeleteRoomRequest request) {
        Optional<Room>roomOptional = roomRepo.findById(request.getRoomId());
        if (roomOptional.isEmpty()) {
            return MessageResponse.builder().message("Room not found").build();
        }

        Room room = roomOptional.get();
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

        roomRepo.delete(room);
        return MessageResponse.builder().message("Delete Room Success").build();
    }
}
