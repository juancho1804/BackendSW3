package com.unicauca.microservice_asginatura.service;

import com.unicauca.microservice_asginatura.dtos.AsignaturaDTO;
import com.unicauca.microservice_asginatura.entities.Asignatura;
import com.unicauca.microservice_asginatura.exceptions.AsignaturaDomainException;
import com.unicauca.microservice_asginatura.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IAsignaturaService {
    public List<Asignatura> findAll();

    public Asignatura findById(Long id) throws ResourceNotFoundException;

    public Asignatura create(Asignatura asignatura) throws AsignaturaDomainException;

    public Asignatura update(Long id, Asignatura asignatura) throws AsignaturaDomainException, ResourceNotFoundException;

    public void deleteById(Long id) throws ResourceNotFoundException;

    public Asignatura  cambiarEstadoAsignatura(Long id,Boolean nuevoEstado) throws ResourceNotFoundException;
    public Asignatura crearAsignaturaConCompetencias(AsignaturaDTO asignaturaDTO);
    public AsignaturaDTO obtenerAsignaturaConCompetencias(Long id);
}
