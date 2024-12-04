package com.unicauca.microservice_asginatura.controller;

import com.unicauca.microservice_asginatura.dtos.PeriodoRequest;
import com.unicauca.microservice_asginatura.exceptions.ResourceNotFoundException;
import com.unicauca.microservice_asginatura.persisence.PeriodoRepository;
import com.unicauca.microservice_asginatura.service.IPeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unicauca.microservice_asginatura.entities.Periodo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/periodos")
@CrossOrigin(origins = "*")
public class PeriodoController {
    @Autowired
    private IPeriodoService periodoService;
    /**
     * Crear un nuevo periodo
     */
    @PostMapping
    public ResponseEntity<Periodo> agregarPeriodo(@RequestBody PeriodoRequest periodoRequest) throws ResourceNotFoundException {
        Periodo nuevoPeriodo = periodoService.create(periodoRequest.getAsignaturaCompetenciaId(),
                periodoRequest.getPeriodoIni(),
                periodoRequest.getPeriodoFin());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPeriodo);
    }


    /**
     * Listar todos los periodos
     */
    @GetMapping
    public List<Periodo> listarPeriodos() {
        return periodoService.findAll();
    }

    /**
     * Obtener un periodo por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Periodo> obtenerPeriodoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        Periodo periodo = periodoService.findById(id);
        return ResponseEntity.ok(periodo);
    }

    /**
     * Actualizar un periodo existente
     */
    /*
    @PutMapping("/{id}")
    public ResponseEntity<Periodo> actualizarPeriodo(
            @PathVariable Long id,
            @RequestParam LocalDate periodoIni,
            @RequestParam LocalDate periodoFin) throws ResourceNotFoundException {
        Periodo periodoActualizado = periodoService.update(id, periodoIni, periodoFin);
        return ResponseEntity.ok(periodoActualizado);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Periodo> actualizarPeriodo(@PathVariable Long id, @RequestBody PeriodoRequest periodoRequest) throws ResourceNotFoundException {
        Periodo periodoActualizado = periodoService.update(id, periodoRequest.getPeriodoIni(), periodoRequest.getPeriodoFin());
        return ResponseEntity.ok(periodoActualizado);
    }


    /**
     * Eliminar un periodo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPeriodo(@PathVariable Long id) throws ResourceNotFoundException {
        periodoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
