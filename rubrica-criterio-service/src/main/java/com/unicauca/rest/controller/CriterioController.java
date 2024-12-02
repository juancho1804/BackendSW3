package com.unicauca.rest.controller;


import com.unicauca.rest.entities.Criterio;
import com.unicauca.rest.exceptions.CriterioDomainException;
import com.unicauca.rest.exceptions.ResourceNotFoundException;
import com.unicauca.rest.service.ICriterioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("criterios")
@CrossOrigin(value = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CriterioController {
    @Autowired
    private ICriterioService criterioService;
    /**
     * Listar todos
     *
     * @return listado de Categorias en json
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Criterio> findAll() {
        return (List<Criterio>) criterioService.findAll();
    }

    /**
     * Listar un Categoria por id
     *
     * @param id identificador
     * @return Categoria en formato json
     * @throws Exception
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Criterio findById(@PathVariable Long id) throws ResourceNotFoundException {

        Criterio criterio = criterioService.findById(id);
        return criterio;
    }

    /**
     * Crear un producto
     *
     * @param criterio criterio
     * @return criterio creado
     */

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Criterio create(@RequestBody Criterio criterio) throws CriterioDomainException {
        return criterioService.create(criterio);
    }

    /**
     * Editar
     *
     * @param criterio criterio a editar
     * @param id       identificador de el criterio
     * @return categoria  editado
     * @throws ResourceNotFoundException recurso no encontrado
     * @throws Exception                 Id no encontrado
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Criterio update(@RequestBody Criterio criterio, @PathVariable Long id)
            throws CriterioDomainException, ResourceNotFoundException
    {
        return criterioService.update(id, criterio);
    }

    /**
     * Eliminar
     *
     * @param id id de LA criterio
     * @throws ResourceNotFoundException id no encontrado
     */

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ResourceNotFoundException {
        criterioService.deleteById(id);
    }

}
