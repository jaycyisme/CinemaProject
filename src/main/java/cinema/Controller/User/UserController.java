package cinema.Controller.User;

import cinema.DTO.Request.ChangePasswordRequest;
import cinema.DTO.Request.CreateTicketRequest;
import cinema.DTO.Request.ScheduleRequest;
import cinema.DTO.Response.GetMovieScheduleResponse;
import cinema.DTO.Response.GetSeatByScheduleResponse;
import cinema.DTO.Response.MessageResponse;
import cinema.Service.ServiceImpl.UserServicesImpl;
import cinema.Service.ServiceImpl.VNPayServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserServicesImpl userServices;

    private final VNPayServices vnPayServices;


    @PostMapping("/changepassword")
    public ResponseEntity<MessageResponse> createNewPassword(
            @RequestBody ChangePasswordRequest request
    ) {
        return ResponseEntity.ok(userServices.changePassword(request));
    }

    @GetMapping("/getmoviebyschedule")
    public ResponseEntity<GetMovieScheduleResponse> getMovieScheduleResponse (@RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(userServices.getMovieScheduleInfor(request));
    }

    @GetMapping("/getseatbyschedule")
    public ResponseEntity<GetSeatByScheduleResponse> getSeatByScheduleResponse (@RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(userServices.getSeatBySchedule(request));
    }

    @PostMapping("/createbill")
    public ResponseEntity<MessageResponse> createBill() {
        return ResponseEntity.ok(userServices.createBill());
    }

    @PostMapping("/createbillticket")
    public ResponseEntity<MessageResponse> createBillTicket(@RequestBody CreateTicketRequest request) {
        return ResponseEntity.ok(userServices.createBillTicket(request));
    }

    @PutMapping("/cancelbillticket")
    public ResponseEntity<MessageResponse> cancelBillTicket(@RequestBody Integer ticketId) {
        return ResponseEntity.ok(userServices.cancelBillTicket(ticketId));
    }

    @PostMapping("/createbillfood")
    public ResponseEntity<MessageResponse> createBillFood(@RequestBody Integer foodId) {
        return ResponseEntity.ok(userServices.createBillFood(foodId));
    }

    @PutMapping("/cancelbillfood")
    public ResponseEntity<MessageResponse> cancelBillFood(@RequestBody Integer billFoodId) {
        return ResponseEntity.ok(userServices.cancelBillFood(billFoodId));
    }

    @GetMapping("/submitorder")
    public String submitOrder() throws UnsupportedEncodingException {
        return vnPayServices.getPay();
    }

    @GetMapping("/vnpay-payment")
    public String confirmPayment(HttpServletRequest request, Model model){
        int paymentStatus =vnPayServices.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");


        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }
}
