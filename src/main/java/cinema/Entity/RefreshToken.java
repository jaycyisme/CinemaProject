package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "RefreshToken")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Token")
    private String token;
    @Column(name = "ExpiredTime")
    private Date expiredTime;
    @Column(name = "UserId", insertable = false, updatable = false)
    private int userId;

    @ManyToOne
    @JoinColumn(name = "UserId")
    @JsonBackReference
    private User user;
}
