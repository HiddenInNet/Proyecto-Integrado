package com.ms.motorsphere_api.model.dao;

import com.ms.motorsphere_api.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario, Long> {

}
