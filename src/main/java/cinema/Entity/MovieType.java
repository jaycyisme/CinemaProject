package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "MovieType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "MovieTypeName")
    private String movieTypeName;
    @Column(name = "IsActive")
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movieType")
    @JsonManagedReference
    private Set<Movie> movies;
}
