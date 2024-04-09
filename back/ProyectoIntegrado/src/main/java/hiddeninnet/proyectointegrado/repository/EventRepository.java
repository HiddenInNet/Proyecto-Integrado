package hiddeninnet.proyectointegrado.repository;

import hiddeninnet.proyectointegrado.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
