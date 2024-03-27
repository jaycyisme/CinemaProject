package cinema.Repository;

import cinema.Entity.Cinema;
import cinema.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {

    List<Room> findAllByCinema(Cinema cinema);
}
