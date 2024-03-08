package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Cinema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Address")
    private String address;
    @Column(name = "Description")
    private String description;
    @Column(name = "Code")
    private String code;
    @Column(name = "NameOfCinema")
    private String nameOfCinema;
    @Column(name = "IsActive")
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cinema")
    @JsonManagedReference
    private Set<Room> rooms;
}
