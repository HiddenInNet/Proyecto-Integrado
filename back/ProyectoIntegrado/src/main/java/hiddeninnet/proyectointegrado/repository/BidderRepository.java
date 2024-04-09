package hiddeninnet.proyectointegrado.repository;

import hiddeninnet.proyectointegrado.model.Bidder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidderRepository extends JpaRepository<Bidder, Long> {
}
