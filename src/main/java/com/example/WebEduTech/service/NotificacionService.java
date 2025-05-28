package com.example.EduTech_Innovators_SPA.service;

import com.example.EduTech_Innovators_SPA.model.Notificacion;
import com.example.EduTech_Innovators_SPA.repository.NotificacionRepository;
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
