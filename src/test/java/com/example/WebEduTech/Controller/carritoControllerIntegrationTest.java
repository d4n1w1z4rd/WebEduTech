package com.example.WebEduTech.Controller;

import com.example.WebEduTech.model.Producto;
import com.example.WebEduTech.service.ProductoService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.WebEduTech.controller.carritoController;

@WebMvcTest(carritoController.class)
public class carritoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoser;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void agregarProducto_returnProductoAgregado() throws Exception {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setTitulo("Libro");
        producto.setStock(5);

        // Simulamos que se encontró el producto
        when(productoser.getProductoId(1)).thenReturn(producto);

        mockMvc.perform(post("/api/v1/carrito/agregar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto agregado al carrito" + producto.getTitulo()));
    }

    @Test
    void agregarProducto_returnSinStock() throws Exception {
        Producto producto = new Producto();
        producto.setId(2);
        producto.setTitulo("Cuaderno");
        producto.setStock(0);

        when(productoser.getProductoId(2)).thenReturn(producto);

        mockMvc.perform(post("/api/v1/carrito/agregar/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("producto sin Stock"));
    }

    @Test
    void agregarProducto_returnNoEncontrado() throws Exception {
        when(productoser.getProductoId(999)).thenReturn(null);

        mockMvc.perform(post("/api/v1/carrito/agregar/999"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto no fue encontrado"));
    }

    @Test
    void verCarrito_returnListaVacia() throws Exception {
        mockMvc.perform(get("/api/v1/carrito"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]")); // al inicio el carrito estará vacío
    }

    @Test
    void vaciarCarrito_returnMensaje() throws Exception {
        mockMvc.perform(delete("/api/v1/carrito/Vaciar"))
                .andExpect(status().isOk())
                .andExpect(content().string("Carrito vacio"));
    }

    @Test
    void totalProductosCarrito_returnCeroInicialmente() throws Exception {
        mockMvc.perform(get("/api/v1/carrito/Total"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    void eliminarProducto_returnMensajeNoEstaba() throws Exception {
        mockMvc.perform(delete("/api/v1/carrito/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto no estaba en el carrito"));
    }
}