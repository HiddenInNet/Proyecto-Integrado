package dgg.motorsphere.service.impl;

import dgg.motorsphere.api.dto.login.RegisterDTO;
import dgg.motorsphere.api.dto.login.UserDTO;
import dgg.motorsphere.model.dao.UserDAO;
import dgg.motorsphere.model.dao.UsuarioDAO;
import dgg.motorsphere.model.entity.User;
import dgg.motorsphere.model.entity.Usuario;
import dgg.motorsphere.service.IAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
@Service
public class LoginService implements IAuth {

    @Autowired
    UserDAO userDAO;
    @Autowired
    UsuarioDAO usuarioDAO;

    //Hard coded login setup for JWT showcase, do not use in a real application.
    public Optional<Long> login(UserDTO userDTO) {

        User user = userDAO.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        if (user != null) {
            return Optional.of(user.getUserId());
        }

        return Optional.empty();
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        if (registerDTO != null) {
            if (userDAO.findByUsername(registerDTO.getUsername()) == null) {
                User user = User.builder()
                        .username(registerDTO.getUsername())
                        .password(registerDTO.getPassword())
                        .build();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = null;
                try {
                    fechaNacimiento = dateFormat.parse(registerDTO.getBirthDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                    return "Formato de fecha de nacimiento inválido";
                }

                Usuario usuario = Usuario.builder()
                        .nombre(registerDTO.getName())
                        .apellidos(registerDTO.getLastName())
                        .email(registerDTO.getEmail())
                        .telefono(registerDTO.getPhone())
                        .biografia(registerDTO.getBiography())
                        .fechaNacimiento(fechaNacimiento)
                        .imagenPerfil(registerDTO.getProfileImage())
                        .user(user)
                        .fechaCreacionPerfil(new Date())
                        .build();

                User savedUser = userDAO.save(user);
                Usuario savedUsuario = usuarioDAO.save(usuario);

                return savedUser != null && savedUsuario != null ? "OK" : "Fallo al guardar";
            } else {
                return "El usuario ya existe";
            }
        } else {
            return "UsuarioDTO incompleto";
        }
    }

}
