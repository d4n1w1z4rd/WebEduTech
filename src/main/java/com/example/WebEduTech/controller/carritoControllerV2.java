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

//Importar libreria  de swagger para la documentacion de las APIs
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.WebEduTech.assemblers.carritoModelAssembler;

import com.example.WebEduTech.model.Producto;
import com.example.WebEduTech.service.ProductoService;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;


import org.springframework.http.ResponseEntity;


import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/carrito")
//La anotacion Tag se usa para agrupar y etiquetar los controladores dentro de la documentacion.
@Tag(name = "Carrito de compras", description = "Operaciones sobre el carrito de compras")
public class carritoControllerV2 {
    private final List<Producto> carrito = new ArrayList<>();

    @Autowired
    private ProductoService productoser;

    @Operation(summary = "Muestra los productos al carrito de compras", description = "Muestra todos los cursos al carrito de compras")

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

    @Operation(summary = "Muestra los productos agregados al carrito de compras", description = "Muestra todos los cursos al carrito de compras")
    @GetMapping
    public List<Producto> verCarrito(){
        return carrito;
    }
    
    @Operation(summary = "Vacia los productos agregados al carrito de compras", description = "Vacia todos los cursos al carrito de compras")
    @DeleteMapping("/Vaciar")
    public String vaciarCarrito(){
        carrito.clear();
        return "Carrito vacio";
    }

    @Operation(summary = "Elimina los productos agregados al carrito de compras", description = "Elimina todos los cursos al carrito de compras")
    @DeleteMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id){
        boolean eliminado = carrito.removeIf(producto -> producto.getId()==id);
        return eliminado ? "Producto ha sido eliminado del carrito" : "Producto no estaba en el carrito";
    }

    @Operation(summary = "Cuenta los productos agregados al carrito de compras", description = "Cuenta todos los cursos al carrito de compras")
    @GetMapping("/Total")
    public int totalProductosCarritos(){
        return carrito.size();
    }
}


