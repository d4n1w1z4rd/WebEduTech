package com.example.WebEduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebEduTech.model.Producto;
import com.example.WebEduTech.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.WebEduTech.assemblers.productoModelAssembler;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v2/productos")

public class productoControllerV2 {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private productoModelAssembler assembler;

    @Operation(summary = "Lista los productos agregados al carrito de compras", description = "Lista los cursos al carrito de compras")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> listarProductos(){
        
        List<EntityModel<Producto>> productos = productoService.getProductos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(productos,
            linkTo(methodOn(productoControllerV2.class).listarProductos()).withSelfRel());
    }

    @Operation(summary = "Agrega los productos al carrito de compras", description = "Agrega un curso al carrito de compras")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> agregarProducto(@RequestBody Producto producto){
        Producto crear = productoService.saveProducto(producto);
        return ResponseEntity.created(linkTo(methodOn(productoControllerV2.class).buscarProducto(crear.getId())).toUri()).body(assembler.toModel(crear));
    }

    @Operation(summary = "Busca los productos agregados al carrito de compras", description = "Busca todos los cursos al carrito de compras")
    @GetMapping("{id}")
    public Producto buscarProducto(@PathVariable int id){
        return productoService.getProductoId(id);
    }

    @Operation(summary = "Actualiza los productos agregados al carrito de compras", description = "Actualiza los cursos al carrito de compras")
    @PutMapping("{id}")
    public Producto actualizarProducto(@PathVariable int id, @RequestBody Producto producto){
        return productoService.updateProducto(producto);
    }

    @Operation(summary = "Elimina los productos agregados al carrito de compras", description = "Elimina los cursos al carrito de compras")
    @DeleteMapping("{id}")
    public String eliminarProducto(@PathVariable int id){
        return productoService.deleteProducto(id);
    }

    @Operation(summary = "Elimina los productos agregados al carrito de compras", description = "Elimina los cursos al carrito de compras")
    @GetMapping(value=  "/total", produces = MediaTypes.HAL_JSON_VALUE)
    public int totalProductosV2(){
        return productoService.totalProductosV2();
    }

    
}
