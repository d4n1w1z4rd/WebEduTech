package com.example.WebEduTech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebEduTech.model.usuario;

public interface  usuarioRepository extends JpaRepository<usuario,Long> {
    Optional <usuario> findByEmail(String email);
}

