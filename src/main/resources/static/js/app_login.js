/*API_URL = "http://localhost:8080/api/v1/usuario/login";

function login(){
    fetch(API_URL,{
        method: "POST",
        headers:{"Content-Type": "application/json"},
        body: JSON.stringify({
            email : document.getElementById("email").value,
            password : document.getElementById("password").value
        })
    })
    .then(response => response.json())
    .then(data => {
        if(data.result === "Ok"){
            sessionStorage.setItem("nombreUsuario", data.nombre);
            window.location.href = "/index.html";
        }else{
            alert("Acceso es denegado")
        }
    });
}*/
API_URL = "http://localhost:8080/api/v1/usuario/login"; //se conecta con el usuario

function login(){ //se sincronisa la conexion login
    fetch(API_URL, {
        method: "POST", //se agrega un objeto json
        headers:{"Content-Type": "application/json"}, // definiendo
        body: JSON.stringify({ //enviando la solicitud con el email y la clave
        email : document.getElementById("email").value,
        password : document.getElementById("password").value
        })
    })
    .then(response => response.json()) // crear la variable que maneja la respuesta del objeto
    .then(data => { //
        if(data.result === "OK"){
            sessionStorage.setItem("nombre", data.nombre);
            window.location.href = "/index.html";
        }else {
            alert("Acceso es denegado")
        }
            
    });
}