package org.unicauca.docenteservice.capaAccesoADatos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "docentes")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ETipoIdentificacion tipoIdentificacion;

    @Column
    private String identificacion;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column
    private String email;

    @Column
    private String tituloAcademico;

    @Column
    @Enumerated(EnumType.STRING)
    private EEstado estado;

    @Column
    private String contrasenia;

    @Column
    @Enumerated(EnumType.STRING)
    private ETipoDocente tipoDocente;
}
