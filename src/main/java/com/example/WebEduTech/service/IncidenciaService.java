package com.example.WebEduTech.service;

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
