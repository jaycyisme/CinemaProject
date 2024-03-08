package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "ConfirmEmail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "UserId", insertable = false, updatable = false)
    private int userId;
    @Column(name = "RequiredTime")
    private Date requiredTime;
    @Column(name = "ExpiredTime")
    private Date expiredTime;
    @Column(name = "ConfirmCode")
    private String confirmCode;
    @Column(name = "IsConfirm")
    private boolean isConfirm;

    @ManyToOne
    @JoinColumn(name = "UserId")
    @JsonBackReference
    private User user;
}
