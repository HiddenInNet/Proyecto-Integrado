package com.ms.motorsphere_api.model.dao;

import com.ms.motorsphere_api.model.entity.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginDAO extends CrudRepository<Login, Long> {

    Login findByUsernameAndPassword(String username, String password);
}
