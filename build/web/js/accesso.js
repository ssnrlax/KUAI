
function iniciar()
{
    let u=document.getElementById("txtUsuario").value;
    let c=document.getElementById("txtContrasenia").value;
    
    u = normalizar(u);
    u = sanitizar(u);
    c = normalizar(c);
    c = sanitizar(c);
    
    let us={"nombreUsuario":u,"contrasenia":c};
    
    let parametros = {"usuario":JSON.stringify(us)};
    
    let ruta="http://localhost:8080/kuai/api/acceso/login";
    
    fetch(ruta,
    { method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: new URLSearchParams(parametros)
    }).then(response=>response.json()).then(
            response => {
            if(response.idUsuario !==0 && response.idUsuario !== null){
                  console.log("acceso valido");
           alert("ID Empleado: " + response.idEmpleado);
               
        localStorage.setItem("token", response.token);
        localStorage.setItem("idUsuario", response.idUsuario);
        localStorage.setItem("nombreUsuario", response.nombreUsuario);
        localStorage.setItem("idEmpleado", response.idEmpleado);
        localStorage.setItem("puesto", response.puesto);
        localStorage.setItem("nombre", response.nombre);
        localStorage.setItem("apellidoPaterno", response.apellidoPaterno);
          if(response.puesto === "Mesero"){
            console.log("Acceso a mesero");
            window.location.href = "../html/mesero.html";
        } else if(response.puesto === "Gerente") {
            console.log("Acceso a gerente");
            window.location.href = "../html/prueba.html";
        } else if(response.puesto === "Cocina") {
            console.log("Acceso a cocina");
            window.location.href = "../html/cocina.html";
        } else {
            console.log("Acceso válido pero no es mesero ni gerente");
            window.location.href = "http://localhost:8080/kuai/html/principal.html";
        }
    } else {
        console.log("Acceso denegado");
        alert("Usuario o contraseña incorrectos");
        document.getElementById("txtUsuario").value = "";
        document.getElementById("txtContrasenia").value = "";
    }
})
.catch(error => {
    console.error('Error durante la solicitud:', error);
    alert('Error durante la solicitud. Consulta la consola para obtener más detalles.');
});
    //armar peticion
}

function cerrarSesion(){
    let parametros = {
     t: localStorage.getItem("token")   
    };
    let ruta ="http://localhost:8080/sicefaaa/api/acceso/logout";
   fetch(ruta, {
    method: 'POST',
    headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
    body: new URLSearchParams(parametros)
})
.then(response => response.json())
.then(response => {
    if(response.result){
        localStorage.removeItem("token");
        window.location.href = "http://localhost:8080/sicefaaa/index_1.html";
    } else if(response.error){
        alert(response.error);
    }
})
.catch(error => {
    console.error('Error durante la solicitud:', error);
    alert('Error durante la solicitud. Consulta la consola para obtener más detalles.');
});

}

function normalizar(texto){
    for (var i = 0; i < texto.length; i++) {
    texto = texto.replace("á","a");
    texto = texto.replace("é","e");
    texto = texto.replace("í","i");
    texto = texto.replace("ó","o");
    texto = texto.replace("ú","u");
    texto = texto.replace("Á","A");
    texto = texto.replace("É","E");
    texto = texto.replace("Í","I");
    texto = texto.replace("Ó","O");
    texto = texto.replace("Ú","U");
    }
   return texto;
}

function sanitizar(texto){
    for (var i = 0; i < texto.length; i++) {
    texto = texto.replace("(","");
    texto = texto.replace(")","");
    texto = texto.replace("[","");
    texto = texto.replace("]","");
    texto = texto.replace(";","");
    texto = texto.replace("/","");
    texto = texto.replace(",","");
    texto = texto.replace("%","");
    texto = texto.replace("´","");
    }
    return texto;
}


function redirectToAnotherPage() {
  window.location.href = "html/login.html";
}
function handleKeyPress(event) {
    if (event.keyCode === 13) { // Verificar si la tecla presionada es "Enter"
        iniciar(); // Llamar a la función iniciar() cuando se presiona "Enter"
    }
}