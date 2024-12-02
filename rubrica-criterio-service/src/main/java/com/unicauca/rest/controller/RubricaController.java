package com.unicauca.rest.controller;


import com.unicauca.rest.entities.Criterio;
import com.unicauca.rest.entities.Rubrica;
import com.unicauca.rest.exceptions.ResourceNotFoundException;
import com.unicauca.rest.exceptions.RubricaDomainException;
import com.unicauca.rest.service.IRubricaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rubricas")
@CrossOrigin(value = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class RubricaController {

    @Autowired
    private IRubricaService rubricaService;



    /**
     * Listar todos
     *
     * @return listado de productos en json
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Rubrica> findAll() {
        return (List<Rubrica>) rubricaService.findAll();
    }

    /**
     * Listar un producto por id
     *
     * @param id identificador
     * @return Producto en formato json
     * @throws Exception
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Rubrica findById(@PathVariable Long id) throws ResourceNotFoundException {

        Rubrica rubrica = rubricaService.findById(id);
        // Forzamos la inicialización de la lista de criterios
        rubrica.getCriterioList().size();
        return rubrica;
    }

    /**
     * obtiene  rubrica por nombre
     */
    @RequestMapping(value = "/by-name/{name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Rubrica findProductByName(@PathVariable String name) throws ResourceNotFoundException {
        System.out.println("entro al metodo buscar por nombre producto " + name);
        Rubrica rubrica = rubricaService.findRubricaByRubricaNombre(name);
        if(rubrica == null) {
            System.out.println("la rubrica NO SE ENCONTRO");

        }else{
            System.out.println("lo encontro "+rubrica.getRubricaNombre());
        }


        return rubrica;
    }

    /**
     * Crear un producto
     *
     * @param rubrica rubrica
     * @return rubrica creada
     */

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Rubrica create(@RequestBody Rubrica rubrica) throws RubricaDomainException {
        return rubricaService.create(rubrica);
    }


    /**
     * Editar
     *
     * @param rubrica rubrica a editar
     * @param id      identificador de la rubrica
     * @return rubrica editado
     * @throws ResourceNotFoundException recurso no encontrado
     * @throws Exception                 Id no encontrado
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Rubrica update(@RequestBody Rubrica rubrica, @PathVariable Long id)
            throws RubricaDomainException, ResourceNotFoundException {
        return rubricaService.update(id, rubrica);
    }

    /**
     * Eliminar
     *
     * @param id id del rubrica
     * @throws ResourceNotFoundException id no encontrado
     */

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ResourceNotFoundException {
        rubricaService.deleteById(id);
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> detail (@PathVariable Long id) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(rubricaService.findById(id));
    }
}
