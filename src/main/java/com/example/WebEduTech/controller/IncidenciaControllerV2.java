package com.example.WebEduTech.controller;

import com.example.WebEduTech.model.Incidencia;
import com.example.WebEduTech.service.IncidenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/Incidencias")
public class IncidenciaControllerV2 {
    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping
    public List<Incidencia> listarIncidencias(){
        return incidenciaService.obtenerIncidencias();
    }
}
