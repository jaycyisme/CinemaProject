package cinema.DTO.Request.Room;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteRoomRequest {
    private Integer roomId;
}
