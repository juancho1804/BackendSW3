package com.unicauca.microservice_asginatura.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "asignatura")
public class Asignatura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asignaturaId;
    private String Nombre;
    private String Descripcion;
    private Integer numCredito;
    private  Integer semestreAsignatura;
    private Boolean estado;
    // Lista de IDs de competencias asociadas
    @ElementCollection
    @CollectionTable(name = "asignatura_competencias", joinColumns = @JoinColumn(name = "asignatura_id"))
    @Column(name = "competencia_id")
    private List<Integer> competenciasIds = new ArrayList<>();
}
