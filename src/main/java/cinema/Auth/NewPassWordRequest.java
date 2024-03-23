package cinema.Auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NewPassWordRequest {
    private String email;
    private String confirmCode;
    private String password;
}
