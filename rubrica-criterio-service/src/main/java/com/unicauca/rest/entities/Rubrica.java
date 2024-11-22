package com.unicauca.rest.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "rubrica")
public class Rubrica   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rubricaId;
    private String rubricaNombre;
    private boolean estado;

    @OneToMany(mappedBy = "rubrica",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)

    private List<Criterio> criterioList = new ArrayList<Criterio>();



}
