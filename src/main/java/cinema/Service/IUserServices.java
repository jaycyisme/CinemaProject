package cinema.Service;

import cinema.DTO.Request.ChangePasswordRequest;
import cinema.DTO.Request.CreateTicketRequest;
import cinema.DTO.Request.ScheduleRequest;
import cinema.DTO.Response.GetMovieScheduleResponse;
import cinema.DTO.Response.GetSeatByScheduleResponse;
import cinema.DTO.Response.MessageResponse;

public interface IUserServices {
    public MessageResponse changePassword(ChangePasswordRequest request);

    public GetMovieScheduleResponse getMovieScheduleInfor(ScheduleRequest request);

    public GetSeatByScheduleResponse getSeatBySchedule(ScheduleRequest request);

    public MessageResponse createBill();

    public MessageResponse createBillTicket(CreateTicketRequest request);

    public MessageResponse cancelBillTicket(Integer ticketId);

    public MessageResponse createBillFood(Integer foodId);

    public MessageResponse cancelBillFood(Integer billFoodId);
}
