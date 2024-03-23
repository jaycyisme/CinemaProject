package cinema.DTO.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RemakeSeatRequest {
    private Integer seatId;
    private Integer number;
    private Integer seatStatusId;
    private String line;
    private Integer roomId;
    private Boolean isActive;
    private Integer seatTypeId;
}
