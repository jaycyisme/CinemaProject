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
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Point")
    private int point;
    @Column(name = "Username")
    private String username;
    @Column(name = "Email")
    private String email;
    @Column(name = "Name")
    private String name;
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Column(name = "Password")
    private String password;
    @Column(name = "RankCustomerId", insertable = false, updatable = false)
    private int rankCustomerId;
    @Column(name = "UserStatusId", insertable = false, updatable = false)
    private int userStatusId;
    @Column(name = "IsActive")
    private boolean isActive;
    @Column(name = "RoleId", insertable = false, updatable = false)
    private int roleId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private Set<Bill> bills;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private Set<ConfirmEmail> confirmEmails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private Set<RefreshToken> refreshTokens;

    @ManyToOne
    @JoinColumn(name = "RankCustomerId")
    @JsonBackReference
    private RankCustomer rankCustomer;

    @ManyToOne
    @JoinColumn(name = "UserStatusId")
    @JsonBackReference
    private UserStatus userStatus;

    @ManyToOne
    @JoinColumn(name = "RoleId")
    @JsonBackReference
    private Role role;

}
