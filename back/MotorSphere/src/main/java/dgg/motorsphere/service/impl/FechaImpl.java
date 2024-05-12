package dgg.motorsphere.service.impl;

import dgg.motorsphere.api.dto.FechasEventoDTO;
import dgg.motorsphere.model.dao.EventoDAO;
import dgg.motorsphere.model.dao.FechaDAO;
import dgg.motorsphere.model.entity.Fecha;
import dgg.motorsphere.service.IFecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FechaImpl implements IFecha {

    @Autowired
    private FechaDAO fechaDAO;
    @Autowired
    private EventoDAO eventoDAO;


}
