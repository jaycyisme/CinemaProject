package cinema.Repository;

import cinema.Entity.BillTicket;
import cinema.Entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTicketRepo extends JpaRepository<BillTicket, Integer> {
    public void deleteByTicket_Schedule_Room_Cinema(Cinema cinema);
}
