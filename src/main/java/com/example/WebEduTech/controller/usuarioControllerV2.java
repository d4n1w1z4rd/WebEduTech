package com.example.WebEduTech.controller;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebEduTech.model.usuario;
import com.example.WebEduTech.service.usuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.WebEduTech.assemblers.usuarioModelAssembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v2/usuario")
@CrossOrigin
@Tag(name = "Usuario", description = "Operaciones sobre los usuarios del sistema")
public class usuarioControllerV2 {
    @Autowired
    private usuarioService serv;

    @Autowired
    private usuarioModelAssembler assembler;

    @Operation(summary = "Registrar usuarios", description = "Registra un nuevo usuario en el sistema")
    @PostMapping(value = "/registrar", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<usuario>> registrar(@RequestBody usuario u) {
        usuario creado = serv.registrarUsuario(u);
        return ResponseEntity
                .created(linkTo(methodOn(usuarioControllerV2.class).registrar(creado)).toUri())
                .body(assembler.toModel(creado));
    }

     @Operation(summary = "Iniciar Sesión", description = "Autentica la sesión de un usuario registrado con email y password")
    @PostMapping(value = "/login", produces = MediaTypes.HAL_JSON_VALUE) //Método para iniciar sesión
    //Recibe un objeto usuario con email y contraseña
    public ResponseEntity<EntityModel<Map<String, String>>> login(@RequestBody usuario u) {
        Optional<usuario> user = serv.autenticar(u.getEmail(), u.getPassword());
        Map<String, String> response = new HashMap<>();
        if (user.isPresent()) {
            response.put("result", "OK");
            response.put("nombre", user.get().getNombre());
            response.put("email", user.get().getEmail()); 
            response.put("password", user.get().getPassword()); 
        } else {
            response.put("result", "Error");
        }
        
        EntityModel<Map<String, String>> model = EntityModel.of(response);
        model.add(linkTo(methodOn(usuarioControllerV2.class).login(u)).withSelfRel());
        model.add(linkTo(methodOn(usuarioControllerV2.class).registrar(new usuario())).withRel("registrar"));

        return ResponseEntity.ok(model);
    }
}
