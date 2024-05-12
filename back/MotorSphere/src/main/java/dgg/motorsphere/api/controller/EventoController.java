package dgg.motorsphere.api.controller;

import dgg.motorsphere.api.dto.evento.EventoDTO;
import dgg.motorsphere.service.IEvento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0/evento")
public class EventoController {

    static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IEvento eventoService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<EventoDTO> response = eventoService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
