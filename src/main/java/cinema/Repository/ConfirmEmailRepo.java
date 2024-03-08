package cinema.Repository;

import cinema.Entity.ConfirmEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmEmailRepo extends JpaRepository<ConfirmEmail, Integer> {
}
