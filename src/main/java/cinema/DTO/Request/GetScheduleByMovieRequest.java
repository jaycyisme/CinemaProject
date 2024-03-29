package cinema.DTO.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetScheduleByMovieRequest {
    private Integer movieId;
}
