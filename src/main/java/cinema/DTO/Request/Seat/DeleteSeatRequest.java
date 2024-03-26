package cinema.DTO.Request.Seat;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteSeatRequest {
    private Integer seatId;
}
