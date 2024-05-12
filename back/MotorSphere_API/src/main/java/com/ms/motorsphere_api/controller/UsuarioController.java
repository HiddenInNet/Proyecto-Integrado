package com.ms.motorsphere_api.controller;

import com.ms.motorsphere_api.error.ErrorResponse;
import com.ms.motorsphere_api.model.dto.LoginDTO;
import com.ms.motorsphere_api.model.dto.UserDTO;
import com.ms.motorsphere_api.model.dto.UserWithPublicationsDTO;
import com.ms.motorsphere_api.model.entity.Usuario;
import com.ms.motorsphere_api.services.IUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v0/user")
public class UsuarioController {
    static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuario userService;

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserDTO userDTO){
        Usuario usuarioSave = userService.save(userDTO);

        return UserDTO.builder()
                .id(usuarioSave.getId())
                .username(usuarioSave.getLogin().getUsername())
                .birthDate(usuarioSave.getBirthDate())
                .profileDate(usuarioSave.getProfileDate())
                .biography(usuarioSave.getBiography())
                .email(usuarioSave.getEmail())
                .lastName(usuarioSave.getLastName())
                .name(usuarioSave.getName())
                .phone(usuarioSave.getPhone())
                .profileImage(usuarioSave.getProfileImage())
                .build();
    }

    @PutMapping("update")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO update(@RequestBody UserDTO userDTO){

        Usuario usuarioUpdate = userService.save(userDTO);

        return UserDTO.builder()
                .id(usuarioUpdate.getId())
                .birthDate(usuarioUpdate.getBirthDate())
                .profileDate(usuarioUpdate.getProfileDate())
                .biography(usuarioUpdate.getBiography())
                .email(usuarioUpdate.getEmail())
                .lastName(usuarioUpdate.getLastName())
                .name(usuarioUpdate.getName())
                .phone(usuarioUpdate.getPhone())
                .profileImage(usuarioUpdate.getProfileImage())
                .build();
    }

    @DeleteMapping("remove/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    // O ponermos ResponseStatus o lo hacemos por un control try catch
    public ResponseEntity<?> remove(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuarioDel = userService.findById(id);
            userService.remove(usuarioDel);
            return new ResponseEntity<>(usuarioDel, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exRv) {
            response.put("mensaje", exRv.getMessage());
            response.put("user", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO showById(@PathVariable Long id){

        Usuario usuario = userService.findById(id);

        return UserDTO.builder()
                .id(usuario.getId())
                .birthDate(usuario.getBirthDate())
                .profileDate(usuario.getProfileDate())
                .biography(usuario.getBiography())
                .email(usuario.getEmail())
                .lastName(usuario.getLastName())
                .name(usuario.getName())
                .phone(usuario.getPhone())
                .profileImage(usuario.getProfileImage())
                .build();
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(){

        List<Usuario> usuarioList = userService.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (Usuario usuario : usuarioList) {
            UserDTO userDTO = UserDTO.builder()
                    .id(usuario.getId())
                    .birthDate(usuario.getBirthDate())
                    .profileDate(usuario.getProfileDate())
                    .biography(usuario.getBiography())
                    .email(usuario.getEmail())
                    .lastName(usuario.getLastName())
                    .name(usuario.getName())
                    .phone(usuario.getPhone())
                    .profileImage(usuario.getProfileImage())
                    .build();
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    @GetMapping("{id}/publications")
    public ResponseEntity<?> userWithPublications(@PathVariable Long id){
        UserWithPublicationsDTO response = userService.getUserPublications(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserDTO userDTO = userService.userLogin(loginDTO);
            return ResponseEntity.ok(userDTO);
        } catch (NullPointerException e){
            response.put("message", e.getMessage());
            response.put("cause", ErrorResponse.SERVER_001);
            response.put("userDTO", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
