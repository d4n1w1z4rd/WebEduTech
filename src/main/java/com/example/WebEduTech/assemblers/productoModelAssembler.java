package com.example.WebEduTech.assemblers;

import com.example.WebEduTech.model.Producto;
import com.example.WebEduTech.controller.productoController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;


@Component
public class productoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {
    @Override
    public @NonNull EntityModel<Producto> toModel(@NonNull Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(productoController.class).buscarProducto(producto.getId())).withSelfRel(),
                linkTo(methodOn(productoController.class).listarProductos()).withRel("productos"),
                linkTo(methodOn(productoController.class).eliminarProducto(producto.getId())).withRel("eliminar"),
                linkTo(methodOn(productoController.class).actualizarProducto(producto.getId(), producto)).withRel("actualizar"));
                

    }
}
