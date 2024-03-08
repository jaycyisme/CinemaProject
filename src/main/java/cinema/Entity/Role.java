package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Code")
    private String code;
    @Column(name = "RoleName")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    @JsonManagedReference
    private Set<User> users;
}
