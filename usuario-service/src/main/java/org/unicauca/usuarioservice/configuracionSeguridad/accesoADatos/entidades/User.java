package org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "identificacion"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String tipoIdentificacion;

    @Size(min = 2, max = 15)
    private String identificacion;



    @Size(max = 20)
    private String nombres;


    @Size(max = 20)
    private String apellidos;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String titulo;

    @NotBlank
    @Size(max = 20)
    private String estado;

    @NotBlank
    @Size(max = 120)
    private String contrasenia;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String identificacion, String nombres, String apellidos, String email, String title, String state, String password) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.titulo = title;
        this.estado = state;
        this.contrasenia = password;
    }



}
