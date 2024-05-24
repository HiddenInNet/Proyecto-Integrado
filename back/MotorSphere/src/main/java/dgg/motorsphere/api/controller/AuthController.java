package dgg.motorsphere.api.controller;

import dgg.motorsphere.api.dto.login.LoginResponseDTO;
import dgg.motorsphere.api.dto.login.RegisterDTO;
import dgg.motorsphere.api.dto.login.UserDTO;
import dgg.motorsphere.service.JwtTokenService;
import dgg.motorsphere.service.impl.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("/api/v0/auth")
public class AuthController {

    static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    private final JwtTokenService jwtTokenService;
    private final LoginService loginService;

    public AuthController(JwtTokenService jwtTokenService, LoginService loginService) {
        this.jwtTokenService = jwtTokenService;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {

        Optional<Long> userId = loginService.login(userDTO);
        log.info("Usuario encontrado: "+ userId);

        if (userId.isPresent()){
            String token = jwtTokenService.generateToken(Math.toIntExact(userId.get()), "USER");
            LoginResponseDTO response = LoginResponseDTO.builder()
                    .jwt(token)
                    .userId(userId.orElse(0L))
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        log.info("Dentro de register");

        String response = loginService.register(registerDTO);

        Map<String, String> res = new HashMap<>();
        res.put("server_response", response);

        log.info("Informacion " + response);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
