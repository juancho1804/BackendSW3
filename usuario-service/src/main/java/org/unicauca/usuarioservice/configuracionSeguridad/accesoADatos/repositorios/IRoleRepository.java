package org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.ERole;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.Role;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

