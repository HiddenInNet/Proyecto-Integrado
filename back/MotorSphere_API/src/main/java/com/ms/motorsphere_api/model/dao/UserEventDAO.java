package com.ms.motorsphere_api.model.dao;

import com.ms.motorsphere_api.model.entity.UserEvent;
import org.springframework.data.repository.CrudRepository;

public interface UserEventDAO extends CrudRepository<UserEvent, Long> {
}
