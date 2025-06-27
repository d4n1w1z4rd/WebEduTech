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

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin
public class usuarioController {
    @Autowired
    private usuarioService serv;
    
    @PostMapping("/registrar")
    public usuario registrar(@RequestBody usuario u){
        return serv.registrarUsuario(u);
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody usuario u){
        Optional<usuario>user = serv.autenticar(u.getEmail(), u.getPassword());
        Map<String,String> response= new HashMap<>();
        if (user.isPresent()) {
            response.put("result", "OK");
            response.put("nombre", user.get().getNombre());
            //Solo para el testing
            response.put("email", user.get().getEmail());
            response.put("password", user.get().getPassword());
        }else{
            response.put("result", "error");
        }
        return response;
    }
}
