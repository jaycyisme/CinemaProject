package cinema.DTO.Response;

import cinema.Entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponse {
    private User user;
}
