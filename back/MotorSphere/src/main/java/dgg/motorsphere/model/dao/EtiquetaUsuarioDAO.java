package dgg.motorsphere.model.dao;

import dgg.motorsphere.model.entity.relations.EtiquetaUsuario;
import org.springframework.data.repository.CrudRepository;

public interface EtiquetaUsuarioDAO extends CrudRepository<EtiquetaUsuario, Long> {
    void deleteByUsuarioId(Long id);
}
