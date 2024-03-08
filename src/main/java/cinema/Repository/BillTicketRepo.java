package cinema.Repository;

import cinema.Entity.BillTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTicketRepo extends JpaRepository<BillTicket, Integer> {
}
