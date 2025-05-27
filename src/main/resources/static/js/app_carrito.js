const carrito = (() => {
    const API = "/api/v1/carrito";

    //Funcion para mostrar el carrito 
    async function listarCarrito() {
        try{
            const response = await fetch(API);
            const productos = await response.json();
            const tbody = document.querySelector("#tablaCarrito tbody");
            const totalItems = document.getElementById("totalCarrito");
            tbody.innerHTML = "";
            totalItems.textContent = productos.length; 

            productos.forEach(producto => {
                const fila = `
                    <tr>
                        <td>${producto.id}</td>
                        <td>${producto.titulo}</td>
                        <td>${producto.precio}</td>
                        <td>
                            <button onclick ="carrito.eliminarProducto(${producto.id})">Eliminar</button>
                        </td>
                    </tr>    
                `;
                tbody.innerHTML += fila;
            });
        } catch (err){
            console.error("Error al cargar el carrito", err);
        }
    }    
    //Funcion para agregar un libro al carrito de compra
    async function agregarProducto(id) {
        try {
            await fetch(`${API}/agregar/${id}`, 
                {method: "POST"});
            alert("Producto agregado al carrito");
            listarCarrito();
        } catch (err){
            console.error("Error al agregar al carrito", err);
        }
    }
    //funcion para eliminar un libro en el carrito de compras
    async function eliminarProducto(id) {
        try{
            await fetch(`${API}/eliminar/${id}`,
                {method: "DELETE"});
                alert("producto ha sido eliminado del carrito");
                listarCarrito();
            }catch(err){
                console.error("Error al eliminar del carrito", err);
            }
    }

    //funcion par vaciar el carrito 
    async function vaciarCarrito() {
        if(confirm("¿Está seguro de vaciar el carrito de compra?")){
            await fetch(`${API}/vaciar`,
                {method: "DELETE"});
                alert("Producto ha sido eliminado del carrito");
                listarCarrito();
        }
        
    }
    async function confirmarCompra() {
        const total = document.getElementById("totalCarrito").textContent;
        if(parseInt(total)=== 0){
            alert ("El carrito esta vacio");
            return;
        }
        if(confirm(`desea confirmar su compra por ${total}`)){
            await fetch(`${API}/vaciar`, {method: "DELETE"});
            alert("Gracias por su compra")
            listarCarrito();
        }
        
    }

    function agregarNotificacion(mensaje) {
    let notificaciones = JSON.parse(localStorage.getItem('notificaciones')) || [];
    notificaciones.push(mensaje);
    localStorage.setItem('notificaciones', JSON.stringify(notificaciones));
}

// Ejemplo: después de una compra exitosa
agregarNotificacion("¡Compra realizada con éxito!");

// Ejemplo: después de cancelar un producto
agregarNotificacion("Producto cancelado correctamente.");
    return{listarCarrito, agregarProducto, eliminarProducto, vaciarCarrito, confirmarCompra};
})();