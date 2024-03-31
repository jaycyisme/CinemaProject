package cinema.DTO.Request.User;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateUserRequest {
    private String username;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
//    private Integer rankCustomerId;
//    private Integer userStatusId;
//    private Boolean isActive;
//    private Integer roleId;
}
