package cinema.Repository;

import cinema.Entity.BillFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillFoodRepo extends JpaRepository<BillFood, Integer> {
}
