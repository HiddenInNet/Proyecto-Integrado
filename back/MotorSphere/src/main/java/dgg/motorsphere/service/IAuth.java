package dgg.motorsphere.service;

import dgg.motorsphere.api.dto.login.UserDTO;

import java.util.Optional;

public interface IAuth {

    Optional<Long> login(UserDTO userDTO);
    String register(UserDTO userDTO);
}
