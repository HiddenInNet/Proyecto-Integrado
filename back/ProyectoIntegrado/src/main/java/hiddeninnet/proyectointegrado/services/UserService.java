package hiddeninnet.proyectointegrado.services;

import hiddeninnet.proyectointegrado.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getAllUsers();
    public Optional<User> getUserById(Long id);
    public Optional<User> getUserByUsername(String username);
    public User addUser(User user);
    public User updateUser(User user);
    public boolean removeUserById(Long id);

}
