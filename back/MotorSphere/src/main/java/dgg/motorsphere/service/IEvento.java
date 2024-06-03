package dgg.motorsphere.service;

import dgg.motorsphere.api.dto.IsSuscribedDTO;
import dgg.motorsphere.api.dto.UsuarioEventoDTO;
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

    UsuarioEventoDTO addUserToEvent(UsuarioEventoDTO usuarioEventoDTO);

    List<Long> isSuscribed(IsSuscribedDTO isSuscribedDTO);

    // Helper
    EventoDTO buildEventoDTO(Evento evento);

}
