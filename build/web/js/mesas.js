/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function() {
    mostrarMesas();
});
const selectEmpleado = document.getElementById('selectEmpleado');

// Endpoint para obtener los empleados desde el backend
const url = 'http://localhost:8080/kuai/api/mesa/mostrarMeseros';

// Fetch para obtener los datos de los empleados desde el backend
fetch(url)
        .then(response => response.json())
        .then(data => {
            // Iterar sobre los datos de los empleados
            data.forEach(empleado => {
                // Crear una opción para cada empleado
                const option = document.createElement('option');
                option.value = empleado.idEmpleado; // Establecer el valor del empleado como el ID
                option.textContent = `${empleado.persona.nombre} ${empleado.persona.apellidoPaterno}`; // Mostrar nombre y apellido
                selectEmpleado.appendChild(option); // Agregar la opción al select
            });
        })
        .catch(error => {
            console.error('Error al obtener los datos de los empleados:', error);
        });

function insertarMesa() {
    const idEmpleado = document.getElementById('selectEmpleado').value;
    fetch('http://localhost:8080/kuai/api/mesa/insertarMesa', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `idEmpleado=${idEmpleado}`
    })
            .then(response => {
                if (response.ok) {
                    console.log('Mesa insertada correctamente');
                    Swal.fire('¡Mesa insertada correctamente!', '', 'success');

                    // Aquí podrías hacer algo después de insertar el producto, como recargar la página o mostrar un mensaje de éxito
                } else {
                    throw new Error('Error al insertar el producto.');
                }
            })
            .catch(error => console.error('Error:', error));

}




function mostrarMesas() {
    fetch('http://localhost:8080/kuai/api/mesa/mostrarMesas')
        .then(response => response.json())
        .then(data => {
            const mesasContainer = document.getElementById('mesas-container');
            mesasContainer.innerHTML = ''; // Limpiar contenido anterior

            // Iterar sobre las mesas recibidas
            data.forEach(mesa => {
                console.log('Número de mesa:', mesa.numMesa); 
                let estatusClass = '';
                let estatusText = '';

                if (mesa.estatus === 1) {
                    estatusClass = 'text-black';
                    estatusText = 'Ocupada';
                } else if (mesa.estatus === 2) {
                    estatusClass = 'text-danger';
                    estatusText = 'Deshabilitada';
                } else {
                    estatusClass = 'text-success';
                    estatusText = 'Disponible';
                }
                     const avisosText = mesa.avisos ? mesa.avisos : 'Sin avisos por el momento';

                // Crear la tarjeta de la mesa
                // Crear la tarjeta de la mesa
                 const card = document.createElement('div');
               card.classList.add('col-md-4');
                card.innerHTML =  `
                    <div >
                        <div class="card mesa-card border-secondary mb-3">
                            <div class="card-header" style="background-color: #e38a16;">
                                <h5><ion-icon name="wine-outline"></ion-icon> Mesa: ${mesa.numMesa}</h5>
                            </div>
                            <div class="card-body" style="background-color: #e9e9e9;">
                                <p class="card-text">Avisos: ${avisosText}</p>
                                <p class="card-text">Atendida por: ${mesa.empleado.persona.nombre} ${mesa.empleado.persona.apellidoPaterno}</p>
                            </div>
                            <div class="card-footer bg-transparent border-dark ${estatusClass}">Estatus: ${estatusText}
    </div>
            
                        </div>
                    </div>
                `;

                // Agregar la tarjeta al contenedor
                card.addEventListener('click', () => llenarFormulario(mesa));
                
                // Agregar la tarjeta al contenedor
                mesasContainer.appendChild(card);
 
            });
        })
        .catch(error => console.error('Error al obtener las mesas:', error));
}
// Obtener referencia al selector de empleados


let empleadoMesa = null;
let nombre = null;
let apellido = null;
let mesaSeleccionada = null;
function llenarFormulario(mesa) {
    mesaSeleccionada = mesa.numMesa;
    empleadoMesa = mesa.empleado.idEmpleado;
    nombre = mesa.empleado.persona.nombre;
    apellido = mesa.empleado.persona.apellidoPaterno;
    
      document.getElementById('txtEmpleado').value = `${mesa.empleado.persona.nombre} ${mesa.empleado.persona.apellidoPaterno}`;
    document.getElementById('idMesa').value = mesa.numMesa;

    // Mostrar los inputs
    document.getElementById('txtEmpleado').style.display = 'block';
    document.getElementById('idMesa').style.display = 'block';
      document.getElementById('lblEmpleado').style.display = 'block';
    document.getElementById('lblMesa').style.display = 'block';
document.getElementById('formularioDiv').style.display = 'block';
    // Log para verificar
    
        console.log('Número de mesa seleccionada:', mesaSeleccionada , empleadoMesa , nombre,apellido);
}

function actualizarMesa() {
    // Obtener los valores seleccionados
    const numMesaSeleccionada = document.getElementById('idMesa').value;
    const idEmpleadoSeleccionado = document.getElementById('selectEmpleado').value;

    // Fetch para actualizar la mesa
    fetch('http://localhost:8080/kuai/api/mesa/editarMesa', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `numMesa=${numMesaSeleccionada}&idEmpleado=${idEmpleadoSeleccionado}`
    })
    .then(response => {
        if (response.ok) {
            console.log('Mesa actualizada correctamente');
            // Aquí podrías hacer algo después de actualizar la mesa, como recargar la página o mostrar un mensaje de éxito
        } else {
            throw new Error('Error al actualizar la mesa.');
        }
    })
    .catch(error => console.error('Error:', error));
}
