package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "SeatStatus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Code")
    private String code;
    @Column(name = "NameStatus")
    private String nameStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seatStatus")
    @JsonManagedReference
    private Set<Seat> seats;

}
