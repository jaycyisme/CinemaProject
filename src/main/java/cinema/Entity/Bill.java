package cinema.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "TotalMoney")
    private double totalMoney;
    @Column(name = "TradingCode")
    private String tradingCode;
    @Column(name = "CreateTime")
    private Date createTime;
    @Column(name = "CustomerId", insertable = false, updatable = false)
    private int customerId;
    @Column(name = "Name")
    private String name;
    @Column(name = "UpdateTime")
    private Date updateTime;
    @Column(name = "PromotionId", insertable = false, updatable = false)
    private int promotionId;
    @Column(name = "BillStatusId", insertable = false, updatable = false)
    private int billStatusId;
    @Column(name = "IsActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "BillStatusId")
    @JsonBackReference
    private BillStatus billStatus;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "PromotionId")
    @JsonBackReference
    private Promotion promotion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
    @JsonManagedReference
    private Set<BillFood>billFoods;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
    @JsonManagedReference
    private Set<BillTicket>billTickets;
}
