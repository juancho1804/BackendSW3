package org.unicauca.competenciasrapservice.capaAccesoADatos.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Competencia;

@Repository
public interface ICompetenciaRepositorio extends JpaRepository<Competencia,Integer> {
}
