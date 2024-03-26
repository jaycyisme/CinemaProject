package cinema.DTO.Request.Movie;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NewMovieRequest {
    private Integer movieDuration;
    private Date endTime;
    private Date premiereDate;
    private String description;
    private String director;
    private String image;
    private String heroImage;
    private String language;
    private Integer movieTypeId;
    private String name;
    private Integer rateId;
    private String trailer;
    private Boolean isActive;
    private Integer ticketSoldedQuantity;
}
