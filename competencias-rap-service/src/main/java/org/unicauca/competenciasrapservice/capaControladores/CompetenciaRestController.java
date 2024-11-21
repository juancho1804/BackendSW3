package org.unicauca.competenciasrapservice.capaControladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unicauca.competenciasrapservice.fachadaServices.DTO.CompetenciaDTO;
import org.unicauca.competenciasrapservice.fachadaServices.services.ICompetenciaService;

import java.util.List;

@RestController
@RequestMapping("/competenciasrap")
@CrossOrigin(value = "http://localhost:4200",
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CompetenciaRestController {
    @Autowired
    private ICompetenciaService competenciaService;

    @GetMapping("/competencias")
    public ResponseEntity<List<CompetenciaDTO>> findAll(){
        List<CompetenciaDTO> lista = competenciaService.listarCompetencias();
        ResponseEntity<List<CompetenciaDTO>> response = new ResponseEntity<>(lista, HttpStatus.OK);
        return response;
    }


    @PostMapping("/competencias")
    public ResponseEntity<CompetenciaDTO> add(@RequestBody CompetenciaDTO competenciaDTO){
        CompetenciaDTO objCompetenciaDTO = competenciaService.agregarCompetencia(competenciaDTO);
        ResponseEntity<CompetenciaDTO> response = new ResponseEntity<>(objCompetenciaDTO, HttpStatus.CREATED);
        return response;
    }


    @PutMapping("/competencias/{id}")
    public ResponseEntity<CompetenciaDTO> update(@RequestBody CompetenciaDTO competenciaDTO, @PathVariable int id){
        CompetenciaDTO objCompetenciaDTO = competenciaService.editarCompetencia(id,competenciaDTO);
        ResponseEntity<CompetenciaDTO> response = new ResponseEntity<>(objCompetenciaDTO, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/competencias")
    public ResponseEntity<Boolean> delete(@RequestParam int id){
        Boolean resp = competenciaService.eliminarCompetencia(id);
        ResponseEntity<Boolean> response = new ResponseEntity<>(resp, HttpStatus.NO_CONTENT);
        return response;
    }
}
