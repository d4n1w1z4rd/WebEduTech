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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(productoController.class)
public class productoControllerIntegrationTest {

    @Test
    void testName() {
        
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProductos_returnLista() throws Exception {
        Producto p1 = new Producto();
        p1.setId(1);
        p1.setTitulo("Libro");
        p1.setStock(10);

        Producto p2 = new Producto();
        p2.setId(2);
        p2.setTitulo("Cuaderno");
        p2.setStock(5);

        List<Producto> lista = Arrays.asList(p1, p2);
        when(productoService.getProductos()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("Libro"))
                .andExpect(jsonPath("$[1].titulo").value("Cuaderno"));
    }

    @Test
    void agregarProducto_returnGuardado() throws Exception {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setTitulo("Agenda");
        producto.setStock(20);

        when(productoService.saveProducto(producto)).thenReturn(producto);

        mockMvc.perform(post("/api/v1/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Agenda"))
                .andExpect(jsonPath("$.stock").value(20));
    }

    @Test
    void buscarProducto_returnProducto() throws Exception {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setTitulo("Agenda");
        producto.setStock(20);

        when(productoService.getProductoId(1)).thenReturn(producto);

        mockMvc.perform(get("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Agenda"))
                .andExpect(jsonPath("$.stock").value(20));
    }

    @Test
    void actualizarProducto_returnActualizado() throws Exception {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setTitulo("Agenda actualizada");
        producto.setStock(15);

        when(productoService.updateProducto(producto)).thenReturn(producto);

        mockMvc.perform(put("/api/v1/productos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Agenda actualizada"))
                .andExpect(jsonPath("$.stock").value(15));
    }

    @Test
    void eliminarProducto_returnMensaje() throws Exception {
        when(productoService.deleteProducto(1)).thenReturn("Producto eliminado");

        mockMvc.perform(delete("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado"));
    }

    @Test
    void totalProductos_returnCantidad() throws Exception {
        when(productoService.totalProductosV2()).thenReturn(3);

        mockMvc.perform(get("/api/v1/productos/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }
}