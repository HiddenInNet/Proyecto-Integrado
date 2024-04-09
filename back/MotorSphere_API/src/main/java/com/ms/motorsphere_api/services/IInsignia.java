package com.ms.motorsphere_api.services;

import com.ms.motorsphere_api.model.dto.InsigniaDTO;
import com.ms.motorsphere_api.model.entity.Insignia;

import java.util.List;

public interface IInsignia {

    List<Insignia> findAll();
    Insignia findById(Long id);
    Boolean remove(Long id);
    Insignia save(InsigniaDTO insigniaDTO);
}
