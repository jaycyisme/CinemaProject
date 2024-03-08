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
@Table(name = "Ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Code")
    private String code;
    @Column(name = "ScheduleId", insertable = false, updatable = false)
    private int scheduleId;
    @Column(name = "SeatId", insertable = false, updatable = false)
    private int seatId;
    @Column(name = "PriceTicket")
    private double priceTicket;
    @Column(name = "IsActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "ScheduleId")
    @JsonBackReference
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "SeatId")
    @JsonBackReference
    private Seat seat;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket")
    @JsonManagedReference
    private Set<BillTicket> billTickets;
}
