package dgg.motorsphere.model.dao;

import dgg.motorsphere.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
