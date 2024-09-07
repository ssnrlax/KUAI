/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* 
 * Este script se encarga de manejar la interacción con una API para realizar operaciones CRUD en una base de datos de empleados.
 */

// Función para insertar un nuevo empleado en la base de datos
function insertarEmpleado() {
    // Define la ruta de la API donde enviaremos la solicitud para agregar un empleado nuevo

    let ruta = "http://localhost:8080/kuai/api/empleado/insertEmpleado";

    // Recolecta los datos ingresados por el usuario en el formulario

    let persona = {
        nombre: document.getElementById("txtNombre").value,
        apellidoPaterno: document.getElementById("txtApellidoP").value,
        apellidoMaterno: document.getElementById("txtApellidoM").value,
        genero: document.getElementById("txtGenero").value,
        fechaNacimiento: document.getElementById("txtFechaNacimiento").value,
        rfc: document.getElementById("txtRFC").value,
        domicilio: document.getElementById("txtDomicilio").value,
        telefono: document.getElementById("txtTelefono").value
    };

    let usuario = {
        nombreUsuario: document.getElementById("txtNombreUsuario").value,
        contrasenia: document.getElementById("txtContraseña").value,
        rolUsuario: document.getElementById("txtRolUsuario").value
    };

    let empleado = {
        puesto: document.getElementById("txtPuesto").value,
        rol: document.getElementById("txtRol").value,
        salarioBruto: parseFloat(document.getElementById("txtSalarioBruto").value)
    };

    // Agrupa los datos del empleado, su usuario y detalles laborales en un solo objeto

    persona.empleado = empleado;
    persona.usuario = usuario;

    // Prepara los datos a enviar en la solicitud fetch

    let params = {datosEmpleado: JSON.stringify(persona)};

    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: new URLSearchParams(params)
    };

    // Realiza la solicitud fetch para agregar el empleado

    fetch(ruta, requestOptions)
            .then(function (data) {
                if (!data.ok) {
                    throw new Error('Error en la solicitud al servidor');
                }
                return data.json();
            })
            .then(function (json) {
                console.log(json);


            })
            .catch(function (error) {
                console.error('Error:', error);
                alert('Por favor, rellena los datos para el nuevo empleado.');
            });

    // Limpia el formulario y muestra la lista actualizada de empleados

    limpiarFormulario();
    mostrarEmpleados();


}

