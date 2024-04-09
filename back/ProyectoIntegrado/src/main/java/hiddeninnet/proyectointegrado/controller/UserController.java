package hiddeninnet.proyectointegrado.controller;

import hiddeninnet.proyectointegrado.model.User;
import hiddeninnet.proyectointegrado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{username}")
    public Optional<User> findByUsername(@PathVariable("username") String username) {
        return userRepository.findByUsername(username);
    }

}
