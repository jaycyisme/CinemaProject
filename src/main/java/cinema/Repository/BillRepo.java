package cinema.Repository;

import cinema.Entity.Bill;
import cinema.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer> {

    Bill findByUser(User user);

    Bill findByUserAndBillStatusId(User user, Integer billStatusId);


}
