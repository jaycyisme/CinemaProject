package cinema.Repository;

import cinema.Entity.Bill;
import cinema.Entity.BillTicket;
import cinema.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer> {

    Bill findByUser(User user);

    Bill findByUserAndBillStatusId(User user, Integer billStatusId);

    List<Bill> findDistinctByBillTicketsIn(List<BillTicket> billTickets);

    @Query("SELECT MONTH(b.updateTime), SUM(b.totalMoney) FROM Bill b WHERE YEAR(b.updateTime) = :year AND b.id IN :billIds GROUP BY MONTH(b.updateTime)")
    List<Object[]> getMonthlyRevenue(@Param("year") int year, @Param("billIds") List<Integer> billIds);

}
