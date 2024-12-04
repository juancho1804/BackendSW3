package com.unicauca.microservice_asginatura.persisence;

import com.unicauca.microservice_asginatura.entities.AsignaturaCompetencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaCompetenciaRepository extends JpaRepository<AsignaturaCompetencia, Long> {
}
