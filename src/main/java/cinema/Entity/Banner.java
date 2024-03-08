package cinema.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Banner")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "ImageUrl")
    private String imageUrl;
    @Column(name = "Title")
    private String title;
}
