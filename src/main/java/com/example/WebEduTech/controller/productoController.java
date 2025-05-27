package com.example.WebEduTech.controller;

import com.example.WebEduTech.model.Producto;
import com.example.WebEduTech.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")

public class productoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProductos(){
        return productoService.getProductos();
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto){
        return productoService.saveProducto(producto);
    }

    @GetMapping("{id}")
    public Producto buscarProducto(@PathVariable int id){
        return productoService.getProductoId(id);
    }

    @PutMapping("{id}")
    public Producto actualizarProducto(@PathVariable int id, @RequestBody Producto producto){
        return productoService.updateProducto(producto);
    }

    @DeleteMapping("{id}")
    public String eliminarProducto(@PathVariable int id){
        return productoService.deleteProducto(id);
    }

    @GetMapping("/total")
    public int totalProductosV2(){
        return productoService.totalProductosV2();
    }
}
