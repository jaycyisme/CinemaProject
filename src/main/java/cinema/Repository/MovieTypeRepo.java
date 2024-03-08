package cinema.Repository;

import cinema.Entity.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTypeRepo extends JpaRepository<MovieType, Integer> {
}
