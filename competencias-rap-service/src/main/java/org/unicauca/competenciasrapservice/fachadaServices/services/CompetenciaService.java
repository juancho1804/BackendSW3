package org.unicauca.competenciasrapservice.fachadaServices.services;


import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Competencia;
import org.unicauca.competenciasrapservice.capaAccesoADatos.repositories.ICompetenciaRepositorio;
import org.unicauca.competenciasrapservice.fachadaServices.DTO.CompetenciaDTO;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenciaService implements ICompetenciaService{
    @Autowired
    private ICompetenciaRepositorio competenciaRepositorio;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CompetenciaDTO> listarCompetencias() {
        List<Competencia> competencias = this.competenciaRepositorio.findAll();
        if(!competenciaRepositorio.findAll().isEmpty()){
            List<CompetenciaDTO>competenciaDTOS = this.modelMapper.map(competencias, List.class);
            return competenciaDTOS;
        }else{
            throw new EntityNotFoundException("No hay competencias");
        }

    }

    @Override
    public CompetenciaDTO agregarCompetencia(CompetenciaDTO competencia) {
        CompetenciaDTO competenciaDTO = null;
        Competencia competenciaEntity = this.modelMapper.map(competencia, Competencia.class);
        Competencia objCompetenciaEntity=this.competenciaRepositorio.save(competenciaEntity);
        competenciaDTO = this.modelMapper.map(objCompetenciaEntity, CompetenciaDTO.class);
        return competenciaDTO;
    }

    @Override
    public CompetenciaDTO editarCompetencia(int id, CompetenciaDTO competenciaDTO) {


        Optional<Competencia> optionalCompetencia = competenciaRepositorio.findById(id);
        if (optionalCompetencia.isPresent()) {
            Competencia competenciaEntity = optionalCompetencia.get();
            // Actualizar los campos de la entidad
            competenciaEntity.setDescripcion(competenciaDTO.getDescripcion());
            competenciaEntity.setNivel(competenciaDTO.getNivel());
            // Guardar los cambios y retornar la entidad actualizada
            competenciaRepositorio.save(competenciaEntity);
            return competenciaDTO;
        } else {
            // Manejo de error si no se encuentra el ID
            throw new EntityNotFoundException("La competencia con el ID " + id + " no existe.");
        }
    }


    @Override
    public boolean eliminarCompetencia(int id) {
        Optional<Competencia> optionalCompetencia = competenciaRepositorio.findById(id);
        if (optionalCompetencia.isPresent()) {
            competenciaRepositorio.deleteById(id);
            return true; // Eliminación exitosa
        } else {
            throw new EntityNotFoundException("La competencia con el ID " + id + " no existe");
        }
    }





}
