package com.example.WebEduTech.Controller;

import com.example.WebEduTech.controller.usuarioController;
import com.example.WebEduTech.model.usuario;
import com.example.WebEduTech.service.usuarioService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(usuarioController.class)
public class usuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private usuarioService serv;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registrarUsuario_returnUsuarioGuardado() throws Exception {
        usuario nuevo = new usuario();
        nuevo.setId(1);
        nuevo.setNombre("Juan");
        nuevo.setEmail("juan@mail.com");
        nuevo.setPassword("1234");

        when(serv.registrarUsuario(any(usuario.class))).thenReturn(nuevo);

        mockMvc.perform(post("/api/v1/usuario/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.email").value("juan@mail.com"));
    }

    @Test
    void loginUsuario_returnLoginExitoso() throws Exception {
        usuario u = new usuario();
        u.setEmail("juan@mail.com");
        u.setPassword("1234");

        usuario encontrado = new usuario();
        encontrado.setId(1);
        encontrado.setNombre("Juan");
        encontrado.setEmail("juan@mail.com");

        when(serv.autenticar(eq("juan@mail.com"), eq("1234"))).thenReturn(Optional.of(encontrado));

        mockMvc.perform(post("/api/v1/usuario/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("OK"))
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void loginUsuario_returnErrorLogin() throws Exception {
        usuario u = new usuario();
        u.setEmail("noexiste@mail.com");
        u.setPassword("wrongpass");

        when(serv.autenticar(eq("noexiste@mail.com"), eq("wrongpass"))).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/v1/usuario/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("error"));
    }
}