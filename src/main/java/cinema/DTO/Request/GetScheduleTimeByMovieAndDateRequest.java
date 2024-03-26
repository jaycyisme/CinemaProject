package cinema.DTO.Request;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetScheduleTimeByMovieAndDateRequest {
    private Integer movieId;
    private LocalDate date;
}
