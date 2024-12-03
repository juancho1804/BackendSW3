package org.unicauca.docenteservice.capaControladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unicauca.docenteservice.fachadaServices.DTO.DocenteDTO;
import org.unicauca.docenteservice.fachadaServices.rest.Asignatura;
import org.unicauca.docenteservice.fachadaServices.services.IDocenteService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/docentes")
public class DocenteRestController {
    @Autowired
    private IDocenteService docenteService;

    @PostMapping
    public ResponseEntity<DocenteDTO> agregar(@RequestBody DocenteDTO docenteDTO){
        try{
            DocenteDTO nuevoDocente = docenteService.agregarDocente(docenteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDocente);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{identificacion}")
    public ResponseEntity<DocenteDTO> editar(@RequestBody DocenteDTO docenteDTO, @PathVariable String identificacion){
        try{
            DocenteDTO docenteActualizado = docenteService.actualizarDocente(docenteDTO, identificacion);
            return ResponseEntity.status(HttpStatus.OK).body(docenteActualizado);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DocenteDTO> cambiarEstado(@PathVariable int id){
        if(docenteService.cambiarEstado(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping
    public ResponseEntity<List<DocenteDTO>> listarDocentes() {
        List<DocenteDTO> docentes = docenteService.listarDocentes();
        if (docentes.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(docentes); // 200 OK
    }

    @PatchMapping("/agregarAsignatura/{identificacion}")
    public ResponseEntity<DocenteDTO>agregarAsignatura(@PathVariable("identificacion") String identificacion, @RequestBody List<Integer> asignatura){
        if(docenteService.agregarAsignatura(identificacion,asignatura)!=null){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
