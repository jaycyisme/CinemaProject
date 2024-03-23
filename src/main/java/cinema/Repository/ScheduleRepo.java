package cinema.Repository;

import cinema.Entity.Cinema;
import cinema.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
    public void deleteByRoom_Cinema(Cinema cinema);
}
