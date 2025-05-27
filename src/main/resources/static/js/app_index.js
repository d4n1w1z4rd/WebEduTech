const nombre = sessionStorage.getItem("nombre");
    if(nombre){
        document.getElementById("mensaje").textContent = `Bienvenid@, ${nombre}`;
    }
    function cerrarSesion(){
        sessionStorage.clear();
        window.location.href = "/login.html";
    }