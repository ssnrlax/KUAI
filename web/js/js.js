function cargarProductos(tipo) {
    fetch(`http://localhost:8080/kuai/api/producto/mostrar${tipo}`)
        .then(response => response.json())
        .then(productos => {
            const contenedor = document.getElementById(tipo);
            productos.forEach(producto => {
                // Crear una tarjeta (card) para mostrar los detalles del producto
                const divTarjeta = document.createElement('div');
                divTarjeta.classList.add('tarjeta');

                // Crear un elemento <img> y establecer su atributo src
                const img = document.createElement('img');
                img.src = `data:image/png;base64, ${producto.rutaImagen}`;
                img.alt = producto.nombre;
                img.classList.add('imagen');

                // Crear un elemento <p> para mostrar el nombre del producto
                const nombre = document.createElement('p');
                nombre.textContent = producto.nombreProducto;
                nombre.classList.add('nombre');

                // Crear un elemento <p> para mostrar la descripción del producto
                const descripcion = document.createElement('p');
                descripcion.textContent = producto.descripcion;
                descripcion.classList.add('descripcion');

                // Agregar los elementos a la tarjeta del producto
                divTarjeta.appendChild(img);
                divTarjeta.appendChild(nombre);
                divTarjeta.appendChild(descripcion);
  divTarjeta.addEventListener('click', () => llenarFormulario(producto));

                // Agregar la tarjeta del producto al contenedor correspondiente
                contenedor.appendChild(divTarjeta);
            });
        })
        .catch(error => console.error(`Error al cargar los productos de tipo ${tipo}:`, error));
}
function insertarProducto() {
    const nombreProducto = document.getElementById('nombreProducto').value;
    const inputImagen = document.getElementById('input-imagen');
    const idCategoria = document.getElementById('idCategoria').value;
    const descripcion = document.getElementById('descripcion').value;
    const precioUnitario = document.getElementById('precioUnitario').value;
    const estatus = 1; // Por defecto

    if (inputImagen.files.length > 0) {
        // Obtener la ruta de la imagen
        var rutaImagen = inputImagen.files[0].name;

        fetch('http://localhost:8080/kuai/api/producto/insertar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `nombreProducto=${nombreProducto}&rutaImagen=${rutaImagen}&idCategoria=${idCategoria}&descripcion=${descripcion}&precioUnitario=${precioUnitario}&estatus=${estatus}`
        })
        .then(response => {
            if (response.ok) {
                console.log('Producto insertado correctamente.');
           Swal.fire('¡Producto insertado correctamente!', '', 'success');
                        limpiarFormulario();
                        mostrarProductos();
                // Aquí podrías hacer algo después de insertar el producto, como recargar la página o mostrar un mensaje de éxito
            } else {
                throw new Error('Error al insertar el producto.');
            }
        })
        .catch(error => console.error('Error:', error));
    } else {
        console.error('No se seleccionó ningún archivo.');
    }
}

let idProductoSeleccionado = null;
function llenarFormulario(producto) {
    // Almacenar el ID del producto seleccionado
    idProductoSeleccionado = producto.idProducto;

    // Llenar los campos del formulario con los datos del producto
    document.getElementById('nombreProducto').value = producto.nombreProducto;
    document.getElementById('idCategoria').value = producto.idCategoria;
    document.getElementById('descripcion').value = producto.descripcion;
    document.getElementById('precioUnitario').value = producto.precioUnitario;
}

