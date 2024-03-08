package cinema.Repository;

import cinema.Entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatStatusRepo extends JpaRepository<SeatStatus, Integer> {
}
