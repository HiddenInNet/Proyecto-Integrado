package com.ms.motorsphere_api.services.impl;

import com.ms.motorsphere_api.model.dao.EventDAO;
import com.ms.motorsphere_api.model.dao.InsigniaDAO;
import com.ms.motorsphere_api.model.dto.InsigniaDTO;
import com.ms.motorsphere_api.model.entity.Insignia;
import com.ms.motorsphere_api.services.IInsignia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsigniaImpl implements IInsignia {

    @Autowired
    private InsigniaDAO insigniaDAO;
    @Autowired
    private EventDAO eventDAO;

    @Override
    public List<Insignia> findAll() {
        return (List<Insignia>) insigniaDAO.findAll();
    }

    @Override
    public Insignia findById(Long id) {
        return insigniaDAO.findById(id).orElse(null);
    }

    @Override
    public Boolean remove(Long id) {
        if(id != null) {
            insigniaDAO.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Insignia save(InsigniaDTO insigniaDTO) {
        if (insigniaDTO != null){
            Insignia insignia = Insignia.builder()
                    .id(insigniaDTO.getInsigniaId())
                    .event(eventDAO.findById(insigniaDTO.getInsigniaId()).orElse(null))
                    .value(insigniaDTO.getValue())
                    .name(insigniaDTO.getName())
                    .image(insigniaDTO.getImage())
                    .build();
            return insigniaDAO.save(insignia);
        }
        return null;
    }
}
