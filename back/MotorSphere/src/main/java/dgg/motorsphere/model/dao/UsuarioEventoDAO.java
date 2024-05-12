package dgg.motorsphere.model.dao;

import dgg.motorsphere.model.entity.relations.UsuarioInscritoEvento;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioEventoDAO extends CrudRepository<UsuarioInscritoEvento, Long> {
}
