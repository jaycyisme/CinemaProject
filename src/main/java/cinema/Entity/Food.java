package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Price")
    private double price;
    @Column(name = "Description")
    private String description;
    @Column(name = "Image")
    private String image;
    @Column(name = "NameOfFood")
    private String nameOfFood;
    @Column(name = "IsActive")
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "food")
    @JsonManagedReference
    private Set<BillFood>billFoods;
}
