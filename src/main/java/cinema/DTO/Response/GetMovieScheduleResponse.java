package cinema.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetMovieScheduleResponse {
    private String movieImage;
    private String movieName;
    private String movieDescription;
    private String movieTypeName;
    private Integer movieDuration;
    private String cinemaName;
    private Date scheduleStartAtDate;
    private String roomName;

}
