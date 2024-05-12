package com.ms.motorsphere_api.services;

import com.ms.motorsphere_api.model.dto.LoginDTO;
import com.ms.motorsphere_api.model.dto.UserDTO;
import com.ms.motorsphere_api.model.dto.UserWithPublicationsDTO;
import com.ms.motorsphere_api.model.entity.Usuario;

import java.util.List;

public interface IUsuario {

    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(UserDTO user);
    void remove(Usuario usuario);

    UserDTO userLogin(LoginDTO loginDTO);

    UserWithPublicationsDTO getUserPublications(Long id);
}