function actualizarProducto() {
     const idProducto = idProductoSeleccionado;
    const nombreProducto = document.getElementById('nombreProducto').value;
    const idCategoria = document.getElementById('idCategoria').value;
    const descripcion = document.getElementById('descripcion').value;
    const precioUnitario = document.getElementById('precioUnitario').value;
     const estatus = 1;
    // Obtener el nombre de la imagen si se selecciona un archivo
    let rutaImagen ='';
    const inputImagen = document.getElementById('input-imagen');
    if (inputImagen.files.length > 0) {
        // Obtener el nombre del archivo seleccionado
        rutaImagen = inputImagen.files[0].name;
    }
    

    // Construir el objeto con los datos actualizados
  
    // Realizar el fetch para actualizar el producto
    fetch('http://localhost:8080/kuai/api/producto/actualizarProducto', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        body:`idProducto=${idProducto}&nombreProducto=${nombreProducto}&rutaImagen=${rutaImagen}&idCategoria=${idCategoria}&descripcion=${descripcion}&precioUnitario=${precioUnitario}&estatus=${estatus}`
    })
    .then(response => {
        if (response.ok) {
            console.log('Producto actualizado correctamente.');
       Swal.fire('¡Producto editado correctamente!', '', 'success');
                    limpiarFormulario();
                    mostrarProductos();
            // Aquí podrías hacer algo después de actualizar el producto, como recargar la página o mostrar un mensaje de éxito
        } else {
            throw new Error('Error al actualizar el producto.');
        }
    })
    .catch(error => console.error('Error:', error));
}
function mostrarProductos() {
    fetch(`http://localhost:8080/kuai/api/producto/mostrar`)
        .then(response => response.json())
        .then(productos => {
            const tabla = document.createElement('table');
            tabla.classList.add('table', 'table-striped', 'table-hover','table-bordered'); // Agregar clases de Bootstrap a la tabla

            // Crear la cabecera de la tabla
            const cabecera = document.createElement('thead');
            const cabeceraRow = document.createElement('tr');
            cabecera.innerHTML = `
                <th>ID</th>
                <th>Nombre</th>
                <th>Imagen</th>
                <th>Descripción</th>
                <th>Precio Unitario</th>
                <th>Estatus</th>
                <th>Acciones</th> <!-- Agregar una columna para acciones -->
            `;
            cabecera.appendChild(cabeceraRow);
            tabla.appendChild(cabecera);

            // Crear el cuerpo de la tabla
            const cuerpo = document.createElement('tbody');
            productos.forEach((producto, index) => {
                const fila = document.createElement('tr');
                fila.innerHTML = `
                    <td>${producto.idProducto}</td>
                    <td>${producto.nombreProducto}</td>
                    <td><img src="data:image/png;base64, ${producto.rutaImagen}" alt="${producto.nombre}" class="imagen"></td>
                    <td>${producto.descripcion}</td>
                   <td>$${parseFloat(producto.precioUnitario).toFixed(2)}</td>
                    <td>${producto.estatus}</td>
                    <td>
                         <button type="button" class="btn btn-danger" onclick="eliminarProducto(${producto.idProducto})">
            <ion-icon name="trash-outline"></ion-icon> <!-- Icono de papelera para eliminar -->
        </button>
        <button type="button" class="btn btn-success" onclick="activarProducto(${producto.idProducto})">
            <ion-icon name="checkmark-outline"></ion-icon> <!-- Icono de marca de verificación para activar -->
        </button>
                    </td>
                `;
                fila.addEventListener('click', () => llenarFormulario(producto));
                cuerpo.appendChild(fila);

                // Aplicar estilos alternados a las filas
                if (index % 2 === 0) {
                    fila.classList.add('fila-par');
                } else {
                    fila.classList.add('fila-impar');
                }
            });
            tabla.appendChild(cuerpo);

            // Agregar la tabla al contenedor correspondiente
            const contenedor = document.getElementById('productos-container');
            contenedor.innerHTML = ''; // Limpiar el contenedor antes de agregar la tabla
            contenedor.appendChild(tabla);
        })
        .catch(error => console.error(`Error al cargar los productos:`, error));
}

function limpiarFormulario() {
    document.getElementById('nombreProducto').value = '';
    document.getElementById('idCategoria').value = '';
    document.getElementById('descripcion').value = '';
    document.getElementById('precioUnitario').value = '';
    document.getElementById('input-imagen').value = ''; // Limpiar el input de imagen
}
function eliminarProducto(idProducto) {
    fetch('http://localhost:8080/kuai/api/producto/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `idProducto=${idProducto}`
    })
    .then(response => {
        if (response.ok) {
            console.log('Producto desactivado correctamente.');
    Swal.fire('¡Producto desactivado correctamente!', '', 'success');
            // Aquí puedes hacer algo después de desactivar el producto, como recargar la tabla de productos
            limpiarFormulario();
            mostrarProductos();
        } else {
            throw new Error('Error al desactivar el producto.');
        }
    })
    .catch(error => console.error('Error:', error));
}
function activarProducto(idProducto) {
    fetch('http://localhost:8080/kuai/api/producto/activate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `idProducto=${idProducto}`
    })
    .then(response => {
        if (response.ok) {
            console.log('Producto activado correctamente.');
    Swal.fire('¡Producto activado correctamente!', '', 'success');
            // Aquí puedes hacer algo después de desactivar el producto, como recargar la tabla de productos
            limpiarFormulario();
            mostrarProductos();
        } else {
            throw new Error('Error al desactivar el producto.');
        }
    })
    .catch(error => console.error('Error:', error));
}
function filtrarTablaProductos(criterio) {
    // Convertir el criterio de búsqueda a minúsculas para una comparación insensible a mayúsculas y minúsculas
    const filtro = criterio.toLowerCase();
    
    // Obtener todas las filas de la tabla de productos
    const filas = document.querySelectorAll('#productos-container table tbody tr');

    // Iterar sobre cada fila y ocultar las que no coincidan con el criterio de búsqueda
    filas.forEach(fila => {
        // Obtener el contenido de la fila como texto
        const contenido = fila.textContent.toLowerCase();
        
        // Mostrar la fila si coincide con el criterio de búsqueda o si el criterio está vacío
        if (contenido.includes(filtro) || criterio.trim() === '') {
            fila.style.display = '';
        } else {
            fila.style.display = 'none';
        }
    });
}
document.addEventListener('DOMContentLoaded', function() {
    // Obtener referencia al campo de búsqueda
    const filtroInput = document.getElementById('filtroInput');

    // Agregar evento keyup para detectar cambios en el campo de búsqueda
    filtroInput.addEventListener('keyup', function() {
        // Obtener el valor del campo de búsqueda
        const criterio = filtroInput.value.trim();

        // Llamar a la función para filtrar la tabla de productos
        filtrarTablaProductos(criterio);
    });
});

