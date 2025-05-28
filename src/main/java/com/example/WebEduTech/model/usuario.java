package com.example.WebEduTech.model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class usuario {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY )
    private long id;
    private String nombre;
    private String email;
    private String password;
    
    public static Optional<usuario> map(Object o){// Metodo automatico para enviar el objeto usuario a la base de datos
        throw new UnsupportedOperationException("Unimplemented method 'map' ");
    }
}
