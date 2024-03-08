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
@Table(name = "Room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Capacity")
    private int capacity;
    @Column(name = "Type")
    private int type;
    @Column(name = "Description")
    private String description;
    @Column(name = "CinemaId", insertable = false, updatable = false)
    private int cinemaId;
    @Column(name = "Code")
    private String code;
    @Column(name = "Name")
    private String name;
    @Column(name = "IsActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "CinemaId")
    @JsonBackReference
    private Cinema cinema;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    @JsonManagedReference
    private Set<Schedule> schedules;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    @JsonManagedReference
    private Set<Seat> seats;
}
