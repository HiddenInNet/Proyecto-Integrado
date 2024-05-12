package dgg.motorsphere.service.impl;

import dgg.motorsphere.api.dto.comentario.ComentarioDTO;
import dgg.motorsphere.model.dao.ComentarioDAO;
import dgg.motorsphere.model.dao.PublicacionDAO;
import dgg.motorsphere.model.dao.UsuarioDAO;
import dgg.motorsphere.model.entity.Comentario;
import dgg.motorsphere.service.IComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioImpl implements IComentario {

    @Autowired
    private ComentarioDAO comentarioDAO;
    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private PublicacionDAO publicacionDAO;

    @Override
    public List<ComentarioDTO> findAll() {
        List<Comentario> comentarios = (List<Comentario>) comentarioDAO.findAll();
        if (!comentarios.isEmpty()) {
            List<ComentarioDTO> comentarioDTOS = new ArrayList<>();
            comentarios.forEach(comentario -> comentarioDTOS.add(buildComentarioDTO(comentario)));
            return comentarioDTOS;
        }
        return null; // No se ha podido obtener los comentarios
    }

    @Override
    public ComentarioDTO findById(Long id) {
        if (id != null){
            Comentario comentario = comentarioDAO.findById(id).orElse(null);
            return buildComentarioDTO(comentario);
        }
        return null; // El id es nulo
    }

    @Override
    public boolean remove(Long id) {
        if (id != null) {
            if (comentarioDAO.findById(id).isPresent()) {
                comentarioDAO.deleteById(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public ComentarioDTO insert(ComentarioDTO comentarioDTO) {
        if(comentarioDTO != null){
            Comentario comment = Comentario.builder()
                    .informacion(comentarioDTO.getInformation())
                    .fecha(comentarioDTO.getDate())
                    .id(comentarioDTO.getId())
                    .usuario(usuarioDAO.findById(comentarioDTO.getUserId()).orElse(null))
                    .publicacion(publicacionDAO.findById(comentarioDTO.getPublicationId()).orElse(null))
                    .build();
            return buildComentarioDTO(comentarioDAO.save(comment));
        }
        return null;
    }

    @Override
    public ComentarioDTO update(ComentarioDTO comentarioDTO) {
        if(comentarioDTO != null){
            if(comentarioDAO.findById(comentarioDTO.getId()).isPresent()){
                Comentario comentario = Comentario.builder()
                        .fecha(comentarioDTO.getDate())
                        .id(comentarioDTO.getId())
                        .publicacion(publicacionDAO.findById(comentarioDTO.getPublicationId()).orElse(null))
                        .usuario(usuarioDAO.findById(comentarioDTO.getUserId()).orElse(null))
                        .informacion(comentarioDTO.getInformation())
                        .build();
                return buildComentarioDTO(comentarioDAO.save(comentario));
            }
            return null; // No existe el comentario
        }
        return null; // comentarioDTO es nulo
    }

    public ComentarioDTO buildComentarioDTO(Comentario comentario) {
        if (comentario != null) {
            return ComentarioDTO.builder()
                    .id(comentario.getId())
                    .date(comentario.getFecha())
                    .publicationId(comentario.getPublicacion().getId())
                    .userId(comentario.getUsuario().getId())
                    .information(comentario.getInformacion())
                    .build();
        }
        return null; // No se ha podido crear el comentarioDTO de forma correcta
    }
}
