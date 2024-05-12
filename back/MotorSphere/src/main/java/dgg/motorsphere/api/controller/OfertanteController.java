package dgg.motorsphere.api.controller;

import dgg.motorsphere.api.dto.ofertante.OfertanteDTO;
import dgg.motorsphere.service.IOfertante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/ofertante")
public class OfertanteController {

    static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IOfertante ofertanteService;

    @PostMapping("add")
    public ResponseEntity<?> create(@RequestBody OfertanteDTO ofertanteDTO){
        OfertanteDTO response = ofertanteService.insert(ofertanteDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
