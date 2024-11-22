package com.unicauca.rest.service;

import com.unicauca.rest.entities.Criterio;
import com.unicauca.rest.exceptions.CriterioDomainException;
import com.unicauca.rest.exceptions.CriterioError;
import com.unicauca.rest.exceptions.ResourceNotFoundException;
import com.unicauca.rest.persisence.CriterioRepository;
import com.unicauca.rest.persisence.RubricaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriterioImplService implements  ICriterioService{

    @Autowired
    private CriterioRepository criterioRepository;

    @Autowired
    private RubricaRepository rubricaRepository;

    @Override
    public List<Criterio> findAll() {
        return (List<Criterio>) criterioRepository.findAll();
    }

    @Override
    public Criterio findById(Long id) throws ResourceNotFoundException {
        Criterio criterio = criterioRepository.findById(id).orElse(null);
        if (criterio == null) {
            throw new ResourceNotFoundException();

        }
        return criterio;
    }
    @Transactional
    @Override
    public Criterio create(Criterio criterio) throws CriterioDomainException {
        System.out.println("esta entrando al metodo crear criterio");
        System.out.println("imprimiend el proudcto del id,creando...");

        System.out.println(criterio.getCriterioId());
        List<CriterioError> errors = validateDomain(criterio);

        if (!errors.isEmpty()) {
            throw new CriterioDomainException(errors);
        }

        return criterioRepository.save(criterio);
    }
    @Transactional
    @Override
    public Criterio update(Long id, Criterio criterio) throws CriterioDomainException, ResourceNotFoundException {
        Criterio cri = this.findById(id);
        if (cri == null) {
            throw new ResourceNotFoundException();
        }

        List<CriterioError> errors = validateDomain(cri);

        if (!errors.isEmpty()) {
            throw new CriterioDomainException(errors);
        }

        // cate.setName(cate.getName());

        cri.setCriterioDescripcion(criterio.getCriterioDescripcion());
        cri.setRubrica(criterio.getRubrica());
        cri.setPonderacion(criterio.getPonderacion());
        cri.setNivel1(criterio.getNivel1());
        cri.setNivel2(criterio.getNivel2());
        cri.setNivel3(criterio.getNivel3());
        cri.setNivel4(criterio.getNivel4());

        return criterioRepository.save(cri);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Criterio cri = this.findById(id);
        if (cri == null) {
            throw new ResourceNotFoundException();
        }

        criterioRepository.deleteById(id);
    }



    /**
     * Aplica validaciones o reglas del dominio para un producto. Antes de ser
     * agregado o modificado.
     *
     * @param product producto a validad
     * @return lista de errores de validación
     */

    private List<CriterioError> validateDomain(Criterio criterio) {
        List<CriterioError> errors = new ArrayList<>();

        if (criterio.getCriterioDescripcion() == null || criterio.getCriterioDescripcion().isBlank()) {
            errors.add(new CriterioError(EnumErrorCodes.EMPTY_FIELD, "name", "La descripcion del criterio es obligatorio"));
        }

        return errors;

    }
}
