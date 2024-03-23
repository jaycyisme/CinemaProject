package cinema.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListSeatResponse {
    private Integer seatId;
    private Integer number;
    private String seatStatus;
    private String line;
    private Integer roomId;
    private Boolean isActive;
    private String seatType;
}
