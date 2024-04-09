package com.ms.motorsphere_api.controller;

import com.ms.motorsphere_api.error.ErrorResponse;
import com.ms.motorsphere_api.model.dto.LoginDTO;
import com.ms.motorsphere_api.model.dto.UserDTO;
import com.ms.motorsphere_api.model.dto.UserWithPublicationsDTO;
import com.ms.motorsphere_api.model.entity.User;
import com.ms.motorsphere_api.services.IUser;
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
public class UserController {
    static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUser userService;

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserDTO userDTO){
        User userSave = userService.save(userDTO);

        return UserDTO.builder()
                .id(userSave.getId())
                .birthDate(userSave.getBirthDate())
                .profileDate(userSave.getProfileDate())
                .biography(userSave.getBiography())
                .email(userSave.getEmail())
                .lastName(userSave.getLastName())
                .name(userSave.getName())
                .phone(userSave.getPhone())
                .profileImage(userSave.getProfileImage())
                .build();
    }

    @PutMapping("update")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO update(@RequestBody UserDTO userDTO){

        User userUpdate = userService.save(userDTO);

        return UserDTO.builder()
                .id(userUpdate.getId())
                .birthDate(userUpdate.getBirthDate())
                .profileDate(userUpdate.getProfileDate())
                .biography(userUpdate.getBiography())
                .email(userUpdate.getEmail())
                .lastName(userUpdate.getLastName())
                .name(userUpdate.getName())
                .phone(userUpdate.getPhone())
                .profileImage(userUpdate.getProfileImage())
                .build();
    }

    @DeleteMapping("remove/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    // O ponermos ResponseStatus o lo hacemos por un control try catch
    public ResponseEntity<?> remove(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();
        try {
            User userDel = userService.findById(id);
            userService.remove(userDel);
            return new ResponseEntity<>(userDel, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exRv) {
            response.put("mensaje", exRv.getMessage());
            response.put("user", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO showById(@PathVariable Long id){

        User user = userService.findById(id);

        return UserDTO.builder()
                .id(user.getId())
                .birthDate(user.getBirthDate())
                .profileDate(user.getProfileDate())
                .biography(user.getBiography())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .name(user.getName())
                .phone(user.getPhone())
                .profileImage(user.getProfileImage())
                .build();
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(){

        List<User> userList = userService.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .birthDate(user.getBirthDate())
                    .profileDate(user.getProfileDate())
                    .biography(user.getBiography())
                    .email(user.getEmail())
                    .lastName(user.getLastName())
                    .name(user.getName())
                    .phone(user.getPhone())
                    .profileImage(user.getProfileImage())
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
