package com.example.WebEduTech.controller;
//importar el backend de la clase usuario
import com.example.WebEduTech.model.usuario;
import com.example.WebEduTech.service.usuarioService;

//Importar ObjectMapper para convertir objetos en JSON
import com.fasterxml.jackson.databind.ObjectMapper;

//Importar las anotaciones de pruebas JUnit
import org.junit.jupiter.api.Test;

//Importar las anotaciones de Spring para inyectar las dependencias Maven
import org.springframework.beans.factory.annotation.Autowired;

//Importar de la anotacion para las pruebas de los controladores Web
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

//Importar la anotacion para simular os beans de Spring (Ingresar a los metodos del service)
import org.springframework.boot.test.mock.mockito.MockBean;

//Importar ContentType de tipo MediaType para manejar las peticiones HTTP
import org.springframework.http.MediaType;

//Importar MockMvc para realizar las peticiones HTTP simuladas
import org.springframework.test.web.servlet.MockMvc;

//Importar las clases necesarias para realizar las peticiones HTTP simuladas
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//Importar las clases necesarias para verificar los resultados de las peticiones HTTP simuladas
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//Importar el argumento any para simular los metodos del servicio
import static org.mockito.ArgumentMatchers.any;
//Importar Mockito para simular el comportamiento de los metodos del servicio
import static org.mockito.Mockito.when;

//Import para controlar cualquier tipo de vista o arreglo que tengamos en las capas de usuario
import java.util.Optional;

//Usar la anotacion WebMvcTest para probar el controlador web de una clase especifica.
@WebMvcTest(usuarioController.class)
public class usuarioControllerIntegrationTest {
    //Inyectar MockMvc para realizar las peticiones HTTP simuladas
    @Autowired
    private MockMvc mockMvc;

    //Inyectar el MockBean para simular el servicio de usuario
    @MockBean
    private usuarioService usuarioService;

    //Usar ObjectMapper para convertir los objetos en JSON
    @Autowired
    private ObjectMapper objectMapper;

    //Crear Test para simular la creacion de un nuevo usuario
    @Test
    void registrarUsuario_ReturnGuardado() throws Exception{
        usuario newUser = new usuario(); //Usar la tabla usuario y crear una variable para crear un usuario simulado
        newUser.setNombre("Esteban"); //Nombre de usuario simulado
        newUser.setEmail("esteban@gmail.com"); //Email de usuario simulado
        newUser.setPassword("1234"); //Password de usuario simulado

        //Simular que el usuario existe
        when(usuarioService.registrarUsuario(any(usuario.class))).thenReturn(newUser);

        //Realizar la peticion al POST para registrar un nuevo usuario
        mockMvc.perform(post("/api/v1/usuario/registrar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newUser)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Esteban"))
            .andExpect(jsonPath("$.email").value("esteban@gmail.com"))
            .andExpect(jsonPath("$.password").value("1234"));
    }

    //Test para simular el login del usuario
    @Test
    void loginUser_ReturnOK() throws Exception{
        usuario userExistente = new usuario();
        userExistente.setNombre("Esteban");
        userExistente.setEmail("esteban@gmail.com");
        userExistente.setPassword("1234");

        //Simular que el usuario esta registrado
        when(usuarioService.autenticar("esteban@gmail.com", "1234")).thenReturn(Optional.of(userExistente));

        //Realizar la peticion al POST para Iniciar Sesion
        mockMvc.perform(post("/api/v1/usuario/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userExistente)))
            .andExpect(status().isOk()) //Verificar la respuesta del estado de la peticion POST
            .andExpect(jsonPath("$.result").value("OK"))
            .andExpect(jsonPath("$.nombre").value("Esteban"))
            .andExpect(jsonPath("$.email").value("esteban@gmail.com"))
            .andExpect(jsonPath("$.password").value("1234"));
    }

    //Crear test para simular el comportamiendo del inicio de sesion de un usuario no registrado
    @Test
    void loginUsuario_ReturnError() throws Exception{
        usuario UserInexistente = new usuario();
        UserInexistente.setEmail("noexiste@gmail.com");
        UserInexistente.setPassword("1234");

        //Simular el comportamiento del login con una usuario no registrado
        when(usuarioService.autenticar("noexiste@gmail.com", "1234"))
            .thenReturn(Optional.empty());

        //Realizar la peticion al POST para iniciar sesion
        mockMvc.perform(post("/api/v1/usuario/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(UserInexistente)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("error"));
    }
}