// Función para formatear una fecha en el formato 'YYYY-MM-DD'
function formatFecha(fecha) {
    if (!fecha) {
        return ''; // Retorna una cadena vacía si la fecha es nula o indefinida
    }

    // Obtener los componentes de la fecha
    const [year, month, day] = fecha.split('-');

    // Formatear la fecha en el formato deseado (YYYY-MM-DD)
    return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`;
}

// Variable para almacenar el ID del empleado seleccionado para editar
let empleadoSeleccionadoId = null;

// Función para cargar los datos de un empleado en el formulario de edición
function cargarDatosEdicion(empleado) {
    // Almacena el ID del empleado seleccionado
    empleadoSeleccionadoId = empleado.persona.idPersona;
    // Obtener referencias a los elementos del formulario
    const idPersonaInput = document.getElementById('txtIdPersona');
    const nombreInput = document.getElementById('txtNombre');
    const apellidoPaternoInput = document.getElementById('txtApellidoP');
    const apellidoMaternoInput = document.getElementById('txtApellidoM');
    const generoInput = document.getElementById('txtGenero');
    const fechaNacimientoInput = document.getElementById('txtFechaNacimiento');
    const rfcInput = document.getElementById('txtRFC');
    const domicilioInput = document.getElementById('txtDomicilio');
    const telefonoInput = document.getElementById('txtTelefono');
    const nombreUsuarioInput = document.getElementById('txtNombreUsuario');
    const contraseñaInput = document.getElementById('txtContraseña');
    const rolUsuarioInput = document.getElementById('txtRolUsuario');
    const puestoInput = document.getElementById('txtPuesto');
    const rolInput = document.getElementById('txtRol');
    const salarioBrutoInput = document.getElementById('txtSalarioBruto');

    // Cargar los datos del empleado en el formulario
    idPersonaInput.value = empleadoSeleccionadoId;
    nombreInput.value = empleado.persona.nombre;
    apellidoPaternoInput.value = empleado.persona.apellidoPaterno;
    apellidoMaternoInput.value = empleado.persona.apellidoMaterno;
    generoInput.value = empleado.persona.genero;
    fechaNacimientoInput.value = formatFecha(empleado.persona.fechaNacimiento);
    rfcInput.value = empleado.persona.rfc;
    domicilioInput.value = empleado.persona.domicilio;
    telefonoInput.value = empleado.persona.telefono;
    nombreUsuarioInput.value = empleado.usuario.nombreUsuario;
    contraseñaInput.value = empleado.usuario.contrasenia;
    rolUsuarioInput.value = empleado.usuario.rolUsuario;
    puestoInput.value = empleado.puesto;
    rolInput.value = empleado.rol;
    salarioBrutoInput.value = empleado.salarioBruto;

    // Deshabilita la edición del nombre de usuario y contraseña
    nombreUsuarioInput.disabled = true;
    contraseñaInput.disabled = true;

}

// Función para editar los datos de un empleado
function editarEmpleado() {
    // Verifica si se ha seleccionado un empleado para editar
    if (empleadoSeleccionadoId) {
        empleadoSeleccionadoId = empleado.persona.idPersona;
        // Obtener referencias a los elementos del formulario

        const v_idPersona = document.getElementById('txtIdPersona');
        const v_nombre = document.getElementById('txtNombre');
        const v_apellidoPaterno = document.getElementById('txtApellidoP');
        const v_apellidoMaterno = document.getElementById('txtApellidoM');
        const v_genero = document.getElementById('txtGenero');
        const v_fechaNacimiento = document.getElementById('txtFechaNacimiento');
        const v_rfc = document.getElementById('txtRFC');
        const v_domicilio = document.getElementById('txtDomicilio');
        const v_telefono = document.getElementById('txtTelefono');
        const v_nombreUsuario = document.getElementById('txtNombreUsuario');
        const v_contraseña = document.getElementById('txtContraseña');
        const v_rolUsuario = document.getElementById('txtRolUsuario');
        const v_puesto = document.getElementById('txtPuesto');
        const v_rol = document.getElementById('txtRol');
        const v_salarioBruto = document.getElementById('txtSalarioBruto');

        // Define los datos actualizados del empleado
        let persona = {
            v_nombre: document.getElementById("txtNombre").value,
            v_apellidoPaterno: document.getElementById("txtApellidoP").value,
            v_apellidoMaterno: document.getElementById("txtApellidoM").value,
            v_genero: document.getElementById("txtGenero").value,
            v_fechaNacimiento: document.getElementById("txtFechaNacimiento").value,
            v_rfc: document.getElementById("txtRFC").value,
            v_domicilio: document.getElementById("txtDomicilio").value,
            v_telefono: document.getElementById("txtTelefono").value
        };

        let usuario = {
            v_nombreUsuario: document.getElementById("txtNombreUsuario").value,
            v_contrasenia: document.getElementById("txtContraseña").value,
            v_rol: document.getElementById("txtRolUsuario").value
        };

        let empleado = {
            v_puesto: document.getElementById("txtPuesto").value,
            v_rol: document.getElementById("txtRol").value,
            v_salarioBruto: parseFloat(document.getElementById("txtSalarioBruto").value)
        };

        persona.empleado = empleado;
        persona.usuario = usuario;


        // Llama a la función para actualizar los datos del empleado
        actualizarEmpleado(persona);
    } else {
        alert('Por favor, selecciona un empleado antes de editar.');
    }

    // Limpia el formulario y muestra la lista actualizada de empleados
    limpiarFormulario();
    mostrarEmpleados();
}

// Función para editar los datos de un empleado en el servidor
function editarEmpleado(empleado) {
    // Obtiene la ruta del endpoint para actualizar empleado
    const ruta = "http://localhost:8080/kuai/api/empleado/update";

    // Crea un objeto URLSearchParams para enviar los datos al servidor
    const params = new URLSearchParams();
    params.append('datosEmpleado', JSON.stringify(empleado));

    // Configura las opciones para la solicitud fetch
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: params
    };

    // Realiza la solicitud fetch para actualizar los datos del empleado
    fetch(ruta, requestOptions)
            .then(function (data) {
                return data.json();// Convierte la respuesta a formato JSON
            })  
            .then(function (json) {
            })
            .catch(function (error) {
                // Maneja los errores de la solicitud fetch
                alert('Por favor, selecciona un empleado para editar.');
            });
}

function actualizarEmpleado() {
    idPersona = empleadoSeleccionadoId;

    let persona = {
        idPersona: document.getElementById("txtIdPersona").value,
        nombre: document.getElementById("txtNombre").value,
        apellidoPaterno: document.getElementById("txtApellidoP").value,
        apellidoMaterno: document.getElementById("txtApellidoM").value,
        genero: document.getElementById("txtGenero").value,
        fechaNacimiento: document.getElementById("txtFechaNacimiento").value,
        rfc: document.getElementById("txtRFC").value,
        domicilio: document.getElementById("txtDomicilio").value,
        telefono: document.getElementById("txtTelefono").value
    };

    let usuario = {
        nombreUsuario: document.getElementById("txtNombreUsuario").value,
        contrasenia: document.getElementById("txtContraseña").value,
        rolUsuario: document.getElementById("txtRolUsuario").value
    };

    let empleado = {
        puesto: document.getElementById("txtPuesto").value,
        rol: document.getElementById("txtRol").value,
        salarioBruto: parseFloat(document.getElementById("txtSalarioBruto").value)
    };

    persona.empleado = empleado;
    persona.usuario = usuario;

    console.log(persona);

    const ruta = "http://localhost:8080/kuai/api/empleado/update";

    const params = new URLSearchParams();
    params.append('datosEmpleado', JSON.stringify(persona));

    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: params
    };

    fetch(ruta, requestOptions)
            .then(function (data) {
                return data.json();
            })
            .then(function (json) {
                limpiarFormulario();
                mostrarEmpleados();
            })
            .catch(function (error) {
                alert('Por favor, selecciona un empleado para editar.');
            });
}

// Función para mostrar la lista de empleados en una tabla HTML
function mostrarEmpleados() {
    // Realiza una solicitud fetch para obtener la lista de empleados desde el servidor
    fetch(`http://localhost:8080/kuai/api/empleado/getAllEmpleados`)
            .then(response => response.json())
            .then(empleados => {
                // Crea una tabla HTML para mostrar los empleados
                const tabla = document.createElement('table');
                tabla.classList.add('table', 'table-striped', 'table-hover', 'table-bordered');

                // Crea la cabecera de la tabla
                const cabecera = document.createElement('thead');
                cabecera.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Rol</th>
                    <th>Puesto</th>
                    <th>Salario bruto</th>
                    <th>Estatus</th>
                    <th>Acciones</th>
                </tr>
            `;
                tabla.appendChild(cabecera);

                // Crea el cuerpo de la tabla y agrega cada empleado como una fila
                const cuerpo = document.createElement('tbody');
                empleados.forEach(empleado => {
                    console.log(empleados)
                    const fila = document.createElement('tr');
                    fila.innerHTML = `
                    <td>${empleado.idEmpleado}</td>
                    <td>${empleado.persona.nombre}</td>
                    <td>${empleado.persona.apellidoPaterno}</td>
                    <td>${empleado.rol}</td>
                    <td>${empleado.puesto}</td>
                    <td>${empleado.salarioBruto}</td>
                    <td>${empleado.estatus}</td>
            <td>
                         <button type="button" class="btn btn-danger" onclick="desactivarEmpleado(${empleado.persona.idPersona})">
            <ion-icon name="trash-outline"></ion-icon> <!-- Icono de papelera para eliminar -->
        </button>
        <button type="button" class="btn btn-success" onclick="activarEmpleado(${empleado.persona.idPersona})">
            <ion-icon name="checkmark-outline"></ion-icon> <!-- Icono de marca de verificación para activar -->
        </button>
                    </td>
                `;
                    // Agrega un evento click a la fila para cargar los datos del empleado en el formulario de edición
                    fila.addEventListener('click', () => cargarDatosEdicion(empleado));
                    cuerpo.appendChild(fila);
                });
                tabla.appendChild(cuerpo);

                // Obtiene el contenedor HTML donde se mostrará la tabla y lo actualiza
                const contenedor = document.getElementById('empleados-container');
                contenedor.innerHTML = ''; // Limpiar el contenedor antes de agregar la tabla
                contenedor.appendChild(tabla);
            })
            .catch(error => console.error(`Error al cargar los empleados:`, error));
}

// Función para desactivar un empleado
function desactivarEmpleado() {
    const idPersona = document.getElementById("txtIdPersona").value;

    // Crea un objeto con los datos del empleado desactivado
    const empleadoDesactivado = {
        idPersona: idPersona,
        activo: 0
    };
    // actualiza el estado del empleado con la funcion siguiente
    actualizarEstadoNoEmpleado(empleadoDesactivado);
}

function actualizarEstadoNoEmpleado(empleado) {
    const ruta = "http://localhost:8080/kuai/api/empleado/desactivar";

    const params = new URLSearchParams();
    params.append('datosEmpleado', JSON.stringify(empleado));

    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: params
    };

    fetch(ruta, requestOptions)
            .then(function (data) {
                return data.json();
            })
            .then(function (json) {
                mostrarEnTabla(json);
            });

    location.reload();
}

//funcion para activar un empleado
function activarEmpleado() {
    const idPersona = document.getElementById("txtIdPersona").value;

    // Crea un objeto con los datos del empleado activado
    const empleadoActivado = {
        idPersona: idPersona,
        activo: 1
    };

    // actualiza el estado del empleado con la funcion siguiente
    actualizarEstadoSiEmpleado(empleadoActivado);

}

function actualizarEstadoSiEmpleado(empleado) {
    const ruta = "http://localhost:8080/kuai/api/empleado/activar";

    const params = new URLSearchParams();
    params.append('datosEmpleado', JSON.stringify(empleado));

    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: params
    };

    fetch(ruta, requestOptions)
            .then(function (data) {
                return data.json();
            })
            .then(function (json) {
                mostrarEnTabla(json);
            });

    location.reload();
}

// Función para limpiar el formulario de entrada de datos
function limpiarFormulario() {
    // Restablece los valores de todos los campos del formulario a su estado inicial
    document.getElementById('txtNombre').value = '';
    document.getElementById('txtApellidoP').value = '';
    document.getElementById('txtApellidoM').value = '';
    document.getElementById('txtGenero').value = '';
    document.getElementById('txtFechaNacimiento').value = '';
    document.getElementById('txtRFC').value = '';
    document.getElementById('txtDomicilio').value = '';
    document.getElementById('txtTelefono').value = '';
    document.getElementById('txtRol').value = '';
    document.getElementById('txtPuesto').value = '';
    document.getElementById('txtSalarioBruto').value = '';
    document.getElementById('txtNombreUsuario').value = '';
    document.getElementById('txtContraseña').value = '';
    document.getElementById('txtRolUsuario').value = '';
}

// Función para buscar empleados en la lista según un criterio de búsqueda
function buscarEmpleado() {
    // Obtiene el criterio de búsqueda ingresado por el usuario
    const busqueda = document.getElementById('txtBusqueda').value.trim().toLowerCase();
    // Obtiene todas las filas de empleados en la tabla
    const empleados = Array.from(document.querySelectorAll('#empleados-container tbody tr'));

    // Itera sobre cada fila de empleado para verificar si coincide con el criterio de búsqueda
    empleados.forEach(empleado => {
        const camposEmpleado = empleado.querySelectorAll('td');
        let encontrado = false;

        // Itera sobre cada campo del empleado para compararlo con el criterio de búsqueda
        camposEmpleado.forEach(campo => {
            const textoCampo = campo.textContent.trim().toLowerCase();
            if (textoCampo.includes(busqueda)) {
                encontrado = true;
            }
        });

        // Muestra u oculta la fila según si se encontró una coincidencia con el criterio de búsqueda
        if (encontrado) {
            empleado.style.display = ''; // Mostrar el empleado si se encontró una coincidencia
        } else {
            empleado.style.display = 'none'; // Ocultar el empleado si no se encontraron coincidencias
        }
    });
}




