package dgg.motorsphere.service.impl;

import dgg.motorsphere.api.dto.ofertante.OfertanteDTO;
import dgg.motorsphere.model.dao.OfertanteDAO;
import dgg.motorsphere.model.dao.UsuarioDAO;
import dgg.motorsphere.model.entity.Ofertante;
import dgg.motorsphere.model.entity.Usuario;
import dgg.motorsphere.service.IOfertante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfertanteImpl implements IOfertante {
    static final Logger log = LoggerFactory.getLogger(OfertanteImpl.class);

    @Autowired
    private OfertanteDAO ofertanteDAO;
    @Autowired
    private UsuarioDAO usuarioDAO;

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
    public OfertanteDTO getById(Long id) {
        Ofertante ofertante = ofertanteDAO.findById(id).orElse(null);
        return buildOfertanteDTO(ofertante);
    }

    @Override
    public OfertanteDTO getByUsuarioId(Long id) {

        if (id != null) {
            Ofertante ofertante = ofertanteDAO.findByUsuarioId(id);
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

    @Transactional
    public OfertanteDTO insert(OfertanteDTO ofertanteDTO) {

        if (ofertanteDTO != null) {
            Usuario usuario = usuarioDAO.findById(ofertanteDTO.getUserId()).orElse(null);
            if (usuario != null) {
                Ofertante ofertante = Ofertante.builder()
                        .usuario(usuario)
                        .fechaCreacion(ofertanteDTO.getCreationDate())
                        .checker(ofertanteDTO.isChecker())
                        .build();
                Ofertante newOfertante = ofertanteDAO.save(ofertante);

                return buildOfertanteDTO(newOfertante);
            }
            return  null; // No hay usuario para el ofertante
        }
        return null; // El ofertanteDTO es nulo
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
