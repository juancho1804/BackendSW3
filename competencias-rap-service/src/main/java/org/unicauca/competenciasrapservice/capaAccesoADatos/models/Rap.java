package org.unicauca.competenciasrapservice.capaAccesoADatos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String descripcion;
    @ManyToOne(fetch = FetchType.LAZY) // Relación de muchos a uno con Competencia
    @JoinColumn(name = "competencia_id", nullable = false) // Nombre de la columna FK
    private Competencia competencia;

}
