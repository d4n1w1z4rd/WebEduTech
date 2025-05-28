package com.example.WebEduTech.repository;

import com.example.WebEduTech.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {
    // Arreglo que guardara todos los producto
    private List<Producto> listaProductos = new ArrayList<>();

    public ProductoRepository(){
        //Agregar Productos por defecto
        listaProductos.add(new Producto(10, "Python", 15990, 16,0));
        listaProductos.add(new Producto(20, "Java", 17990, 20,0));
        listaProductos.add(new Producto(30, "JavaScript", 21990, 24,0));
        listaProductos.add(new Producto(40, "GitHub", 19990, 18,0));
        listaProductos.add(new Producto(50, "PHP", 16990, 15,0));
        listaProductos.add(new Producto(60, "SQL", 25990, 24,0));
        listaProductos.add(new Producto(70, "HTML", 19990, 20,0));
        listaProductos.add(new Producto(80, "CSS", 17990, 18,0));
    }

    // Metodo que retorna todoa los libros
    public List<Producto> obtenerProductos() {
        return listaProductos;
    }

    // Buscar un producto por su id
    public Producto buscarPorId(int id) {
        for (Producto producto : listaProductos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }


    public Producto guardar(Producto pro) {
        // Generar nuevo ID secuencial
        long nuevoId = 1;
        for (Producto l : listaProductos) {
            if (l.getId() >= nuevoId) {
                nuevoId = l.getId() + 1;
            }
        }

        // Crear una nueva instancia con los datos del libro recibido
        Producto producto = new Producto();
        producto.setId((int) nuevoId); // ID generado automáticamente
        producto.setTitulo(pro.getTitulo());
        producto.setPrecio(pro.getPrecio());
        producto.setHoras(pro.getHoras());
        producto.setStock(pro.getStock());

        // Agregar el nuevo libro a la lista
        listaProductos.add(producto);

        return producto;
    }

    public Producto actualizar(Producto pro) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == pro.getId()) {
                id = pro.getId();
                idPosicion = i;
            }
        }

        Producto producto1 = new Producto();
        producto1.setId(id);
        producto1.setTitulo(pro.getTitulo());
        producto1.setPrecio(pro.getPrecio());
        producto1.setHoras(pro.getHoras());
        producto1.setStock(pro.getStock());

        listaProductos.set(idPosicion, producto1);
        return producto1;
    }

    public void eliminar(int id) {
        // alternativa 1
        // Libro libro = buscarPorId(id);
        // if (libro != null) {
        // listaLibros.remove(libro);
        // }
        //
        // // alternativa 2
        // int idPosicion = 0;
        // for (int i = 0; i < listaLibros.size(); i++) {
        // if (listaLibros.get(i).getId() == id) {
        // idPosicion = i;
        // break;
        // }
        // }
        // if (idPosicion > 0) {
        // listaLibros.remove(idPosicion);
        // }

        // otra alternativa
        listaProductos.removeIf(x -> x.getId() == id);
    }

    public int totalProducto() {
        return listaProductos.size();
    }
}
