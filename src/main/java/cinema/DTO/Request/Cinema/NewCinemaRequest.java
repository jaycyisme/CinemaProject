package cinema.DTO.Request.Cinema;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NewCinemaRequest {
    private String address;
    private String description;
    private String code;
    private String nameOfCinema;
    private Boolean isActive;
}
