package dgg.motorsphere.model.dao;

import dgg.motorsphere.model.entity.Fecha;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FechaDAO extends CrudRepository<Fecha, Long> {

    List<Fecha> findAllByEventoId(Long id);
}
