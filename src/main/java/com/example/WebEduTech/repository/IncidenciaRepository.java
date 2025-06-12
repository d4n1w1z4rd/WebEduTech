package com.example.WebEduTech.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.WebEduTech.model.Incidencia;



@Repository
public class IncidenciaRepository {
    private List<Incidencia> listaIncidencias = new ArrayList<>();

    public IncidenciaRepository() {
        // Agregar incidencias por defecto
        listaIncidencias.add(new Incidencia(1, "No me carg el curso "));
        listaIncidencias.add(new Incidencia(2, "No me permite comprar con mi tarjeta de credito"));
        listaIncidencias.add(new Incidencia(3, "No me salen las notas"));
        listaIncidencias.add(new Incidencia(4, "Compre el curso erroneo"));
    }

    public List<Incidencia> obtenerIncidencias(){
        return listaIncidencias;
    }
}
