package org.unicauca.competenciasrapservice.capaControladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.unicauca.competenciasrapservice.fachadaServices.DTO.RapDTO;
import org.unicauca.competenciasrapservice.fachadaServices.services.IRapService;

import java.util.List;

@Controller
@RequestMapping("/competenciasrap")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class RapRestController {

    @Autowired
    private IRapService rapService;

    @GetMapping("/rap")
    public ResponseEntity<List<RapDTO>> findAll(){
        List<RapDTO> lista = rapService.listarRAP();
        ResponseEntity<List<RapDTO>> response = new ResponseEntity<>(lista, HttpStatus.OK);
        return response;
    }

    @PostMapping("/rap")
    public ResponseEntity<RapDTO> add(@RequestBody RapDTO rapDTO){
        RapDTO objRapDTO = rapService.agregarRap(rapDTO);
        ResponseEntity<RapDTO> response = new ResponseEntity<>(objRapDTO, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/rap/{id}")
    public ResponseEntity<RapDTO> update(@RequestBody RapDTO rapDTO, @PathVariable int id){
        RapDTO objRapDTO = rapService.editarRap(id,rapDTO);
        ResponseEntity<RapDTO> response = new ResponseEntity<>(objRapDTO, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/rap")
    public ResponseEntity<Boolean> delete(@RequestParam int id){
        Boolean resp = rapService.eliminarRap(id);
        ResponseEntity<Boolean> response = new ResponseEntity<>(resp, HttpStatus.NO_CONTENT);
        return response;
    }









}
