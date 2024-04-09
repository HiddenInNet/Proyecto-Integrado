package com.ms.motorsphere_api.services;

import com.ms.motorsphere_api.model.dto.LoginDTO;
import com.ms.motorsphere_api.model.dto.UserDTO;
import com.ms.motorsphere_api.model.dto.UserWithPublicationsDTO;
import com.ms.motorsphere_api.model.entity.Login;
import com.ms.motorsphere_api.model.entity.User;

import java.sql.SQLNonTransientConnectionException;
import java.util.List;
import java.util.Optional;

public interface IUser {

    List<User> findAll();
    User findById(Long id);
    User save(UserDTO user);
    void remove(User user);

    UserDTO userLogin(LoginDTO loginDTO);

    UserWithPublicationsDTO getUserPublications(Long id);
}
