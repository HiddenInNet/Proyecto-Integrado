package hiddeninnet.proyectointegrado.repository;

import hiddeninnet.proyectointegrado.model.Insignia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsigniaRepository extends JpaRepository<Insignia, Long> {
}
