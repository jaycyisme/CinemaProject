package cinema.Repository;

import cinema.Entity.Bill;
import cinema.Entity.BillTicket;
import cinema.Entity.Cinema;
import cinema.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTicketRepo extends JpaRepository<BillTicket, Integer> {
    public void deleteByTicket_Schedule_Room_Cinema(Cinema cinema);

    BillTicket findByBill (Bill bill);

    BillTicket findAllByBill (Bill bill);

    BillTicket findByTicket(Ticket ticket);
}
