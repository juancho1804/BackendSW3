package com.unicauca.rest.persisence;

import com.unicauca.rest.entities.Criterio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioRepository extends JpaRepository<Criterio, Long> {

}
