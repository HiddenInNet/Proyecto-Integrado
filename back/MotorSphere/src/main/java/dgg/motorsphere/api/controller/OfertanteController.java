package dgg.motorsphere.api.controller;

import dgg.motorsphere.api.dto.ofertante.CheckOfertanteDTO;
import dgg.motorsphere.api.dto.ofertante.OfertanteDTO;
import dgg.motorsphere.api.dto.ofertante.OfertanteUsuarioDTO;
import dgg.motorsphere.service.IOfertante;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/ofertante")
public class OfertanteController {

    static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IOfertante ofertanteService;

    @GetMapping("getByUserId/{id}")
    public ResponseEntity<?> getBidderByUserId(@PathVariable Long id) {
        OfertanteDTO response = ofertanteService.getByUsuarioId(id);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getBidderByEventId(@PathVariable Long id){
        OfertanteUsuarioDTO response = ofertanteService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("add")
    public ResponseEntity<?> create(@RequestBody OfertanteDTO ofertanteDTO){
        OfertanteDTO response = ofertanteService.insert(ofertanteDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/removeByUserId/{id}")
    public ResponseEntity<?> removeBidderByUserId(@PathVariable Long id){
        String response = ofertanteService.removeByUserId(id);
        Map<String, String> res = new HashMap<>();
        res.put("response", response);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/checker")
    public ResponseEntity<?> checker(@RequestBody CheckOfertanteDTO checkOfertanteDTO){
        OfertanteDTO response = ofertanteService.setChecker(checkOfertanteDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
