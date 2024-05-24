package dgg.motorsphere.service.impl;

import dgg.motorsphere.api.dto.evento.EventoDTO;
import dgg.motorsphere.api.dto.FechasEventoDTO;
import dgg.motorsphere.api.dto.localizacion.LocalizacionDTO;
import dgg.motorsphere.model.dao.EventoDAO;
import dgg.motorsphere.model.dao.FechaDAO;
import dgg.motorsphere.model.dao.LocalizacionDAO;
import dgg.motorsphere.model.dao.OfertanteDAO;
import dgg.motorsphere.model.entity.Evento;
import dgg.motorsphere.model.entity.Fecha;
import dgg.motorsphere.model.entity.Localizacion;
import dgg.motorsphere.service.IEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventoImpl implements IEvento {

    @Autowired
    private EventoDAO eventoDAO;
    @Autowired
    private OfertanteDAO ofertanteDAO;
    @Autowired
    private FechaDAO fechaDAO;
    @Autowired
    private LocalizacionDAO localizacionDAO;

    @Override
    public List<EventoDTO> getAll() {
        List<EventoDTO> eventoDTOList = new ArrayList<>();
        List<Evento> eventos = (List<Evento>) eventoDAO.findAll();

        for (Evento evento : eventos){
            List<FechasEventoDTO> fechasEventoDTO = new ArrayList<>();
            List<Fecha> fechas = fechaDAO.findAllByEventoId(evento.getId());

            LocalizacionDTO localizacionDTO = LocalizacionDTO.builder()
                    .id(evento.getLocalizacion().getId())
                    .country(evento.getLocalizacion().getPais())
                    .municipality(evento.getLocalizacion().getMunicipio())
                    .province(evento.getLocalizacion().getProvincia())
                    .latitude(evento.getLocalizacion().getLatitud())
                    .longitude(evento.getLocalizacion().getLongitud())
                    .build();

            if (!fechas.isEmpty()) {
                for(Fecha fecha: fechas){
                    fechasEventoDTO.add(buildFechasParaEventoDTO(fecha));
                }
            }

            eventoDTOList.add(
                    EventoDTO.builder()
                            .eventId(evento.getId())
                            .bidderId(evento.getOfertante().getId())
                            .insigniaId(evento.getInsignia().getId())
                            .dates(fechasEventoDTO)
                            .localization(localizacionDTO)
                            .announcementDate(evento.getFechaAnuncioEvento())
                            .name(evento.getNombre())
                            .description(evento.getDescripcion())
                            .image(evento.getImagen())
                            .exigency(evento.getExigencia())
                            .score(evento.getPuntuacion())
                            .build()
            );
        }

        return eventoDTOList;
    }

    @Override
    public EventoDTO getById(Long id) {
        if (id != null && eventoDAO.findById(id).isPresent()){
            Evento evento = eventoDAO.findById(id).orElse(null);

            assert evento != null;

            List<FechasEventoDTO> fechasEventoDTO = new ArrayList<>();
            List<Fecha> fechas = fechaDAO.findAllByEventoId(evento.getId());

            if (!fechas.isEmpty()) {
                for(Fecha fecha: fechas){
                    fechasEventoDTO.add(buildFechasParaEventoDTO(fecha));
                }
            }

            LocalizacionDTO localizacionDTO = LocalizacionDTO.builder()
                    .id(evento.getLocalizacion().getId())
                    .country(evento.getLocalizacion().getPais())
                    .municipality(evento.getLocalizacion().getMunicipio())
                    .province(evento.getLocalizacion().getProvincia())
                    .latitude(evento.getLocalizacion().getLatitud())
                    .longitude(evento.getLocalizacion().getLongitud())
                    .build();

            return EventoDTO.builder()
                    .eventId(evento.getId())
                    .bidderId(evento.getOfertante().getId())
                    .insigniaId(evento.getInsignia().getId())
                    .dates(fechasEventoDTO)
                    .localization(localizacionDTO)
                    .announcementDate(evento.getFechaAnuncioEvento())
                    .name(evento.getNombre())
                    .description(evento.getDescripcion())
                    .image(evento.getImagen())
                    .exigency(evento.getExigencia())
                    .score(evento.getPuntuacion())
                    .build();
        }

        return null;
    }

    @Override
    public EventoDTO update(EventoDTO eventoDTO) {
        return null;
    }

    @Override
    public EventoDTO insert(EventoDTO eventoDTO) {
        return null;
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public EventoDTO buildEventoDTO(Evento evento) {
        return null;
    }

    public FechasEventoDTO buildFechasParaEventoDTO(Fecha fecha) {
        if (fecha != null) {
            return FechasEventoDTO.builder()
                    .id(fecha.getId())
                    .startDate(fecha.getFechaInicio())
                    .finalDate(fecha.getFechaFinal())
                    .price(fecha.getPrecio())
                    .places(fecha.getPlazas())
                    .placesAvailable(fecha.getPlazasDisponibles())
                    .build();
        }
        return null;
    }
/*
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
    */
}
