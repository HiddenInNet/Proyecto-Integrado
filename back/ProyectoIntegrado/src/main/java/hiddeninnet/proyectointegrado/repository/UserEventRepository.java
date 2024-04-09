package hiddeninnet.proyectointegrado.repository;

import hiddeninnet.proyectointegrado.model.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
}
