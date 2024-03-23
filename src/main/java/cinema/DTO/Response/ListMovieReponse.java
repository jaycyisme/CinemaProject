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
public class ListMovieReponse {
    private Integer movieId;
    private Integer movieDuration;
    private Date endTime;
    private Date premiereDate;
    private String description;
    private String director;
    private String image;
    private String heroImage;
    private String language;
    private String movieTypeName;
    private String name;
    private Integer rateId;
    private String trailer;
    private boolean isActive;
    private int ticketSoldQuantity;
}
