package org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User>findByIdentificacion(String identificacion);
}
