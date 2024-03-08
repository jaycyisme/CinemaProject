package cinema.Repository;

import cinema.Entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepo extends JpaRepository<UserStatus, Integer> {
}
