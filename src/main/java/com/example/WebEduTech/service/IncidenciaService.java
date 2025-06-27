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

import com.example.WebEduTech.model.Incidencia;
import com.example.WebEduTech.repository.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    public List<Incidencia> obtenerIncidencias() {
        return incidenciaRepository.obtenerIncidencias();
    }
}
