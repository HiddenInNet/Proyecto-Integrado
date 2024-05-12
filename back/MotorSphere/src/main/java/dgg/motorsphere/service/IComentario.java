package dgg.motorsphere.service;


import dgg.motorsphere.api.dto.comentario.ComentarioDTO;
import dgg.motorsphere.model.entity.Comentario;

import java.util.List;

public interface IComentario {

    List<ComentarioDTO> findAll();
    ComentarioDTO findById(Long id);
    boolean remove(Long id);
    ComentarioDTO update(ComentarioDTO comentarioDTO);
    ComentarioDTO insert(ComentarioDTO comentarioDTO);

    ComentarioDTO buildComentarioDTO(Comentario comentario);
}
