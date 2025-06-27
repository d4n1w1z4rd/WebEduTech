document.getElementById("incidenciaForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const form = e.target;
    const nombre = form.nombre.value.trim();
    const descripcion = form.descripcion.value.trim();
    const estado = form.estado.value;

    if (!descripcion || !estado) {
        alert("Por favor completa todos los campos.");
        return;
    }

    fetch("http://10.155.66.226:8080/api/v1/incidencias/incidencia", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ nombre, descripcion, estado })
    })
    .then(response => {
        if (response.ok) {
            alert("Incidencia enviada correctamente.");
            form.reset();
        } else {
            alert("Error al enviar la incidencia.");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Error al conectar con el servidor.");
    });
});