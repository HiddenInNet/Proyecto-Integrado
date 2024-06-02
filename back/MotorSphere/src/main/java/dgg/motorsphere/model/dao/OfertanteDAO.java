package dgg.motorsphere.model.dao;

import dgg.motorsphere.model.entity.Ofertante;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OfertanteDAO extends CrudRepository<Ofertante, Long> {

    Optional<Ofertante> findByUsuarioId(Long id);

    boolean deleteByUsuarioId(Long id);
}
