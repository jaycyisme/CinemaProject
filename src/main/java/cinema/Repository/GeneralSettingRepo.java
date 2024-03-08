package cinema.Repository;

import cinema.Entity.GeneralSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralSettingRepo extends JpaRepository<GeneralSetting, Integer> {
}
