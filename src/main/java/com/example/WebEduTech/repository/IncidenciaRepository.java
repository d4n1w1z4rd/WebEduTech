package com.example.WebEduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebEduTech.model.Incidencia;

import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> obtenerIncidencias();
}
