package cinema.Repository;

import cinema.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Integer> {
}
