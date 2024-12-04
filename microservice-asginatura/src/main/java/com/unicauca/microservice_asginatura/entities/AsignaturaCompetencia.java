package com.unicauca.microservice_asginatura.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.unicauca.microservice_asginatura.entities.Periodo;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asignatura_competencia")
public class AsignaturaCompetencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    @Column(name = "competencia_id", nullable = false)
    private Integer competenciaId;

    // Relación uno a uno con Periodo
    @JsonManagedReference
    @OneToOne(mappedBy = "asignaturaCompetencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Periodo periodo; // Periodo asociado
}
