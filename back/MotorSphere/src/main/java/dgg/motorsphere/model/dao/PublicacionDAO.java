package dgg.motorsphere.model.dao;

import dgg.motorsphere.model.entity.Publicacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublicacionDAO extends CrudRepository<Publicacion, Long> {

    List<Publicacion> findAllByUsuarioId(Long id);
}
