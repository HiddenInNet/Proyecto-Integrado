package com.ms.motorsphere_api.services.impl;

import com.ms.motorsphere_api.model.dao.LoginDAO;
import com.ms.motorsphere_api.model.dao.PublicationDAO;
import com.ms.motorsphere_api.model.dao.UserDAO;
import com.ms.motorsphere_api.model.dto.LoginDTO;
import com.ms.motorsphere_api.model.dto.PublicationDTO;
import com.ms.motorsphere_api.model.dto.UserDTO;
import com.ms.motorsphere_api.model.dto.UserWithPublicationsDTO;
import com.ms.motorsphere_api.model.entity.Login;
import com.ms.motorsphere_api.model.entity.Publication;
import com.ms.motorsphere_api.model.entity.User;
import com.ms.motorsphere_api.services.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserImpl implements IUser {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private LoginDAO loginDAO;
    @Autowired
    private PublicationDAO publicationDAO;

    @Transactional
    @Override
    public User save(UserDTO userDTO) {

        User user = User.builder()
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

        return userDAO.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) userDAO.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        return userDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void remove(User user) {
        userDAO.delete(user);
    }

    @Override
    public UserDTO userLogin(LoginDTO loginDTO) {

        Login login = loginDAO.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        return UserDTO.builder()
                .id(login.getUser().getId())
                .username(login.getUsername())
                .email(login.getUser().getEmail())
                .name(login.getUser().getName())
                .lastName(login.getUser().getLastName())
                .birthDate(login.getUser().getBirthDate())
                .phone(login.getUser().getPhone())
                .profileDate(login.getUser().getProfileDate())
                .biography(login.getUser().getBiography())
                .profileImage(login.getUser().getProfileImage())
                .build();
    }

    @Override
    public UserWithPublicationsDTO getUserPublications(Long id) {
        if (id != null){
            User user = userDAO.findById(id).orElse(null);

            if (user != null){
                List<Publication> publications = publicationDAO.findAllByUserId(id);
                List<PublicationDTO> publicationsDTO = new ArrayList<>();

                if (publications != null && !publications.isEmpty()){
                    for (Publication publi : publications) {
                        PublicationDTO pu = PublicationDTO.builder()
                                .publicationId(publi.getId())
                                .userId(publi.getUser().getId())
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
                        .id(user.getId())
                        .username(user.getLogin().getUsername())
                        .email(user.getEmail())
                        .name(user.getName())
                        .lastName(user.getLastName())
                        .birthDate(user.getBirthDate())
                        .phone(user.getPhone())
                        .profileDate(user.getProfileDate())
                        .biography(user.getBiography())
                        .profileImage(user.getProfileImage())
                        .publications(publicationsDTO)
                        .build();
            }
        }
        return null;
    }

}
