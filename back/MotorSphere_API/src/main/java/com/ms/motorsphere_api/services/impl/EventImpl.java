package com.ms.motorsphere_api.services.impl;

import com.ms.motorsphere_api.model.dao.BidderDAO;
import com.ms.motorsphere_api.model.dao.EventDAO;
import com.ms.motorsphere_api.model.dto.EventDTO;
import com.ms.motorsphere_api.model.entity.Event;
import com.ms.motorsphere_api.services.IEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventImpl implements IEvent {

    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private BidderDAO bidderDAO;

    @Override
    public List<Event> findAll() {
        return (List<Event>) eventDAO.findAll();
    }

    @Override
    public Event findById(Long id) {
        if(id != null){
            return eventDAO.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public Boolean remove(Long id) {
        if (id != null && eventDAO.existsById(id)) {
            eventDAO.deleteById(id);
            return !eventDAO.existsById(id);
        }
        return false;
    }

    @Override
    public Event save(EventDTO eventDTO) {
        if (eventDTO != null){

            Event event = Event.builder()
                    .id(eventDTO.getEventId())
                    .bidder(bidderDAO.findById(eventDTO.getBidderId()).orElse(null))
                    .eventLatitude(eventDTO.getEventLat())
                    .eventLongitude(eventDTO.getEventLon())
                    .dueDate(eventDTO.getDueDate())
                    .uploadDate(eventDTO.getUploadDate())
                    .description(eventDTO.getDescription())
                    .name(eventDTO.getName())
                    .image(eventDTO.getImage())
                    .build();
            return eventDAO.save(event);
        }
        return null;
    }
}
