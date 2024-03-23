package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "MovieDuration")
    private int movieDuration;
    @Column(name = "EndTime")
    private Date endTime;
    @Column(name = "PremiereDate")
    private Date premiereDate;
    @Column(name = "Description")
    private String description;
    @Column(name = "Director")
    private String director;
    @Column(name = "Image")
    private String image;
    @Column(name = "HeroImage")
    private String heroImage;
    @Column(name = "Language")
    private String language;
    @Column(name = "MovieTypeId", insertable = false, updatable = false)
    private int movieTypeId;
    @Column(name = "Name")
    private String name;
    @Column(name = "RateId", insertable = false, updatable = false)
    private int rateId;
    @Column(name = "Trailer")
    private String trailer;
    @Column(name = "IsActive")
    private boolean isActive;
    @Column(name = "TicketSoldQuantity")
    private int ticketSoldQuantity;

    @ManyToOne
    @JoinColumn(name = "RateId")
    @JsonBackReference
    private Rate rate;

    @ManyToOne
    @JoinColumn(name = "movieTypeId")
    @JsonBackReference
    private MovieType movieType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    @JsonManagedReference
    private Set<Schedule> schedules;
}
