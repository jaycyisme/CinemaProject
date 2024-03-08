package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Seat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Number")
    private int number;
    @Column(name = "SeatStatusId", insertable = false, updatable = false)
    private int seatStatusId;
    @Column(name = "Line")
    private String line;
    @Column(name = "RoomId", insertable = false, updatable = false)
    private int roomId;
    @Column(name = "IsActive")
    private boolean isActive;
    @Column(name = "SeatTypeId", insertable = false, updatable = false)
    private int seatTypeId;

    @ManyToOne
    @JoinColumn(name = "RoomId")
    @JsonBackReference
    private Room room;

    @ManyToOne
    @JoinColumn(name = "SeatStatusId")
    @JsonBackReference
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "SeatTypeId")
    @JsonBackReference
    private SeatType seatType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seat")
    @JsonManagedReference
    private Set<Ticket> tickets;
}
