package com.example.WebEduTech.controller;

import com.example.WebEduTech.model.Incidencia;
import com.example.WebEduTech.service.IncidenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping
    public List<Incidencia> listarIncidencias(){
        return incidenciaService.obtenerIncidencias();
    }
}
