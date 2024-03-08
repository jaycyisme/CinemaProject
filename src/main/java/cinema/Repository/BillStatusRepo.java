package cinema.Repository;

import cinema.Entity.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillStatusRepo extends JpaRepository<BillStatus, Integer> {
}
