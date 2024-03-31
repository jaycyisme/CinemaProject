package cinema.Service.ServiceImpl;

import cinema.DTO.Request.Cinema.DeleteCinemaRequest;
import cinema.DTO.Request.Cinema.NewCinemaRequest;
import cinema.DTO.Request.Cinema.RemakeCinemaRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.*;
import cinema.Repository.*;
import cinema.Service.ICinemaServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    private final BillRepo billRepo;


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

    @Override
    public List<Object[]> getRevenueListByCinema(int cinemaId, int year) {
        Cinema cinema = cinemaRepo.findById(cinemaId).orElse(null);
        if(cinema == null){
            return null;
        }

        //TODO lấy tất cả Room theo Cinema
        List<Room> rooms = roomRepo.findAllByCinema(cinema);
        if(rooms.size() < 1){
            return null;
        }

        //TODO lấy tất cả Schedue theo list Room của Cinema
        List<Schedule> schedulesByRoom = new ArrayList<>();
        for (Room room:rooms){
            List<Schedule> schedules = scheduleRepo.findAllByRoom(room);
            schedulesByRoom.addAll(schedules);
        }
        if(schedulesByRoom.size() < 1){
            return null;
        }

        //TODO lấy  Ticket theo danh sách Schedule
        List<Ticket> ticketsBySchedule = new ArrayList<>();
        for (Schedule schedule:schedulesByRoom){
            List<Ticket> tickets = ticketRepo.findAllByScheduleAndCodeNotNullAndPriceTicketGreaterThan(schedule,0);
            ticketsBySchedule.addAll(tickets);
        }

        //TODO lấy BillTicket theo danh sách ticket
        List<BillTicket> billTicketsByTicket = new ArrayList<>();
        for (Ticket ticket:ticketsBySchedule){
            billTicketsByTicket.add(billTicketRepo.findByTicket(ticket));
        }

        List<Bill> bills = billRepo.findDistinctByBillTicketsIn(billTicketsByTicket);

        List<Integer> billIds = new ArrayList<>();
        for (Bill bill : bills) {
            billIds.add(bill.getId());
        }
        List<Object[]> objects = billRepo.getMonthlyRevenue(year,billIds);

        return objects;
    }
}
