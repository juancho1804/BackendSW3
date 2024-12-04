package com.unicauca.microservice_asginatura.service;

import com.unicauca.microservice_asginatura.entities.Asignatura;
import com.unicauca.microservice_asginatura.exceptions.AsignaturaDomainException;
import com.unicauca.microservice_asginatura.exceptions.ResourceNotFoundException;
import com.unicauca.microservice_asginatura.entities.Periodo;

import java.time.LocalDate;
import java.util.List;

public interface IPeriodoService {
    public List<Periodo> findAll();

    public Periodo findById(Long id) throws ResourceNotFoundException;

    public Periodo create(Long asignaturaCompetenciaId, LocalDate periodoIni, LocalDate periodoFin) throws ResourceNotFoundException ;

    public Periodo update(Long id, LocalDate periodoIni, LocalDate periodoFin) throws ResourceNotFoundException;

    public void deleteById(Long id) throws ResourceNotFoundException;

}
