package cinema.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "GeneralSetting")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "BreakTime")
    private Date breakTime;
    @Column(name = "BusinessHours")
    private int businessHours;
    @Column(name = "CloseTime")
    private Date closeTime;
    @Column(name = "FixedTicketPrice")
    private double fixedTicketPrice;
    @Column(name = "PercentDay")
    private int percentDay;
    @Column(name = "PercentWeekend")
    private int percentWeekend;
    @Column(name = "TimeBeginToChange")
    private Date timeBeginToChange;
}
