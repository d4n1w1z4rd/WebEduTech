package com.example.WebEduTech.controller;

import com.example.WebEduTech.model.Notificacion;
import com.example.WebEduTech.service.NotificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {
    
    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public List<Notificacion> listarNotificaciones(){
        return notificacionService.obtenerNotificaciones();
    }
}
