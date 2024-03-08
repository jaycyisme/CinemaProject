package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "SeatType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "NameType")
    private String nameType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seatType")
    @JsonManagedReference
    private Set<Seat> seats;
}
