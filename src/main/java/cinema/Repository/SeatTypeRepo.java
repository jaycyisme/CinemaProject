package cinema.Repository;

import cinema.Entity.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeRepo extends JpaRepository<SeatType, Integer> {
}
