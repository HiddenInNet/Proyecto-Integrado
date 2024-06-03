package dgg.motorsphere.model.dao;

import dgg.motorsphere.model.entity.relations.UsuarioInscritoEvento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioInscritoEventoDAO extends CrudRepository<UsuarioInscritoEvento, Long> {

    List<UsuarioInscritoEvento> findAllByUsuarioIdAndEventoId(Long usuarioId, Long eventoId);
}
