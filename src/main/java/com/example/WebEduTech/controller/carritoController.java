package com.example.WebEduTech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebEduTech.model.Producto;
import com.example.WebEduTech.service.ProductoService;


@RestController
@RequestMapping("/api/v1/carrito")
public class carritoController {
    private final List<Producto> carrito = new ArrayList<>();

    @Autowired
    private ProductoService productoser;

    @PostMapping("/agregar/{id}")
    public String agregarProducto(@PathVariable int id){
        Producto producto = productoser.getProductoId(id);
        if (producto != null){
            if (producto.getStock()>0){
                producto.setStock(producto.getStock()-1);
                productoser.updateProducto(producto);
                carrito.add(producto);
                return "Producto agregado al carrito" + producto.getTitulo();
            } else {
                return "producto sin Stock";
            }
        }
        return "Producto no fue encontrado";
    }

    @GetMapping
    public List<Producto> verCarrito(){
        return carrito;
    }
    
    @DeleteMapping("/Vaciar")
    public String vaciarCarrito(){
        carrito.clear();
        return "Carrito vacio";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id){
        boolean eliminado = carrito.removeIf(producto -> producto.getId()==id);
        return eliminado ? "Producto ha sido eliminado del carrito" : "Producto no estaba en el carrito";
    }

    @GetMapping("/Total")
    public int totalProductosCarritos(){
        return carrito.size();
    }
}


