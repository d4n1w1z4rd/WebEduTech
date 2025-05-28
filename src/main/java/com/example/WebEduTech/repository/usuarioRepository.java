package com.example.WebEduTech.repository;
import com.example.EduTech_Innovators_SPA.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class usuarioRepository extends JpaRepository<usuario,Long>{
    Optional <usuario> findByEmail(String email);
}

