package com.unicauca.microservice_asginatura.entities;

import com.unicauca.microservice_asginatura.entities.AsignaturaCompetencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "periodo") // Asegúrate de que el nombre coincide con la tabla en la base de datos
public class Periodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "asignatura_competencia_id", nullable = false)
    private AsignaturaCompetencia asignaturaCompetencia;

    @Column(name = "periodo_ini", nullable = false)
    private LocalDate periodoIni;

    @Column(name = "periodo_fin", nullable = false)
    private LocalDate periodoFin;

    // Getters y Setters
}
