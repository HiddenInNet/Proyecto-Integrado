package dgg.motorsphere.api.controller;

import dgg.motorsphere.api.dto.comentario.ComentarioDTO;
import dgg.motorsphere.service.IComentario;
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
@RequestMapping("/api/v0/comentario")
public class ComentarioController {

    static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IComentario comentarioService;

    @PostMapping("update")
    public ResponseEntity<?> create(@RequestBody ComentarioDTO comentarioDTO){
        ComentarioDTO response = comentarioService.update(comentarioDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
