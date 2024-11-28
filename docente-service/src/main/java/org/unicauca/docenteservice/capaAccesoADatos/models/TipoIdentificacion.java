package org.unicauca.docenteservice.capaAccesoADatos.models;

import jakarta.persistence.*;


public class TipoIdentificacion {
    private Integer id;

    private ETipoIdentificacion name;
    public TipoIdentificacion() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ETipoIdentificacion getTipoIdentificacion() {
        return name;
    }

    public void setTipoIdentificacion(ETipoIdentificacion tipoIdentificacion) {
        this.name = tipoIdentificacion;
    }
}
