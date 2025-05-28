package com.example.WebEduTech.service;

import com.example.WebEduTech.model.Producto;
import com.example.WebEduTech.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos(){
         return productoRepository.obtenerProductos();
    }
    
    public Producto saveProducto(Producto producto){
        return productoRepository.guardar(producto);    
    }

    public Producto getProductoId(int id){
        return productoRepository.buscarPorId(id);
    }

    public Producto updateProducto(Producto producto){
        return productoRepository.actualizar(producto);
    }

    public String deleteProducto(int id){
        productoRepository.eliminar(id);
        return "producto eliminado";
    }

    public int totalProductos(){
        return productoRepository.obtenerProductos().size();
    }

    public int totalProductosV2(){
        return productoRepository.totalProducto();
    }
}