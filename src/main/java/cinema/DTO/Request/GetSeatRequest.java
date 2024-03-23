package cinema.DTO.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetSeatRequest {
    private Integer cinemaId;
    private Integer roomId;
}
