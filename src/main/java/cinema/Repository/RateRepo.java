package cinema.Repository;

import cinema.Entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepo extends JpaRepository<Rate, Integer> {
}
