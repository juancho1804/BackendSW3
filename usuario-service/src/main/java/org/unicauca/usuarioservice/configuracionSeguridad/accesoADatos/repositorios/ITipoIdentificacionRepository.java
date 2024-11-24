package org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.ETipoIdentificacion;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.TipoIdentificacion;

import java.util.Optional;

public interface ITipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Long> {
    Optional<TipoIdentificacion> findByName(ETipoIdentificacion name);

}
