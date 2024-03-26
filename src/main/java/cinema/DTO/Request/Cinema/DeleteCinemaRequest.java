package cinema.DTO.Request.Cinema;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteCinemaRequest {
    private Integer cinemaId;
}
