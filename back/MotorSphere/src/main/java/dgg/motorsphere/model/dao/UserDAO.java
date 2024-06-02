package dgg.motorsphere.model.dao;

import dgg.motorsphere.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO extends CrudRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    void deleteByUsername(String username);
}
