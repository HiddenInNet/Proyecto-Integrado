package com.ms.motorsphere_api.model.dao;

import com.ms.motorsphere_api.model.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventDAO extends CrudRepository<Event, Long> {
}
