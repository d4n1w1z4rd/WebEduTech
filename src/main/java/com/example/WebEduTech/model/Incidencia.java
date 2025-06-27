package com.example.WebEduTech.model;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private String estado;

    private LocalDateTime fechaReporte;

    public Incidencia() {
        this.fechaReporte = LocalDateTime.now();
    }

    public Incidencia(String descripcion, String estado) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaReporte = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaReporte() { return fechaReporte;}

}


