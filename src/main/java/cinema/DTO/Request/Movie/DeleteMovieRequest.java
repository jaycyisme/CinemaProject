package cinema.DTO.Request.Movie;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteMovieRequest {
    private int movieId;
}
