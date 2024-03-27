package cinema.Repository;

import cinema.Entity.Promotion;
import cinema.Entity.RankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Integer> {

    Promotion findAllByRankCustomer(RankCustomer rankCustomer);
}
