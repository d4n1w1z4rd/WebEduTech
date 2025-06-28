package com.example.WebEduTech.assemblers;

import com.example.WebEduTech.controller.carritoController;
import com.example.WebEduTech.model.Producto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.RepresentationModelAssembler;


import org.springframework.stereotype.Component;

import org.springframework.lang.NonNull;

@Component
public class carritoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {
    @Override
    public @NonNull EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(carritoController.class).verCarrito()).withRel("verCarrito"),
                linkTo(methodOn(carritoController.class).eliminarProducto(producto.getId())).withRel("eliminarProducto"));                
    }
}
