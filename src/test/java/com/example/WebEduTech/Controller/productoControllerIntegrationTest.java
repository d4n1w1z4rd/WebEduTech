package com.example.WebEduTech.Controller;

import com.example.WebEduTech.controller.productoController;
import com.example.WebEduTech.model.Producto;
import com.example.WebEduTech.service.ProductoService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(productoController.class)
public class productoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProductos_returnListaVacia() throws Exception {
        when(productoService.getProductos()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/productos"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void agregarProducto_returnProductoGuardado() throws Exception {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setTitulo("Teclado");
        producto.setStock(10);

        when(productoService.saveProducto(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Teclado"))
                .andExpect(jsonPath("$.stock").value(10));
    }

    @Test
    void buscarProductoPorId_returnProducto() throws Exception {
        Producto producto = new Producto();
        producto.setId(2);
        producto.setTitulo("Mouse");
        producto.setStock(15);

        when(productoService.getProductoId(2)).thenReturn(producto);

        mockMvc.perform(get("/api/v1/productos/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.titulo").value("Mouse"))
                .andExpect(jsonPath("$.stock").value(15));
    }

    @Test
    void actualizarProducto_returnProductoActualizado() throws Exception {
        Producto producto = new Producto();
        producto.setId(3);
        producto.setTitulo("Monitor");
        producto.setStock(5);

        when(productoService.updateProducto(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/api/v1/productos/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.titulo").value("Monitor"))
                .andExpect(jsonPath("$.stock").value(5));
    }

    @Test
    void eliminarProducto_returnMensaje() throws Exception {
        when(productoService.deleteProducto(4)).thenReturn("producto eliminado");

        mockMvc.perform(delete("/api/v1/productos/4"))
                .andExpect(status().isOk())
                .andExpect(content().string("producto eliminado"));
    }

    @Test
    void totalProductosV2_returnNumero() throws Exception {
        when(productoService.totalProductosV2()).thenReturn(7);

        mockMvc.perform(get("/api/v1/productos/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("7"));
    }
}
