package com.unicauca.microservice_asginatura.service;


import com.unicauca.microservice_asginatura.dtos.AsignaturaDTO;
import com.unicauca.microservice_asginatura.dtos.CompetenciaDTO;
import com.unicauca.microservice_asginatura.entities.Asignatura;
import com.unicauca.microservice_asginatura.entities.AsignaturaCompetencia;
import com.unicauca.microservice_asginatura.exceptions.AsignaturaDomainException;
import com.unicauca.microservice_asginatura.exceptions.AsignaturaError;
import com.unicauca.microservice_asginatura.exceptions.ResourceNotFoundException;
import com.unicauca.microservice_asginatura.client.CompetenciaClient;
import com.unicauca.microservice_asginatura.persisence.AsignaturaCompetenciaRepository;
import com.unicauca.microservice_asginatura.persisence.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaImplService implements  IAsignaturaService{

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private CompetenciaClient competenciaClient;
    /*
    @Autowired
    private RubricaRepository rubricaRepository;
    */

    @Autowired
    private AsignaturaCompetenciaRepository asignaturaCompetenciaRepository;
    /*
    @Transactional
    public Asignatura crearAsignaturaConCompetencias(AsignaturaDTO asignaturaDTO) {
        // Validar competencias con el microservicio
        List<CompetenciaDTO> competenciasValidas = competenciaClient.validarCompetencias(asignaturaDTO.getCompetenciasIds());
        if (competenciasValidas.size() != asignaturaDTO.getCompetenciasIds().size()) {
            throw new IllegalArgumentException("Algunas competencias no son válidas.");
        }

        // Crear la asignatura
        Asignatura asignatura = Asignatura.builder()
                .nombre(asignaturaDTO.getNombre())
                .descripcion(asignaturaDTO.getDescripcion())
                .numCredito(asignaturaDTO.getNumCredito())
                .semestreAsignatura(asignaturaDTO.getSemestreAsignatura())
                .estado(asignaturaDTO.getEstado())
                .build();

        // Guardar la asignatura
        Asignatura savedAsignatura = asignaturaRepository.save(asignatura);

        // Crear las relaciones en la tabla intermedia
        List<AsignaturaCompetencia> relaciones = competenciasValidas.stream()
                .map(competencia -> {
                    AsignaturaCompetencia relacion = new AsignaturaCompetencia();
                    relacion.setAsignatura(savedAsignatura);
                    relacion.setCompetenciaId(competencia.getId());
                    return relacion;
                })
                .collect(Collectors.toList());

        asignaturaCompetenciaRepository.saveAll(relaciones);

        return savedAsignatura;
    }*/
    @Transactional
    @Override
    public Asignatura crearAsignaturaConCompetencias(AsignaturaDTO asignaturaDTO) {
        // Validar competencias llamando al microservicio de competencias
        List<CompetenciaDTO> competenciasValidas = competenciaClient.validarCompetencias(asignaturaDTO.getCompetenciasIds());

        // Comprobar si todos los IDs son válidos
        if (competenciasValidas.size() != asignaturaDTO.getCompetenciasIds().size()) {
            throw new IllegalArgumentException("Algunas competencias no son válidas");
        }

        // Crear la entidad Asignatura
        Asignatura asignatura = Asignatura.builder()
                .nombre(asignaturaDTO.getNombre())
                .descripcion(asignaturaDTO.getDescripcion())
                .numCredito(asignaturaDTO.getNumCredito())
                .semestreAsignatura(asignaturaDTO.getSemestreAsignatura())
                .estado(asignaturaDTO.getEstado())
                .build();

        // Guardar la asignatura en la base de datos
        Asignatura savedAsignatura = asignaturaRepository.save(asignatura);

        // Crear las relaciones en la tabla intermedia
        List<AsignaturaCompetencia> relaciones = competenciasValidas.stream()
                .map(competencia -> {
                    AsignaturaCompetencia asignaturaCompetencia = new AsignaturaCompetencia();
                    asignaturaCompetencia.setAsignatura(savedAsignatura);
                    asignaturaCompetencia.setCompetenciaId(competencia.getId());
                    return asignaturaCompetencia;
                })
                .collect(Collectors.toList());

        asignaturaCompetenciaRepository.saveAll(relaciones);

        return savedAsignatura;
    }


    @Override
    public List<Asignatura> findAll() {
        return (List<Asignatura>) asignaturaRepository.findAll();
    }

    @Override
    public Asignatura findById(@PathVariable Long id) throws ResourceNotFoundException {
        System.out.println("imprimiendo id en el metodo encontra asignatura por id"+id);
        Asignatura asignatura = asignaturaRepository.findById(id).orElse(null);
        if (asignatura == null) {
            throw new ResourceNotFoundException();

        }

        return asignatura;
    }
    /*
    @Override
    public AsignaturaDTO obtenerAsignaturaConCompetencias(Long id) {
        // Obtener la asignatura
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        // Obtener los detalles de las competencias desde el otro microservicio
        List<CompetenciaDTO> competencias = competenciaClient.validarCompetencias(asignatura.getCompetenciasIds());

        // Combinar los datos en el DTO de respuesta
        AsignaturaDTO response = new AsignaturaDTO();
        response.setCompetenciasIds(asignatura.getCompetenciasIds());
        response.setNombre(asignatura.getNombre());
        response.setDescripcion(asignatura.getDescripcion());
        response.setNumCredito(asignatura.getNumCredito());
        response.setSemestreAsignatura(asignatura.getSemestreAsignatura());
        response.setEstado(asignatura.getEstado());
        response.setCompetencias(competencias);

        return response;
    }*/

    @Transactional
    @Override
    public Asignatura create(Asignatura asignatura) throws AsignaturaDomainException {
        System.out.println("esta entrando al metodo crear asginatura");
        System.out.println("imprimiend el asginatura del id,creando...");

        System.out.println(asignatura.getAsignaturaId());
        List<AsignaturaError> errors = validateDomain(asignatura);

        if (!errors.isEmpty()) {
            throw new AsignaturaDomainException(errors);
        }

        return asignaturaRepository.save(asignatura);
    }
    @Transactional
    @Override
    public Asignatura update(Long id, Asignatura asignatura) throws AsignaturaDomainException, ResourceNotFoundException {
        Asignatura asig = this.findById(id);
        if (asig == null) {
            throw new ResourceNotFoundException();
        }

        List<AsignaturaError> errors = validateDomain(asig);

        if (!errors.isEmpty()) {
            throw new AsignaturaDomainException(errors);
        }

        // cate.setName(cate.getName());

        asig.setDescripcion(asignatura.getDescripcion());
        asig.setNombre(asignatura.getNombre());
        asig.setNumCredito(asignatura.getNumCredito());
        asig.setEstado(asignatura.getEstado());
        asig.setSemestreAsignatura(asignatura.getSemestreAsignatura());

        return asignaturaRepository.save(asig);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Asignatura asig = this.findById(id);
        if (asig == null) {
            throw new ResourceNotFoundException();
        }

        asignaturaRepository.deleteById(id);
    }

    @Override
    public Asignatura  cambiarEstadoAsignatura(Long id ,Boolean nuevoEstado) throws ResourceNotFoundException {
        System.out.println("imprimiendo id en el metodo cambiar estado asignatura por id"+id);
        Asignatura asignatura = asignaturaRepository.findById(id).orElse(null);
        if (asignatura == null) {
            throw new ResourceNotFoundException();


        }
        asignatura.setEstado(nuevoEstado);

        return  asignaturaRepository.save(asignatura);
    }
    /*
    public Asignatura crearAsignaturaConCompetencias(AsignaturaDTO asignaturaDTO) {
        // Validar competencias llamando al microservicio de competencias
        List<Integer> competenciasIds = asignaturaDTO.getCompetenciasIds();
        List<Competencia> competenciasValidas = competenciaClient.validarCompetencias(competenciasIds);

        if (competenciasValidas.size() != competenciasIds.size()) {
            throw new IllegalArgumentException("Algunas competencias no son válidas");
        }

        // Crear la asignatura
        Asignatura asignatura = Asignatura.builder()
                .nombre(asignaturaDTO.getNombre())
                .descripcion(asignaturaDTO.getDescripcion())
                .numCredito(asignaturaDTO.getNumCredito())
                .semestreAsignatura(asignaturaDTO.getSemestreAsignatura())
                .estado(asignaturaDTO.getEstado())
                .competenciasIds(competenciasIds)
                .build();

        return asignaturaRepository.save(asignatura);
    }*/
    /*
    @Override
    @Transactional
    public Asignatura crearAsignaturaConCompetencias(AsignaturaDTO asignaturaDTO) {
        // Validar competencias llamando al microservicio de competencias
        List<Integer> competenciasIds = asignaturaDTO.getCompetenciasIds();
        List<CompetenciaDTO> competenciasValidas = competenciaClient.validarCompetencias(competenciasIds);


        // Comprobar si todos los IDs son válidos
        if (competenciasValidas.size() != competenciasIds.size()) {
            throw new IllegalArgumentException("Algunas competencias no son válidas");
        }

        // Crear la entidad Asignatura
        Asignatura asignatura = Asignatura.builder()
                .Nombre(asignaturaDTO.getNombre())
                .Descripcion(asignaturaDTO.getDescripcion())
                .numCredito(asignaturaDTO.getNumCredito())
                .semestreAsignatura(asignaturaDTO.getSemestreAsignatura())
                .estado(asignaturaDTO.getEstado())
                .competenciasIds(competenciasIds)
                .build();

        // Guardar en la base de datos
        return asignaturaRepository.save(asignatura);
    }
*/

    /**
     * Aplica validaciones o reglas del dominio para una asignatura. Antes de ser
     * agregado o modificado.
     *
     * @param asignatura asignatura a validad
     * @return lista de errores de validación
     */

    private List<AsignaturaError> validateDomain(Asignatura asignatura) {
        List<AsignaturaError> errors = new ArrayList<>();

        if (asignatura.getDescripcion() == null || asignatura.getDescripcion().isEmpty()) {
            errors.add(new AsignaturaError(EnumErrorCodes.EMPTY_FIELD, "name", "La descripcion de la asignatura es obligatorio"));
        }

        return errors;

    }

}
