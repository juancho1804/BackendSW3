package org.unicauca.competenciasrapservice.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Rap;

import java.util.List;

public interface IRapRepositorio extends JpaRepository<Rap,Integer> {
    List<Rap>findByCompetenciaId(Integer competenciaId);
}
