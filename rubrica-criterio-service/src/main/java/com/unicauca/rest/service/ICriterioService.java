package com.unicauca.rest.service;

import com.unicauca.rest.entities.Criterio;
import com.unicauca.rest.exceptions.CriterioDomainException;
import com.unicauca.rest.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICriterioService {
    public List<Criterio> findAll();

    public Criterio findById(Long id) throws ResourceNotFoundException;

    public Criterio create(Criterio criterio) throws CriterioDomainException;

    public Criterio update(Long id, Criterio criterio) throws CriterioDomainException, ResourceNotFoundException;

    public void deleteById(Long id) throws ResourceNotFoundException;

}
