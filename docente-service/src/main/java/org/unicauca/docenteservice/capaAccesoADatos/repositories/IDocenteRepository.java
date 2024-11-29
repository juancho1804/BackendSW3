package org.unicauca.docenteservice.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unicauca.docenteservice.capaAccesoADatos.models.Docente;

import java.util.Optional;

public interface IDocenteRepository extends JpaRepository<Docente, Integer> {
    public Optional<Docente> findByIdentificacion(String identificacion);
}
