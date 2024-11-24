package org.unicauca.usuarioservice.configuracionSeguridad.fachada.services;

import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.request.LoginRequestDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.request.SignupRequestDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.response.JwtResponseDTO;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.response.MessageResponseDTO;

public interface AuthInt {
    JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest);
    MessageResponseDTO registerUser(SignupRequestDTO signUpRequest);
}
