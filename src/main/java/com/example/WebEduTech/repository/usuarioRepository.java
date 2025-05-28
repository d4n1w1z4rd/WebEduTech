package com.example.WebEduTech.repository;

import com.example.WebEduTech.model.usuario;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioRepository extends JpaRepository<usuario,Long>{
    Optional <usuario> findByEmail(String email);
}



