package com.ms.motorsphere_api.services;

import com.ms.motorsphere_api.model.dto.PublicationDTO;
import com.ms.motorsphere_api.model.entity.Publication;

import java.util.List;

public interface IPublication {

    List<Publication> findAll();
    Publication findById(Long id);
    Publication save(PublicationDTO publicationDTO);
    Boolean remove(Long id);

}
