package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "BillStatus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "billStatus")
    @JsonManagedReference
    private Set<Bill>bills;

}
