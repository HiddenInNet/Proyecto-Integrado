package dgg.motorsphere.api.controller;

import dgg.motorsphere.api.dto.usuario.UsuarioDTO;
import dgg.motorsphere.api.dto.usuario.UsuarioInsertDTO;
import dgg.motorsphere.api.dto.usuario.UsuarioUpdateDTO;
import dgg.motorsphere.messages.ServerERROR;
import dgg.motorsphere.service.IUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dgg.motorsphere.messages.ServerMSG;


@RestController
@RequestMapping("/api/v0/usuario")
public class UsuarioController {
    static final Logger log = LoggerFactory.getLogger(UsuarioController.class);


    @Autowired
    private IUsuario usuarioService;

    @PostMapping("add")
    public ResponseEntity<?> create(@RequestBody UsuarioInsertDTO usuarioInsertDTO){
        log.info("Usuario");
        UsuarioDTO response = usuarioService.insert(usuarioInsertDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody UsuarioUpdateDTO usuarioUpdateDTO){
        UsuarioDTO response = usuarioService.update(usuarioUpdateDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        try {
            boolean bol = usuarioService.remove(id);
            response.put("response", bol ? ServerMSG.RMV_OK : ServerERROR.ERROR_001);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException exRv) {
            response.put("response", ServerERROR.ERROR_001);
            response.put("message", exRv.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        UsuarioDTO response = usuarioService.getById(id);
        log.info("response", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllUsers(){
        List<UsuarioDTO> response = usuarioService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    @GetMapping("{id}/publications")
    public ResponseEntity<?> userWithPublications(@PathVariable Long id){
        UserWithPublicationsDTO response = usuarioService.getUserPublications(id);
        return ResponseEntity.ok(response);
    }
    */
}
