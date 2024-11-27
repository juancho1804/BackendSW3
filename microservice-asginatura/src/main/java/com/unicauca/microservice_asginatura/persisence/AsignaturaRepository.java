package com.unicauca.microservice_asginatura.persisence;

import com.unicauca.microservice_asginatura.entities.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
}
