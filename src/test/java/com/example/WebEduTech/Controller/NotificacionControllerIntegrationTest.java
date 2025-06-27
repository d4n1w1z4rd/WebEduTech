package com.example.WebEduTech.Controller;

import com.example.WebEduTech.controller.NotificacionController;
import com.example.WebEduTech.model.Notificacion;
import com.example.WebEduTech.service.NotificacionService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificacionController.class)
public class NotificacionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionService notificacionService;

    @Test
    void listarNotificaciones_returnListaVacia() throws Exception {
        when(notificacionService.obtenerNotificaciones()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void listarNotificaciones_returnListaConDatos() throws Exception {
        Notificacion noti = new Notificacion();
        noti.setId(1);
        noti.setMensaje("Clase reprogramada");

        when(notificacionService.obtenerNotificaciones()).thenReturn(List.of(noti));

        mockMvc.perform(get("/api/v1/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].mensaje").value("Clase reprogramada"))
                .andExpect(jsonPath("$[0].estado").value("nueva"));
    }
}