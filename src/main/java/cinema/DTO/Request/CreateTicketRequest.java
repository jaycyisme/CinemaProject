package cinema.DTO.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateTicketRequest {
    private Integer seatId;
    private Integer scheduleId;
}
