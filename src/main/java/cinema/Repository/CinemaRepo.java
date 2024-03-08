package cinema.Repository;

import cinema.Entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema, Integer> {
}
