package com.unicauca.rest.persisence;

import com.unicauca.rest.entities.Rubrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubricaRepository  extends JpaRepository<Rubrica, Long> {
    Rubrica findRubricaByRubricaNombre(String rubricaNombre);

}
