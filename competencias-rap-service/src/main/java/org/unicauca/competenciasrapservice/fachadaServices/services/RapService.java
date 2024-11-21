package org.unicauca.competenciasrapservice.fachadaServices.services;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Competencia;
import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Rap;
import org.unicauca.competenciasrapservice.capaAccesoADatos.repositories.IRapRepositorio;
import org.unicauca.competenciasrapservice.fachadaServices.DTO.RapDTO;

import java.util.List;
import java.util.Optional;

@Service
public class RapService implements IRapService{
    @Autowired
    private IRapRepositorio rapRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RapDTO> listarRAP() {
        List<Rap> raps = rapRepositorio.findAll();
        if(!rapRepositorio.findAll().isEmpty()){
            List<RapDTO> rapDTOS = this.modelMapper.map(raps,List.class);
            return rapDTOS;
        }else{
            throw new EntityNotFoundException("No hay resultados de aprendizaje por programa registrados");
        }
    }

    @Override
    public RapDTO agregarRap(RapDTO rap) {
        RapDTO rapDTO = null;
        Rap rapEntity = this.modelMapper.map(rap,Rap.class);
        Rap objRapEntity= this.rapRepositorio.save(rapEntity);
        rapDTO = this.modelMapper.map(objRapEntity,RapDTO.class);

        return rapDTO;
    }

    @Override
    public RapDTO editarRap(int id, RapDTO rap) {
        Optional<Rap> optionalRap = rapRepositorio.findById(id);
        if(optionalRap.isPresent()){
            Rap rapEntity = optionalRap.get();
            rapEntity.setDescripcion(rap.getDescripcion());
            rapEntity.setCompetencia(modelMapper.map(rap.getCompetencia(), Competencia.class));
            RapDTO objRapDTO = this.modelMapper.map(rapRepositorio.save(rapEntity),RapDTO.class);
            return objRapDTO;
        }else{
            throw new EntityNotFoundException("No se encontro el Rap");
        }
    }

    @Override
    public boolean eliminarRap(int id) {
        Optional<Rap> optionalRap = rapRepositorio.findById(id);
        if(optionalRap.isPresent()){
            rapRepositorio.deleteById(id);
            return true;
        }else{
            throw new EntityNotFoundException("No se encontro el Rap con id "+id);
        }
    }
}
