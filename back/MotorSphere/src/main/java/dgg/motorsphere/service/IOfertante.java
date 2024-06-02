package dgg.motorsphere.service;

import dgg.motorsphere.api.dto.ofertante.CheckOfertanteDTO;
import dgg.motorsphere.api.dto.ofertante.OfertanteDTO;
import dgg.motorsphere.api.dto.ofertante.OfertanteUsuarioDTO;

import java.util.List;

public interface IOfertante {

    List<OfertanteDTO> getAll();
    OfertanteUsuarioDTO getById(Long id);
    OfertanteDTO getByUsuarioId(Long id);

    OfertanteDTO setChecker(CheckOfertanteDTO checkOfertanteDTO);
    Boolean remove(Long id);
    String removeByUserId(Long id);
    OfertanteDTO insert(OfertanteDTO ofertanteDTO);

    OfertanteDTO update(OfertanteDTO ofertanteDTO);
}
