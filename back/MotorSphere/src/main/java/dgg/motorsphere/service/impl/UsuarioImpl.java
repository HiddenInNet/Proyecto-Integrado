package dgg.motorsphere.service.impl;

import dgg.motorsphere.api.dto.usuario.UsuarioDTO;
import dgg.motorsphere.api.dto.usuario.UsuarioUpdateDTO;
import dgg.motorsphere.model.dao.UserDAO;
import dgg.motorsphere.model.dao.UsuarioDAO;
import dgg.motorsphere.api.dto.usuario.UsuarioInsertDTO;
import dgg.motorsphere.model.entity.Usuario;
import dgg.motorsphere.service.IUsuario;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioImpl implements IUsuario {
    static final Logger log = LoggerFactory.getLogger(UsuarioImpl.class);

    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public UsuarioDTO update(UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioDAO.findById(usuarioUpdateDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        String nuevoUsername = usuarioUpdateDTO.getUsername();
        String nuevoEmail = usuarioUpdateDTO.getEmail();

        if (!nuevoUsername.equals(usuario.getUser().getUsername()) &&
                userDAO.findByUsername(nuevoUsername) != null) {
            return null;
        }

        if (!nuevoEmail.equals(usuario.getEmail()) &&
                usuarioDAO.findByEmail(nuevoEmail) != null) {
            return null;
        }

        usuario.getUser().setUsername(nuevoUsername);
        usuario.setEmail(nuevoEmail);
        usuario.setNombre(usuarioUpdateDTO.getName());
        usuario.setApellidos(usuarioUpdateDTO.getLastName());
        usuario.setFechaNacimiento(usuarioUpdateDTO.getBirthDate());
        usuario.setTelefono(usuarioUpdateDTO.getPhone());
        usuario.setBiografia(usuarioUpdateDTO.getBiography());
        usuario.setImagenPerfil(usuarioUpdateDTO.getProfileImage());

        Usuario usuarioGuardado = usuarioDAO.save(usuario);
        return buildUsuarioDTO(usuarioGuardado);
    }

    @Override
    public UsuarioDTO insert(UsuarioInsertDTO usuarioInsertDTO) {
        log.info("El valor es: "+usuarioInsertDTO);

        if (usuarioInsertDTO != null && userDAO.findById(usuarioInsertDTO.getUserId()).isPresent()) {
            if(usuarioDAO.findByEmail(usuarioInsertDTO.getEmail()) == null) {

                Usuario usuario = Usuario.builder()
                        .user(userDAO.findById(usuarioInsertDTO.getUserId()).orElse(null))
                        .email(usuarioInsertDTO.getEmail())
                        .nombre(usuarioInsertDTO.getName())
                        .apellidos(usuarioInsertDTO.getLastName())
                        .fechaNacimiento(usuarioInsertDTO.getBirthDate())
                        .telefono(usuarioInsertDTO.getPhone())
                        .fechaCreacionPerfil(usuarioInsertDTO.getProfileDate())
                        .biografia(usuarioInsertDTO.getBiography())
                        .imagenPerfil(usuarioInsertDTO.getProfileImage())
                        .build();
                if(usuario.getUser() != null) {

                    usuario = usuarioDAO.save(usuario);
                    log.info("El valor es: "+buildUsuarioDTO(usuario));

                    return buildUsuarioDTO(usuario);
                }
            }
        }
        log.error("El valor es nulo");
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioDTO> getAll() {
        List<Usuario> usuarios = (List<Usuario>) usuarioDAO.findAll();
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuarioDTOS.add(buildUsuarioDTO(usuario));
        }
        return usuarioDTOS;
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioDTO getById(Long id) {
        Usuario usuario = usuarioDAO.findById(id).orElse(null);
        return buildUsuarioDTO(usuario);
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        if(id != null) {
            if(usuarioDAO.findById(id).isPresent()) {
                usuarioDAO.delete(Objects.requireNonNull(usuarioDAO.findById(id).orElse(null)));
                return true;
            }
        }
        return false;
    }

    /*
    @Override
    public UserWithPublicationsDTO getUserPublications(Long id) {
        if (id != null){
            Usuario usuario = usuarioDAO.findById(id).orElse(null);

            if (usuario != null){
                List<Publication> publications = publicationDAO.findAllByUsuarioId(id);
                List<PublicationDTO> publicationsDTO = new ArrayList<>();

                if (publications != null && !publications.isEmpty()){
                    for (Publication publi : publications) {
                        PublicationDTO pu = PublicationDTO.builder()
                                .publicationId(publi.getId())
                                .userId(publi.getUsuario().getId())
                                .name(publi.getName())
                                .information(publi.getInformation())
                                .uploadDate(publi.getDate())
                                .likes(publi.getLikes())
                                .image(publi.getImage())
                                .build();

                        publicationsDTO.add(pu);
                    }
                }

                return UserWithPublicationsDTO.builder()
                        .id(usuario.getId())
                        .username(usuario.getLogin().getUsername())
                        .email(usuario.getEmail())
                        .name(usuario.getName())
                        .lastName(usuario.getLastName())
                        .birthDate(usuario.getBirthDate())
                        .phone(usuario.getPhone())
                        .profileDate(usuario.getProfileDate())
                        .biography(usuario.getBiography())
                        .profileImage(usuario.getProfileImage())
                        .publications(publicationsDTO)
                        .build();
            }
        }
        return null;
    }
    */

    @Override
    public UsuarioDTO buildUsuarioDTO(Usuario usuario){
        if (usuario != null) {
            return UsuarioDTO.builder()
                    .id(usuario.getId())
                    .email(usuario.getEmail())
                    .username(usuario.getUser().getUsername())
                    .name(usuario.getNombre())
                    .lastName(usuario.getApellidos())
                    .birthDate(usuario.getFechaNacimiento())
                    .phone(usuario.getTelefono())
                    .profileDate(usuario.getFechaCreacionPerfil())
                    .biography(usuario.getBiografia())
                    .profileImage(usuario.getImagenPerfil())
                    .build();
        }
        return null;
    }
}
