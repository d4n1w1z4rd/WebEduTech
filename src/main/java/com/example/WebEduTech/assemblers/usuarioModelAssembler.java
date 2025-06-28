package com.example.WebEduTech.assemblers;
import com.example.WebEduTech.model.usuario;
import com.example.WebEduTech.controller.usuarioController;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.RepresentationModelAssembler;


import org.springframework.stereotype.Component;

import org.springframework.lang.NonNull;

@Component
public class usuarioModelAssembler implements RepresentationModelAssembler<usuario, EntityModel<usuario>> {
    @Override
    public @NonNull EntityModel<usuario> toModel(usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(usuarioController.class).registrar(null)).withSelfRel(),
                linkTo(methodOn(usuarioController.class).login(usuario)).withRel("login"));
               }
}
