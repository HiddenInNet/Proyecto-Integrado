package dgg.motorsphere.service.impl;

import dgg.motorsphere.service.IInsignia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsigniaImpl implements IInsignia {
/*
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

 */
}
