package org.unicauca.competenciasrapservice.fachadaServices.services;


import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Competencia;
import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Rap;
import org.unicauca.competenciasrapservice.capaAccesoADatos.repositories.ICompetenciaRepositorio;
import org.unicauca.competenciasrapservice.capaAccesoADatos.repositories.IRapRepositorio;
import org.unicauca.competenciasrapservice.capaControladores.controladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import org.unicauca.competenciasrapservice.capaControladores.controladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;
import org.unicauca.competenciasrapservice.fachadaServices.DTO.CompetenciaDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetenciaService implements ICompetenciaService{
    @Autowired
    private ICompetenciaRepositorio competenciaRepositorio;
    @Autowired
    private IRapRepositorio rapRepositorio;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CompetenciaDTO> listarCompetencias() {
        // Recuperar la lista de competencias del repositorio
        List<Competencia> competencias = this.competenciaRepositorio.findAll();

        // Validar si la lista está vacía
        if (competencias.isEmpty()) {
            throw new EntityNotFoundException("No hay competencias disponibles");
        }

        // Mapear la lista a DTOs
        return competencias.stream()
                .map(competencia -> this.modelMapper.map(competencia, CompetenciaDTO.class)).collect(Collectors.toList());
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
            Competencia competencia = optionalCompetencia.get();

            // Verificar si existen RAPs asociados
            System.out.println(competencia.getId());
            List<Rap> rapsAsociados = rapRepositorio.findByCompetenciaId(competencia.getId());
            if (!rapsAsociados.isEmpty()) {
                System.out.println("Entro aca");
                throw new ReglaNegocioExcepcion("No se puede eliminar la competencia porque tiene RAPs asociados.");
            }else{
                competenciaRepositorio.deleteById(id);
            }
            return true; // Eliminación exitosa
        } else {
            System.out.println("NO ENTRO");
            throw new EntidadNoExisteException("La competencia con el ID " + id + " no existe");
        }
    }


    @Override
    public List<Competencia> obtenerCompetenciasPorIds(List<Integer> ids) {
        return competenciaRepositorio.findAllById(ids);
    }
    /**
     * Valida una lista de IDs de competencias.
     *
     * @param ids Lista de IDs de competencias a validar.
     * @return Lista de competencias válidas.
     */
    @Override
    public List<CompetenciaDTO> validarCompetencias(List<Integer> ids) {
        List<Competencia> competencias = competenciaRepositorio.findAllById(ids);

        // Convertir entidades a DTOs
        return competencias.stream()
                .map(competencia -> new CompetenciaDTO(
                        competencia.getId(),
                        competencia.getDescripcion(),
                        competencia.getNivel()
                ))
                .collect(Collectors.toList());
    }





}
