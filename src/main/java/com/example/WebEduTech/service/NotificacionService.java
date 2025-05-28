package com.example.WebEduTech.service;

import com.example.WebEduTech.model.Notificacion;
import com.example.WebEduTech.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {
    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> obtenerNotificaciones() {
        return notificacionRepository.obtenerNotificaciones();
    }
}
