package cinema.DTO.Request.User;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RemakeUserInformationRequest {
    private Integer id;
    private Integer point;
    private String username;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private Integer rankCustomerId;
    private Integer userStatusId;
    private Boolean isActive;
    private Integer roleId;
    private Enum roleEnum;
}