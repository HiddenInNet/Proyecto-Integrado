package com.ms.motorsphere_api.services.impl;

import com.ms.motorsphere_api.model.dao.LoginDAO;
import com.ms.motorsphere_api.model.dao.PublicationDAO;
import com.ms.motorsphere_api.model.dao.UsuarioDAO;
import com.ms.motorsphere_api.model.dto.LoginDTO;
import com.ms.motorsphere_api.model.dto.PublicationDTO;
import com.ms.motorsphere_api.model.dto.UserDTO;
import com.ms.motorsphere_api.model.dto.UserWithPublicationsDTO;
import com.ms.motorsphere_api.model.entity.Login;
import com.ms.motorsphere_api.model.entity.Publication;
import com.ms.motorsphere_api.model.entity.Usuario;
import com.ms.motorsphere_api.services.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioImpl implements IUsuario {

    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private LoginDAO loginDAO;
    @Autowired
    private PublicationDAO publicationDAO;

    @Transactional
    @Override
    public Usuario save(UserDTO userDTO) {

        Usuario usuario = Usuario.builder()
                .id(userDTO.getId())
                .birthDate(userDTO.getBirthDate())
                .profileDate(userDTO.getProfileDate())
                .biography(userDTO.getBiography())
                .email(userDTO.getEmail())
                .lastName(userDTO.getLastName())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .profileImage(userDTO.getProfileImage())
                .build();

        return usuarioDAO.save(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario findById(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void remove(Usuario usuario) {
        usuarioDAO.delete(usuario);
    }

    @Override
    public UserDTO userLogin(LoginDTO loginDTO) {

        Login login = loginDAO.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        return UserDTO.builder()
                .id(login.getUsuario().getId())
                .username(login.getUsername())
                .email(login.getUsuario().getEmail())
                .name(login.getUsuario().getName())
                .lastName(login.getUsuario().getLastName())
                .birthDate(login.getUsuario().getBirthDate())
                .phone(login.getUsuario().getPhone())
                .profileDate(login.getUsuario().getProfileDate())
                .biography(login.getUsuario().getBiography())
                .profileImage(login.getUsuario().getProfileImage())
                .build();
    }

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

}
