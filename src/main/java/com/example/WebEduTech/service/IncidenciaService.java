package com.example.WebEduTech.service;

import org.springframework.stereotype.Service;
import com.example.WebEduTech.model.Incidencia;
import com.example.WebEduTech.repository.IncidenciaRepository;
import java.util.List;

@Service
public class IncidenciaService {
    private IncidenciaRepository incidenciaRepository;

    public IncidenciaService(IncidenciaRepository repository) {
        this.incidenciaRepository = repository;
    }

    public void reportar(String descripcion, String estado) {
        incidenciaRepository.save(new Incidencia(descripcion, estado));
    }
    
    public List<Incidencia> obtenerIncidencias() {
        return incidenciaRepository.obtenerIncidencias();
    }
}
