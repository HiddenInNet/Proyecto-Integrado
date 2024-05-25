package dgg.motorsphere.service;

import dgg.motorsphere.api.dto.ofertante.OfertanteDTO;

import java.util.List;

public interface IOfertante {

    List<OfertanteDTO> getAll();
    OfertanteDTO getById(Long id);
    OfertanteDTO getByUsuarioId(Long id);

    Boolean remove(Long id);
    OfertanteDTO insert(OfertanteDTO ofertanteDTO);

    OfertanteDTO update(OfertanteDTO ofertanteDTO);
}
