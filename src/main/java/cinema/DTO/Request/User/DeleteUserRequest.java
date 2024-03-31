package cinema.DTO.Request.User;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteUserRequest {
    private Integer id;
}
