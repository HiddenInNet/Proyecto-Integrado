package dgg.motorsphere.service;

import dgg.motorsphere.api.dto.evento.CreateEventoDTO;
import dgg.motorsphere.api.dto.evento.EventoDTO;
import dgg.motorsphere.model.entity.Evento;

import java.util.List;

public interface IEvento {

    List<EventoDTO> getAll();
    EventoDTO getById(Long id);
    EventoDTO update(EventoDTO eventoDTO);
    EventoDTO insert(CreateEventoDTO createEventoDTO);
    boolean remove(Long id);

    // Helper
    EventoDTO buildEventoDTO(Evento evento);

}
