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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(usuarioController.class)
public class usuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private usuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registrarUsuario_returnGuardado() throws Exception {
        usuario nuevoUsuario = new usuario();
        nuevoUsuario.setNombre("Juan");
        nuevoUsuario.setEmail("juan@gmail.com");
        nuevoUsuario.setPassword("1234");

        when(usuarioService.registrarUsuario(any(usuario.class))).thenReturn(nuevoUsuario);

        mockMvc.perform(post("/api/v1/usuario/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoUsuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.email").value("juan@gmail.com"))
                .andExpect(jsonPath("$.password").value("1234"));
    }

    @Test
    void loginUsuario_returnOK() throws Exception {
        usuario usuarioExistente = new usuario();
        usuarioExistente.setNombre("Juan");
        usuarioExistente.setEmail("juan@gmail.com");
        usuarioExistente.setPassword("1234");

        when(usuarioService.autenticar("juan@gmail.com", "1234"))
                .thenReturn(Optional.of(usuarioExistente));

        // Construye un objeto con solo email y password para simular el body real del login
        usuario loginRequest = new usuario();
        loginRequest.setEmail("juan@gmail.com");
        loginRequest.setPassword("1234");

        mockMvc.perform(post("/api/v1/usuario/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("OK"))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.email").value("juan@gmail.com"))
                .andExpect(jsonPath("$.password").value("1234"));
    }

    @Test
    void loginUsuario_returnError() throws Exception {
        usuario loginRequest = new usuario();
        loginRequest.setEmail("noexiste@gmail.com");
        loginRequest.setPassword("1234");

        when(usuarioService.autenticar("noexiste@gmail.com", "1234"))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/api/v1/usuario/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("Error"));
    }
}