package dgg.motorsphere.model.dao;

import dgg.motorsphere.model.entity.Ofertante;
import org.springframework.data.repository.CrudRepository;

public interface OfertanteDAO extends CrudRepository<Ofertante, Long> {

    Ofertante findByUsuarioId(Long id);
}
