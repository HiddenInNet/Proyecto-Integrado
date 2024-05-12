package dgg.motorsphere.service;

import dgg.motorsphere.api.dto.usuario.UsuarioDTO;
import dgg.motorsphere.api.dto.usuario.UsuarioInsertDTO;
import dgg.motorsphere.api.dto.usuario.UsuarioUpdateDTO;
import dgg.motorsphere.model.entity.Usuario;

import java.util.List;

public interface IUsuario {

    List<UsuarioDTO> getAll();
    UsuarioDTO getById(Long id);
    UsuarioDTO update(UsuarioUpdateDTO usuarioUpdateDTO);
    UsuarioDTO insert(UsuarioInsertDTO UsuarioInsertDTO);
    boolean remove(Long id);

    // UserWithPublicationsDTO getUserPublications(Long id);

    // Métodos auxiliares
    UsuarioDTO buildUsuarioDTO(Usuario usuario);
}
