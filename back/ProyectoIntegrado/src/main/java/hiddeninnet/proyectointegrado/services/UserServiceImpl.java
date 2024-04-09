package hiddeninnet.proyectointegrado.services;

import hiddeninnet.proyectointegrado.model.User;
import hiddeninnet.proyectointegrado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {

        if (username != null) {
            return userRepository.findByUsername(username);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean removeUserById(Long id) {
        return false;
    }
}
