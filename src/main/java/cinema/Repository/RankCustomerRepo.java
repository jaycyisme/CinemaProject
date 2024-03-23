package cinema.Repository;

import cinema.Entity.RankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankCustomerRepo extends JpaRepository<RankCustomer, Integer> {
    RankCustomer findByPoint(int point);
    RankCustomer findByPointBetween(int startPoint, int endPoint);
    RankCustomer findByPointGreaterThanEqual(int point);
}
