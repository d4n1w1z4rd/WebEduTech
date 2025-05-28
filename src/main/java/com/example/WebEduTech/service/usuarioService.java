package com.example.WebEduTech.service;

import com.example.WebEduTech.model.usuario;
import com.example.WebEduTech.repository.usuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class usuarioService {
    @Autowired
    private usuarioRepository repo;
    
    public usuario registrarUsuario(usuario u){
        return repo.save(u);
    }
    public Optional<usuario> autenticar(String email, String password){
        return repo.findByEmail(email).filter(u->u.getPassword().equals(password));
    }
}