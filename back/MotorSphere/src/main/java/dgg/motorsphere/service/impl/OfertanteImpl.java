package dgg.motorsphere.service.impl;

import dgg.motorsphere.api.dto.ofertante.CheckOfertanteDTO;
import dgg.motorsphere.api.dto.ofertante.OfertanteDTO;
import dgg.motorsphere.api.dto.ofertante.OfertanteUsuarioDTO;
import dgg.motorsphere.model.dao.*;
import dgg.motorsphere.model.entity.Evento;
import dgg.motorsphere.model.entity.Fecha;
import dgg.motorsphere.model.entity.Ofertante;
import dgg.motorsphere.model.entity.Usuario;
import dgg.motorsphere.model.entity.relations.EtiquetaEvento;
import dgg.motorsphere.model.entity.relations.UsuarioInscritoEvento;
import dgg.motorsphere.service.IOfertante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OfertanteImpl implements IOfertante {
    static final Logger log = LoggerFactory.getLogger(OfertanteImpl.class);

    @Autowired
    private OfertanteDAO ofertanteDAO;
    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private EventoDAO eventoDAO;
    @Autowired
    private LocalizacionDAO localizacionDAO;
    @Autowired
    private FechaDAO fechaDAO;
    @Autowired
    private InsigniaDAO insigniaDAO;
    @Autowired
    private EtiquetaEventoDAO etiquetaEventoDAO;
    @Autowired
    private UsuarioInscritoEventoDAO usuarioInscritoEventoDAO;

    @Override
    public List<OfertanteDTO> getAll() {

        List<Ofertante> ofertantes = (List<Ofertante>) ofertanteDAO.findAll();
        List<OfertanteDTO> ofertantesDTOS = new ArrayList<>();

        for (Ofertante ofertante: ofertantes) {
            ofertantesDTOS.add(buildOfertanteDTO(ofertante));
        }

        return ofertantesDTOS;
    }

    @Transactional(readOnly = true)
    @Override
    public OfertanteUsuarioDTO getById(Long id) {
        if (id != null) {
            Ofertante ofertante = ofertanteDAO.findById(id).orElse(null);
            if (ofertante != null) {
                return OfertanteUsuarioDTO.builder()
                        .bidderId(ofertante.getId())
                        .email(ofertante.getUsuario().getEmail())
                        .username(ofertante.getUsuario().getUser().getUsername())
                        .name(ofertante.getUsuario().getNombre())
                        .lastName(ofertante.getUsuario().getApellidos())
                        .birthDate(ofertante.getUsuario().getFechaNacimiento())
                        .phone(ofertante.getUsuario().getTelefono())
                        .profileDate(ofertante.getUsuario().getFechaCreacionPerfil())
                        .biography(ofertante.getUsuario().getBiografia())
                        .profileImage(ofertante.getUsuario().getImagenPerfil())

                        .userId(ofertante.getUsuario().getId())
                        .creationDate(ofertante.getFechaCreacion())
                        .checker(ofertante.isChecker())
                        .build();
            }
            return null;
        }
        return null;
    }

    @Override
    public OfertanteDTO getByUsuarioId(Long id) {

        if (id != null) {
            Ofertante ofertante = ofertanteDAO.findByUsuarioId(id).orElse(null);
            if (ofertante != null) {
                return OfertanteDTO.builder()
                        .id(ofertante.getId())
                        .userId(ofertante.getUsuario().getId())
                        .creationDate(ofertante.getFechaCreacion())
                        .checker(ofertante.isChecker())
                        .build();
            }
            return null;
        }
        return null;
    }

    @Override
    public Boolean remove(Long id) {
        if(id != null){
            ofertanteDAO.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public String removeByUserId(Long userId) {
        if (userId != null) {
            Ofertante ofertante = ofertanteDAO.findByUsuarioId(userId).orElse(null);

            if (ofertante != null) {
                // Eliminar eventos relacionados y sus dependencias
                for (Evento evento : ofertante.getEventos()) {
                    // Eliminar usuarios inscritos en el evento
                    for (UsuarioInscritoEvento usuarioInscritoEvento : evento.getUsuariosInscritosEvento()) {
                        usuarioInscritoEventoDAO.deleteById(usuarioInscritoEvento.getId());
                    }
                    // Eliminar etiquetas del evento
                    for (EtiquetaEvento etiquetaEvento : evento.getEtiquetasEvento()) {
                        etiquetaEventoDAO.deleteById(etiquetaEvento.getId());
                    }
                    // Eliminar fechas del evento
                    for (Fecha fecha : evento.getFechas()) {
                        fechaDAO.deleteById(fecha.getId());
                    }
                    // Eliminar localización del evento
                    if (evento.getLocalizacion() != null) {
                        localizacionDAO.deleteById(evento.getLocalizacion().getId());
                    }
                    // Eliminar insignia del evento
                    if (evento.getInsignia() != null) {
                        insigniaDAO.deleteById(evento.getInsignia().getId());
                    }
                    // Eliminar el evento
                    eventoDAO.deleteById(evento.getId());
                }

                // Finalmente, eliminar el ofertante
                ofertanteDAO.deleteById(ofertante.getId());

                return "Se ha borrado correctamente";
            }
            return "No existe un usuario con ese id " + userId;
        }
        return "El id obtenido es nulo";
    }

    @Transactional
    public OfertanteDTO insert(OfertanteDTO ofertanteDTO) {

        if (ofertanteDTO != null) {
            Usuario usuario = usuarioDAO.findById(ofertanteDTO.getUserId()).orElse(null);
            if (usuario != null) {
                Ofertante ofertante = Ofertante.builder()
                        .usuario(usuario)
                        .fechaCreacion(new Date())
                        .checker(ofertanteDTO.isChecker())
                        .build();
                Ofertante newOfertante = ofertanteDAO.save(ofertante);

                return buildOfertanteDTO(newOfertante);
            }
            return  null; // No hay usuario para el ofertante
        }
        return null; // El ofertanteDTO es nulo
    }

    public OfertanteDTO setChecker(CheckOfertanteDTO checkOfertanteDTO) {

        if (checkOfertanteDTO.getBidderId() != null) {
            Optional<Ofertante> ofertante = ofertanteDAO.findById(checkOfertanteDTO.getBidderId());

            if (ofertante.isPresent()) {
                ofertante.get().setChecker(checkOfertanteDTO.isChecker());

                return OfertanteDTO.builder()
                        .id(ofertante.get().getId())
                        .creationDate(ofertante.get().getFechaCreacion())
                        .userId(ofertante.get().getUsuario().getId())
                        .checker(ofertante.get().isChecker())
                        .build();
            }

        }
        return null;
    }

    @Override
    @Transactional
    public OfertanteDTO update(OfertanteDTO ofertanteDTO) {

        Optional<Ofertante> optionalOfertante = ofertanteDAO.findById(ofertanteDTO.getId());

        if (optionalOfertante.isPresent()) {
            Ofertante ofertante = optionalOfertante.get();

            ofertante.setFechaCreacion(ofertanteDTO.getCreationDate());
            ofertante.setChecker(ofertanteDTO.isChecker());

            Ofertante ofertante1 = ofertanteDAO.save(ofertante);

            return buildOfertanteDTO(ofertante1);
        } else {
            return null;
        }
    }

    public OfertanteDTO buildOfertanteDTO(Ofertante ofertante){
        if (ofertante != null) {
            return OfertanteDTO.builder()
                    .id(ofertante.getId())
                    .userId(ofertante.getUsuario().getId())
                    .creationDate(ofertante.getFechaCreacion())
                    .checker(ofertante.isChecker())
                    .build();
        }
        return null;
    }
}
