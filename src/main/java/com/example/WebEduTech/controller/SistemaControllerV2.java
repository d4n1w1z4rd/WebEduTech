package com.example.WebEduTech.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebEduTech.service.IncidenciaService;

@RestController
@RequestMapping("/api/v1/incidencias")
public class SistemaController {

    private final IncidenciaService incidenciaService;

    public SistemaController(IncidenciaService incidenciaService) {

        this.incidenciaService = incidenciaService;
    }


    @PostMapping("/incidencia")
    public void crearIncidencia(@RequestParam String descripcion, @RequestParam String estado) {
        System.out.println("Recibida incidencia: " + descripcion + " | Estado: " + estado);
        incidenciaService.reportar(descripcion, estado);
    }
}
