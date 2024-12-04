package com.unicauca.microservice_asginatura.service;

import com.unicauca.microservice_asginatura.entities.AsignaturaCompetencia;
import com.unicauca.microservice_asginatura.exceptions.ResourceNotFoundException;
import com.unicauca.microservice_asginatura.persisence.AsignaturaCompetenciaRepository;
import com.unicauca.microservice_asginatura.persisence.PeriodoRepository;
import com.unicauca.microservice_asginatura.entities.Periodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class PeriodoImplService implements IPeriodoService{

    @Autowired
    private PeriodoRepository periodoRepository;

    @Autowired
    private AsignaturaCompetenciaRepository asignaturaCompetenciaRepository;

    @Override
    public List<Periodo> findAll() {
        return periodoRepository.findAll();
    }

    @Override
    public Periodo findById(Long id) throws ResourceNotFoundException {
        return periodoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());

    }

    @Override
    public Periodo create(Long asignaturaCompetenciaId, LocalDate periodoIni, LocalDate periodoFin)  throws ResourceNotFoundException{
        // Validar que la AsignaturaCompetencia exista
        AsignaturaCompetencia asignaturaCompetencia = asignaturaCompetenciaRepository.findById(asignaturaCompetenciaId)
                .orElseThrow(() -> new ResourceNotFoundException());

        // Crear el periodo
        Periodo periodo = Periodo.builder()
                .asignaturaCompetencia(asignaturaCompetencia)
                .periodoIni(periodoIni)
                .periodoFin(periodoFin)
                .build();

        // Guardar el periodo
        return periodoRepository.save(periodo);
    }

    @Override
    public Periodo update(Long id, LocalDate periodoIni, LocalDate periodoFin) throws ResourceNotFoundException  {
        Periodo periodo = periodoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());

        periodo.setPeriodoIni(periodoIni);
        periodo.setPeriodoFin(periodoFin);

        return periodoRepository.save(periodo);
    }

    @Override
    public void deleteById(Long id)  throws ResourceNotFoundException{
        Periodo periodo = periodoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        periodoRepository.delete(periodo);
    }
}
