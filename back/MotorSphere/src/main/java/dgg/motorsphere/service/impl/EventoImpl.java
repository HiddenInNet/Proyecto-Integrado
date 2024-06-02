package dgg.motorsphere.service.impl;

import dgg.motorsphere.api.dto.evento.CreateEventoDTO;
import dgg.motorsphere.api.dto.evento.EventoDTO;
import dgg.motorsphere.api.dto.FechasEventoDTO;
import dgg.motorsphere.api.dto.insignia.InsigniaDTO;
import dgg.motorsphere.api.dto.localizacion.LocalizacionDTO;
import dgg.motorsphere.model.dao.*;
import dgg.motorsphere.model.entity.*;
import dgg.motorsphere.model.entity.relations.EtiquetaEvento;
import dgg.motorsphere.service.IEvento;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventoImpl implements IEvento {

    @Autowired
    private EventoDAO eventoDAO;
    @Autowired
    private OfertanteDAO ofertanteDAO;
    @Autowired
    private FechaDAO fechaDAO;
    @Autowired
    private InsigniaDAO insigniaDAO;
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
    @Transactional
    public EventoDTO insert(CreateEventoDTO createEventoDTO) {
        if (createEventoDTO == null) {
            return null;
        }

        // Obtener el ofertante
        Optional<Ofertante> ofertanteOptional = ofertanteDAO.findById(createEventoDTO.getBidderId());
        if (!ofertanteOptional.isPresent()) {
            throw new EntityNotFoundException("Ofertante no encontrado");
        }
        Ofertante ofertante = ofertanteOptional.get();

        // Guardar insignia
        Insignia insignia = saveInsignia(createEventoDTO.getInsignia());
        insignia = insigniaDAO.save(insignia);

        // Guardar localización
        Localizacion localizacion = saveLocalizacion(createEventoDTO.getLocalization());
        localizacion = localizacionDAO.save(localizacion);

        // Crear el evento sin fechas todavía
        Evento evento = Evento.builder()
                .ofertante(ofertante)
                .nombre(createEventoDTO.getName())
                .descripcion(createEventoDTO.getDescription())
                .fechaAnuncioEvento(new Date())
                .insignia(insignia)
                .etiquetasEvento(new HashSet<>())
                .exigencia(createEventoDTO.getExigency())
                .usuariosInscritosEvento(null)
                .imagen(createEventoDTO.getImage())
                .localizacion(localizacion)
                .puntuacion(createEventoDTO.getScore())
                .build();

        // Guardar el evento primero para obtener su ID
        evento = eventoDAO.save(evento);

        // Guardar las fechas con referencia al evento
        Set<Fecha> fechas = new HashSet<>();
        for (FechasEventoDTO fechaDTO : createEventoDTO.getDates()) {
            Fecha fecha = saveFecha(fechaDTO);
            fecha.setEvento(evento); // Establecer la relación con el evento
            fecha = fechaDAO.save(fecha);
            fechas.add(fecha);
        }

        // Establecer las fechas en el evento
        evento.setFechas(fechas);

        // Guardar el evento nuevamente para actualizar las fechas
        Evento savedEvent = eventoDAO.save(evento);

        return EventoDTO.builder()
                .eventId(savedEvent.getId())
                .bidderId(savedEvent.getOfertante().getId())
                .name(savedEvent.getNombre())
                .dates(savedEvent.getFechas().stream()
                        .map(this::buildFechasParaEventoDTO)
                        .collect(Collectors.toList()))
                .description(savedEvent.getDescripcion())
                .score(savedEvent.getPuntuacion())
                .exigency(savedEvent.getExigencia())
                .announcementDate(savedEvent.getFechaAnuncioEvento())
                .image(savedEvent.getImagen())
                .localization(LocalizacionDTO.builder()
                        .id(savedEvent.getLocalizacion().getId())
                        .municipality(savedEvent.getLocalizacion().getMunicipio())
                        .province(savedEvent.getLocalizacion().getProvincia())
                        .longitude(savedEvent.getLocalizacion().getLongitud())
                        .latitude(savedEvent.getLocalizacion().getLatitud())
                        .build())
                .insigniaId(savedEvent.getInsignia().getId())
                .build();
    }

    public Localizacion saveLocalizacion(LocalizacionDTO localizacionDTO) {
        return Localizacion.builder()
                .municipio(localizacionDTO.getMunicipality())
                .provincia(localizacionDTO.getProvince())
                .pais(localizacionDTO.getCountry())
                .latitud(localizacionDTO.getLatitude())
                .longitud(localizacionDTO.getLongitude())
                .build();
    }

    public Set<Fecha> fechasDTOtoFechas(List<FechasEventoDTO> fechasEventoDTO) {
        Set<Fecha> fechas = new HashSet<>();

        for (FechasEventoDTO fecha : fechasEventoDTO) {
            fechas.add(
                    Fecha.builder()
                            .fechaInicio(fecha.getStartDate())
                            .fechaFinal(fecha.getFinalDate())
                            .plazas(fecha.getPlaces())
                            .plazasDisponibles(fecha.getPlacesAvailable())
                            .precio(fecha.getPrice())
                            .usuariosInscritosFecha(null)
                            .build()
            );
        }
        return fechas;
    }

    public Insignia saveInsignia(InsigniaDTO insigniaDTO) {
        return Insignia.builder()
                .nombre(insigniaDTO.getName())
                .imagen(insigniaDTO.getImage())
                .valor(insigniaDTO.getValue())
                .build();
    }

    public Fecha saveFecha(FechasEventoDTO fechaDTO) {
        return Fecha.builder()
                .fechaInicio(fechaDTO.getStartDate())
                .fechaFinal(fechaDTO.getFinalDate())
                .plazas(fechaDTO.getPlaces())
                .plazasDisponibles(fechaDTO.getPlacesAvailable())
                .precio(fechaDTO.getPrice())
                .usuariosInscritosFecha(null)
                .build();
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
