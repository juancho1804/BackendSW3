package com.unicauca.rest.service;

import com.unicauca.rest.entities.Rubrica;
import com.unicauca.rest.exceptions.ResourceNotFoundException;
import com.unicauca.rest.exceptions.RubricaDomainException;

import java.util.List;

public interface IRubricaService {
    public List<Rubrica> findAll();

    public Rubrica findById(Long id) throws ResourceNotFoundException;

    public Rubrica create(Rubrica rubrica) throws RubricaDomainException;

    public Rubrica update(Long id, Rubrica rubrica) throws RubricaDomainException, ResourceNotFoundException;

    public void deleteById(Long id) throws ResourceNotFoundException;

    public Rubrica findRubricaByRubricaNombre(String name) throws ResourceNotFoundException;
}
