package org.unicauca.usuarioservice.configuracionSeguridad.fachada.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.User;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.repositorios.IUserRepository;

import java.util.Optional;

@Service
public class CrudImpl {
    @Autowired
    private IUserRepository userRepository;

    public Optional<User> obtenerUsuario(String email) {
        return this.userRepository.findByEmail(email);
    }
    public Optional<User>obtenerUsuarioPorIdentificacion(String identificacion) {
        return this.userRepository.findByIdentificacion(identificacion);
    }
}
