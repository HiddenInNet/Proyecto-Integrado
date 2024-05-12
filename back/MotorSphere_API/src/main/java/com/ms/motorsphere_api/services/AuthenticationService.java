package com.ms.motorsphere_api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ms.motorsphere_api.security.config.JwtService;
import com.ms.motorsphere_api.controller.requests.AuthenticationRequest;
import com.ms.motorsphere_api.controller.requests.RegisterRequest;
import com.ms.motorsphere_api.controller.response.AuthenticationResponse;
import com.ms.motorsphere_api.model.entity.Usuario;
import com.ms.motorsphere_api.model.dao.UsuarioDAO;

@Service
public class AuthenticationService {

  static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

  private final UsuarioDAO usuarioRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  public AuthenticationService(
      UsuarioDAO usuarioRepository,
      PasswordEncoder passwordEncoder,
      JwtService jwtService,
      AuthenticationManager authenticationManager) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  public AuthenticationResponse register(RegisterRequest request) {
    logger.info("Registering user: {}", request);
    Usuario usuario = new Usuario();
    usuario.setNombre(request.getFirstName());
    usuario.setUserName(request.getFirstName() + "_" + request.getLastName());
    usuario.setApellidos(request.getLastName());
    usuario.setEmail(request.getEmail());
    usuario.setPassword(passwordEncoder.encode(request.getPassword()));
    usuario.setRole("USER");
    usuario.setActivo(true);
    Usuario saved = usuarioRepository.save(usuario);
    String jwtToken = jwtService.generateToken(saved);
    return new AuthenticationResponse(jwtToken);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    logger.info("Authenticating user: {}", request);
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    logger.info("User authenticated: {}", request);
    Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
    logger.info("Authenticating user: {}", usuario);
    String jwtToken = jwtService.generateToken(usuario);
    logger.info("JWT Token: {}", jwtToken);
    return new AuthenticationResponse(jwtToken);
  }
}
