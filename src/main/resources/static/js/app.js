// Este archivo contiene el código JavaScript para la gestión de producto en la aplicación web.
// Se utiliza para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los libros.
const API_URL = "http://localhost:8080/api/v1/productos"; // URL de la API para acceder a los producto
// Función para listar los producto en la tabla
// Se utiliza la API Fetch para obtener los datos de los productos desde el servidor
function listarProductos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(productos => {
            const tbody = document.querySelector("#tablaProductos tbody");
            tbody.innerHTML = "";
            productos.forEach(producto => {
                const fila = `
                    <tr>
                        <td>${producto.id}</td>
                        <td>${producto.titulo}</td>
                        <td>${producto.precio}</td>
                        <td>${producto.horas}</td>
                        <td>${producto.stock}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="eliminarProducto(${producto.id})">🗑️ Eliminar</button>
                            <button class="btn btn-warning btn-sm" onclick="buscarProducto(${producto.id})">✏️ Editar</button>
                            <button class="btn btn-warning btn-sm" onclick="carrito.agregarProducto(${producto.id})">✏️ Agregar</button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}
let productos = []; // Variable para almacenar la lista de producto
// Función para agregar un producto
function agregarProducto() {
    const titulo = document.getElementById("titulo").value;
    const precio = document.getElementById("precio").value;
    const horas = document.getElementById("horas").value;
    const stock = document.getElementById("stock").value;
    
    const nuevoProducto = {
        titulo,
        precio,
        horas,
        stock
    };
    // Enviar el nuevo producto al servidor
    // Se utiliza la API Fetch para enviar los datos al servidor
    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoProducto)
    })// Enviar el nuevo producto al servidor
    .then(response => response.json())
    .then(data => {
        alert("Producto agregado exitosamente");
        listarProductos();// Actualizar la tabla de producto
        limpiarFormulario();// Limpiar el formulario
    });
}
// Función para eliminar un producto
function eliminarProducto(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert("Producto eliminado exitosamente");
                listarProductos();
            }
        });
}
// Función para buscar un prodcuto por su ID y cargarlo en el formulario
// Se utiliza la API Fetch para obtener los datos del product desde el servidor
let productoenEdicionId = null; // Variable para almacenar el ID del libro en edición
function buscarProducto(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(producto => {
            document.getElementById("titulo").value = producto.titulo;
            document.getElementById("precio").value = producto.precio;
            document.getElementById("horas").value = producto.horas;
            document.getElementById("stock").value = producto.stock;

             // Guardar el ID del libro en edición
            productoenEdicionId = producto.id;
            // Cambiar el botón de agregar por actualizar
            const boton = document.getElementById("botonFormulario");
            if (boton) {
                boton.textContent = "Actualizar Producto";
                boton.onclick = function() {
                    actualizarProducto(producto.id);
                };
            }
        });
}
// Función para actualizar un producto
// Se utiliza la API Fetch para enviar los datos actualizados al servidor
function actualizarProducto(id) {
    const titulo = document.getElementById("titulo").value;
    const precio = document.getElementById("precio").value;
    const horas = document.getElementById("horas").value;
    const stock = document.getElementById("stock").value;

    const productoActualizado = {
        id: id,
        titulo: titulo,
        precio: precio,
        horas: horas,
        stock: stock
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(productoActualizado)
    })
    .then(response => response.json())
    .then(data => {
        alert("Producto actualizado exitosamente");
        listarProductos();
        limpiarFormulario();
    });
}
// Función para limpiar el formulario después de agregar o actualizar un libro
// Se utiliza para restaurar el formulario a su estado inicial
function limpiarFormulario() {
    document.getElementById("titulo").value = "";
    document.getElementById("precio").value = "";
    document.getElementById("horas").value = "";
    document.getElementById("stock").value = "";

    // Restaurar botón
    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Producto";
    boton.setAttribute("onclick", "agregarProducto()");

    // Resetear la variable global
    productoenEdicionId = null; // Resetear el ID después de limpiar
}

// Cargar productos al abrir la página

listarProductos();