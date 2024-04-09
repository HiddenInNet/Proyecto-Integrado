package com.ms.motorsphere_api.model.dao;

import com.ms.motorsphere_api.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Long> {


}
