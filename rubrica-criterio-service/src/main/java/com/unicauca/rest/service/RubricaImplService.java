package com.unicauca.rest.service;

import com.unicauca.rest.entities.Criterio;
import com.unicauca.rest.entities.Rubrica;
import com.unicauca.rest.exceptions.ResourceNotFoundException;
import com.unicauca.rest.exceptions.RubricaDomainException;
import com.unicauca.rest.exceptions.RubricaError;
import com.unicauca.rest.persisence.RubricaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RubricaImplService implements  IRubricaService{
    //inyectando

    @Autowired
    private RubricaRepository RubricaRepository;

    @Autowired
    private ICriterioService CriterioService;



    @Transactional
    @Override
    public List<Rubrica> findAll() {
        return (List<Rubrica>) RubricaRepository.findAll();
    }

    @Override
    public Rubrica findById(Long id) throws ResourceNotFoundException {
        Rubrica prod = RubricaRepository.findById(id).orElse(null);
        if (prod == null) {
            throw new ResourceNotFoundException();

        }
        return prod;
    }

    @Transactional
    @Override
    public Rubrica create(Rubrica rubrica) throws RubricaDomainException {
        System.out.println("Entrando al método crear rúbrica...");

        // Validación de dominio
        List<RubricaError> errors = validateDomain(rubrica);
        if (!errors.isEmpty()) {
            throw new RubricaDomainException(errors);
        }

        // Asignar la referencia de la rúbrica a cada criterio
        if (rubrica.getCriterioList() != null) {
            for (Criterio criterio : rubrica.getCriterioList()) {
                criterio.setRubrica(rubrica); // Establecer la relación
            }
        }

        return RubricaRepository.save(rubrica);
    }


    @Override
    public Rubrica findRubricaByRubricaNombre(String name) throws ResourceNotFoundException {
        Rubrica rubrica = RubricaRepository.findRubricaByRubricaNombre(name);
        if (rubrica == null) {

            throw new ResourceNotFoundException();


        }
        return RubricaRepository.findRubricaByRubricaNombre(name);
    }

    @Transactional
    @Override
    public Rubrica update(Long id, Rubrica rubrica) throws RubricaDomainException, ResourceNotFoundException {
        Rubrica rubri = this.findById(id);
        if (rubri == null) {
            throw new ResourceNotFoundException();
        }

        List<RubricaError> errors = validateDomain(rubrica);

        if (!errors.isEmpty()) {
            throw new RubricaDomainException(errors);
        }

        rubri.setRubricaNombre(rubrica.getRubricaNombre());

        rubri.setCriterioList(rubrica.getCriterioList());

        return RubricaRepository.save(rubri);
    }
    @Transactional
    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Rubrica rubrica = this.findById(id);
        if (rubrica == null) {
            throw new ResourceNotFoundException();
        }
        RubricaRepository.deleteById(id);
    }




    /**
     * Aplica validaciones o reglas del dominio para un producto. Antes de ser
     * agregado o modificado.
     *
     * @param rubrica rubrica a validad
     * @return lista de errores de validación
     */

    private List<RubricaError> validateDomain(Rubrica rubrica) {
        List<RubricaError> errors = new ArrayList<>();

        if (rubrica.getRubricaNombre() == null || rubrica.getRubricaNombre().isBlank()) {
            errors.add(new RubricaError(EnumErrorCodes.EMPTY_FIELD, "name", "El nombre de la rubrica es obligatorio"));
        }
        return errors;

    }

}
