package com.unicauca.microservice_asginatura.controller;


import com.unicauca.microservice_asginatura.dtos.AsignaturaDTO;
import com.unicauca.microservice_asginatura.entities.Asignatura;
import com.unicauca.microservice_asginatura.exceptions.AsignaturaDomainException;
import com.unicauca.microservice_asginatura.exceptions.ResourceNotFoundException;
import com.unicauca.microservice_asginatura.service.IAsignaturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("asignaturas")
public class AsignaturaController {
    @Autowired
    private IAsignaturaService asignaturaService;
    /**
     * Listar todos
     *
     * @return listado de Asiganaturas en json
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Asignatura> findAll() {
        return (List<Asignatura>) asignaturaService.findAll();
    }

    /**
     * Listar una Asignatura por id
     *
     * @param id identificador
     * @return asignatura en formato json
     * @throws Exception
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Asignatura findById(@PathVariable("id") Long id) throws ResourceNotFoundException {

        Asignatura asignatura = asignaturaService.findById(id);
        return asignatura;
    }
    @RequestMapping(value = "{id}/obtenerAsignaturaCompetencia", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<AsignaturaDTO> obtenerAsignatura(@PathVariable Long id) {
        AsignaturaDTO asignatura = asignaturaService.obtenerAsignaturaConCompetencias(id);
        return ResponseEntity.ok(asignatura);
    }

    /**
     * Crear una asignatura
     *
     * @param asignatura asignatura
     * @return asignatura creada
     */
    //primera version
/*
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Asignatura create(@RequestBody Asignatura asignatura) throws AsignaturaDomainException {
        return asignaturaService.create(asignatura);
    }*/

    /**
     * Crear una asignatura con competencias asociadas.
     *
     * @param asignaturaDTO Datos de la asignatura, incluyendo los IDs de competencias.
     * @return La asignatura creada.
     * @throws ResourceNotFoundException Si alguno de los IDs de competencias no existe.
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Asignatura create(@RequestBody AsignaturaDTO asignaturaDTO) throws ResourceNotFoundException {
        return asignaturaService.crearAsignaturaConCompetencias(asignaturaDTO);
    }

    /**
     * Editar
     *
     * @param asignatura asignatura a editar
     * @param id       identificador de la asignatura
     * @return asignatura  editado
     * @throws ResourceNotFoundException recurso no encontrado
     * @throws Exception                 Id no encontrado
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Asignatura update(@RequestBody Asignatura asignatura, @PathVariable("id") Long id)
            throws AsignaturaDomainException, ResourceNotFoundException
    {
        return asignaturaService.update(id, asignatura);
    }

    /**
     * Eliminar
     *
     * @param id id de la asignatura
     * @throws ResourceNotFoundException id no encontrado
     */

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        asignaturaService.deleteById(id);
    }

    /**
     * cambair de estado
     * @param id id de la asignatura
     * @param nuevoEstado estado de la asignatura
     */
    @RequestMapping(value = "{id}/cambiar-estado", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Asignatura cambiarEstado(@PathVariable("id") Long id, @RequestBody Boolean nuevoEstado) throws ResourceNotFoundException {
        return asignaturaService.cambiarEstadoAsignatura(id, nuevoEstado);
    }




}
