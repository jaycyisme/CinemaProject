package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Rate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Description")
    private String description;
    @Column(name = "Code")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rate")
    @JsonManagedReference
    private Set<Movie> movies;
}
