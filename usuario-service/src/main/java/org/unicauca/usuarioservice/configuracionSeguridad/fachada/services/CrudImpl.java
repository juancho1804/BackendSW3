package org.unicauca.usuarioservice.configuracionSeguridad.fachada.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.User;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.repositorios.IUserRepository;

import java.util.Optional;

@Service
public class CrudImpl {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private IUserRepository userRepository;

    public Optional<User> obtenerUsuario(String email) {
        return this.userRepository.findByEmail(email);
    }
    public Optional<User>obtenerUsuarioPorIdentificacion(String identificacion) {
        return this.userRepository.findByIdentificacion(identificacion);
    }

    public User update(String identificacion, User user) {

        Optional<User> optionalUser = userRepository.findByIdentificacion(identificacion);
        if (optionalUser.isPresent()) {
            User usuario = optionalUser.get();
            usuario.setNombres(user.getNombres());
            usuario.setApellidos(user.getApellidos());
            usuario.setEmail(user.getEmail());
            usuario.setContrasenia(encoder.encode(user.getContrasenia()));
            userRepository.save(usuario);
            return usuario;
        }else{
            throw new RuntimeException("No se encontro el usuario");
        }

    }
}
