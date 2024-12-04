package com.unicauca.microservice_asginatura.persisence;


import org.springframework.data.jpa.repository.JpaRepository;
import com.unicauca.microservice_asginatura.entities.Periodo;

public interface PeriodoRepository  extends JpaRepository<Periodo, Long> {
}
