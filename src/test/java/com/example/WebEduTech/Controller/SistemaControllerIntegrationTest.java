
package com.example.WebEduTech.Controller;

import com.example.WebEduTech.Controller.SistemaController;
import com.example.WebEduTech.service.IncidenciaService;
import com.example.WebEduTech.service.NotificacionService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SistemaController.class)
public class SistemaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionService notificacionService;

    @MockBean
    private IncidenciaService incidenciaService;

    @Test
    void crearNotificacion_returnOK() throws Exception {
        String mensaje = "Servidor caído";

        doNothing().when(notificacionService).guardar(mensaje);

        mockMvc.perform(post("/api/v1/incidencias/notificacion")
                .param("mensaje", mensaje))
                .andExpect(status().isOk());

        verify(notificacionService).guardar(mensaje);
    }

    @Test
    void crearIncidencia_returnOK() throws Exception {
        String descripcion = "Error en login";
        String estado = "Abierta";

        doNothing().when(incidenciaService).reportar(descripcion, estado);

        mockMvc.perform(post("/api/v1/incidencias/incidencia")
                .param("descripcion", descripcion)
                .param("estado", estado))
                .andExpect(status().isOk());

        verify(incidenciaService).reportar(descripcion, estado);
    }
}