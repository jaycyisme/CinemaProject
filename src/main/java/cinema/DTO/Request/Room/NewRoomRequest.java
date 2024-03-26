package cinema.DTO.Request.Room;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NewRoomRequest {
    private Integer capacity;
    private Integer type;
    private String description;
    private Integer cinemaId;
    private String code;
    private String name;
    private Boolean isActive;
}
