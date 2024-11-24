package org.unicauca.usuarioservice.controladores;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.request.LoginRequestDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.request.SignupRequestDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.response.JwtResponseDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.response.MessageResponseDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.services.AuthImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthImpl objAuthImpl;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        System.out.println("entrando a generar token");
        JwtResponseDTO token=this.objAuthImpl.authenticateUser(loginRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest) {
        System.out.println("entrando a crear usuario");
        MessageResponseDTO mensajeRespuesta=this.objAuthImpl.registerUser(signUpRequest);
        return ResponseEntity.ok(mensajeRespuesta);
    }
}
