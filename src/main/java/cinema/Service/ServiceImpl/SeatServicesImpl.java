package cinema.Service.ServiceImpl;

import cinema.DTO.Request.Seat.DeleteSeatRequest;
import cinema.DTO.Request.Seat.NewSeatRequest;
import cinema.DTO.Request.Seat.RemakeSeatRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.*;
import cinema.Repository.*;
import cinema.Service.ISeatServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatServicesImpl implements ISeatServices {
    private final SeatRepo seatRepo;

    private final SeatTypeRepo seatTypeRepo;

    private final SeatStatusRepo seatStatusRepo;

    private final RoomRepo roomRepo;

    private final TicketRepo ticketRepo;

    private final BillTicketRepo billTicketRepo;

    @Override
    public MessageResponse addNewSeat(NewSeatRequest request) {
        Optional<SeatStatus>seatStatusOptional = seatStatusRepo.findById(request.getSeatStatusId());
        Optional<SeatType>seatTypeOptional = seatTypeRepo.findById(request.getSeatTypeId());
        Optional<Room>roomOptional = roomRepo.findById(request.getRoomId());

        if (seatStatusOptional.isEmpty()) {
            return MessageResponse.builder().message("Seat Status not found").build();
        }
        if (seatTypeOptional.isEmpty()) {
            return MessageResponse.builder().message("Seat Type not found").build();
        }
        if (roomOptional.isEmpty()) {
            return MessageResponse.builder().message("Room Type not found").build();
        }

        Seat seat = new Seat();
        seat.setNumber(request.getNumber());

        SeatStatus seatStatus = seatStatusOptional.get();
        seat.setSeatStatus(seatStatus);
        seatStatusRepo.save(seatStatus);

        seat.setLine(request.getLine());

        Room room = roomOptional.get();
        seat.setRoom(room);
        roomRepo.save(room);

        seat.setActive(request.getIsActive());

        SeatType seatType = seatTypeOptional.get();
        seat.setSeatType(seatType);
        seatTypeRepo.save(seatType);

        seatRepo.save(seat);
        return MessageResponse.builder().message("Add New Seat Success").build();
    }

    @Override
    public MessageResponse remakeSeat(RemakeSeatRequest request) {
        Optional<Seat>seatOptional = seatRepo.findById(request.getSeatId());
        Optional<SeatStatus>seatStatusOptional = seatStatusRepo.findById(request.getSeatStatusId());
        Optional<SeatType>seatTypeOptional = seatTypeRepo.findById(request.getSeatTypeId());
        Optional<Room>roomOptional = roomRepo.findById(request.getRoomId());

        if (seatOptional.isEmpty()) {
            return MessageResponse.builder().message("Seat not found").build();
        }
        if (seatStatusOptional.isEmpty()) {
            return MessageResponse.builder().message("Seat Status not found").build();
        }
        if (seatTypeOptional.isEmpty()) {
            return MessageResponse.builder().message("Seat Type not found").build();
        }
        if (roomOptional.isEmpty()) {
            return MessageResponse.builder().message("Room not found").build();
        }

        Seat seat = seatOptional.get();
        seat.setNumber(request.getNumber());

        SeatStatus seatStatus = seatStatusOptional.get();
        seat.setSeatStatus(seatStatus);
        seatStatusRepo.save(seatStatus);

        seat.setLine(request.getLine());

        Room room = roomOptional.get();
        seat.setRoom(room);
        roomRepo.save(room);

        seat.setActive(request.getIsActive());

        SeatType seatType = seatTypeOptional.get();
        seat.setSeatType(seatType);
        seatTypeRepo.save(seatType);

        seatRepo.save(seat);
        return MessageResponse.builder().message("Remake Seat Success").build();
    }

    @Override
    public MessageResponse deleteSeat(DeleteSeatRequest request) {
        Optional<Seat>seatOptional = seatRepo.findById(request.getSeatId());
        if (seatOptional.isEmpty()) {
            return MessageResponse.builder().message("Room not found").build();
        }

        Seat seat = seatOptional.get();

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
        return MessageResponse.builder().message("Delete Room Success").build();
    }
}
