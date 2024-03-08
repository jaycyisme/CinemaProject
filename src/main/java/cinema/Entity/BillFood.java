package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BillFood")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "BillId", insertable = false, updatable = false)
    private int billId;
    @Column(name = "FoodId", insertable = false, updatable = false)
    private int foodId;
    @ManyToOne
    @JoinColumn(name = "FoodId")
    @JsonBackReference
    private Food food;

    @ManyToOne
    @JoinColumn(name = "BillId")
    @JsonBackReference
    private Bill bill;
}
