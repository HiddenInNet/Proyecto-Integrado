package com.ms.motorsphere_api.services;

import com.ms.motorsphere_api.model.dto.EventDTO;
import com.ms.motorsphere_api.model.entity.Event;

import java.util.List;

public interface IEvent {

    List<Event> findAll();
    Event findById(Long id);
    Boolean remove(Long id);
    Event save(EventDTO eventDTO);
}
