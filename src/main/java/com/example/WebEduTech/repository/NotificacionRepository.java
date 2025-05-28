package com.example.WebEduTech.repository;

import com.example.EduTech_Innovators_SPA.model.Notificacion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificacionRepository {
    private List<Notificacion> listaNotificaciones = new ArrayList<>();

    public NotificacionRepository() {
        // Agregar notificaciones por defecto
        listaNotificaciones.add(new Notificacion(1, "Bienvenido a EduTech Innovators!", 1));
        listaNotificaciones.add(new Notificacion(2, "Tu curso ha sido actualizado.", 2));
        listaNotificaciones.add(new Notificacion(3, "Nuevo mensaje de tu instructor.", 1));
        listaNotificaciones.add(new Notificacion(4, "Tu tarea ha sido calificada.", 3));
    }

    public List<Notificacion> obtenerNotificaciones(){
        return listaNotificaciones;
    }
}
