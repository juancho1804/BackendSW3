package com.unicauca.rest.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "criterio")
public class Criterio  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long criterioId;
    private String criterioDescripcion;
    private float ponderacion;
    private String  nivel1;
    private String  nivel2;
    private String  nivel3;
    private String  nivel4;

    @ManyToOne
    @JoinColumn(name="id_Rubrica",nullable = false)

    private  Rubrica rubrica;
}
