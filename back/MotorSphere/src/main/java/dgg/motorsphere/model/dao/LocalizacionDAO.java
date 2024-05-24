package dgg.motorsphere.model.dao;

import dgg.motorsphere.model.entity.Localizacion;
import org.springframework.data.repository.CrudRepository;

public interface LocalizacionDAO extends CrudRepository<Localizacion, Long> {

    Localizacion findByEvento_Id(Long id);
}
