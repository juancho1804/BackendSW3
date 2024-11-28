package org.unicauca.docenteservice.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unicauca.docenteservice.capaAccesoADatos.models.Docente;

public interface IDocenteRepository extends JpaRepository<Docente, Integer> {
}
