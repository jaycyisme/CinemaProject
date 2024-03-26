package cinema.DTO.Request.Cinema;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RemakeCinemaRequest {
    private Integer cinemaId;
    private String address;
    private String description;
    private String code;
    private String nameOfCinema;
    private Boolean isActive;
}
