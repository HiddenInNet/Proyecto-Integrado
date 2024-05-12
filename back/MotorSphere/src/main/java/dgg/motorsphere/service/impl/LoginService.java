package dgg.motorsphere.service.impl;

import dgg.motorsphere.api.dto.login.UserDTO;
import dgg.motorsphere.model.dao.UserDAO;
import dgg.motorsphere.model.entity.User;
import dgg.motorsphere.service.IAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LoginService implements IAuth {

    @Autowired
    UserDAO userDAO;

    //Hard coded login setup for JWT showcase, do not use in a real application.
    public Optional<Long> login(UserDTO userDTO) {

        User user = userDAO.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        if (user != null) {
            return Optional.of(user.getUserId());
        }

        return Optional.empty();
    }

    @Override
    public String register(UserDTO userDTO) {
        if (userDTO != null) {
            if (userDAO.findByUsername(userDTO.getUsername()) == null) {
                User user = User.builder()
                        .username(userDTO.getUsername())
                        .password(userDTO.getPassword())
                        .build();

                User savedUser = userDAO.save(user);
                return savedUser != null ? "OK" : "Fallo al guardar";
            } else {
                return "El usuario ya existe";
            }
        } else {
            return "UsuarioDTO incompleto";
        }
    }

}
