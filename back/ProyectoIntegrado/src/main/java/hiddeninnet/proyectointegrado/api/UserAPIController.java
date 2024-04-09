package hiddeninnet.proyectointegrado.api;

import hiddeninnet.proyectointegrado.api.dto.UserDTO;
import hiddeninnet.proyectointegrado.mappers.Mapper;
import hiddeninnet.proyectointegrado.model.User;
import hiddeninnet.proyectointegrado.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserAPIController {

    private static final Logger logger = LoggerFactory.getLogger(UserAPIController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper<User, UserDTO> userMapper;

    // Obtener todos los usuarios
    @GetMapping("/api/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        logger.info("Get all users");
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }
}
