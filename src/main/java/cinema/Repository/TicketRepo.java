package cinema.Repository;

import cinema.Entity.Cinema;
import cinema.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    public void deleteBySeat_Room_Cinema(Cinema cinema);
    public void deleteBySchedule_Room_Cinema(Cinema cinema);
}
