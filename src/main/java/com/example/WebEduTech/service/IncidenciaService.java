package com.example.WebEduTech.service;
import org.springframework.stereotype.Service;

import com.example.WebEduTech.model.Incidencia;
import com.example.WebEduTech.repository.IncidenciaRepository;

@Service
public class IncidenciaService {

    private final IncidenciaRepository repository;

    public IncidenciaService(IncidenciaRepository repository) {
        this.repository = repository;
    }

    public void reportar(String descripcion, String estado) {
        repository.save(new Incidencia(descripcion, estado));
    }
}