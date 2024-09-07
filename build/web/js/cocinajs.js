function fetchPedidosPorEstado(estatus, callback) {
    const url = 'http://localhost:8080/kuai/api/detalle-pedido/estatus/' + estatus;

    fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                callback(data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
}

function mostrarPedidosEnPagina(pedidos) {
    const pedidosDiv = document.querySelector('.pedidos');
    pedidosDiv.innerHTML = '';
    
    pedidos.forEach(pedido => {
        const pedidoDiv = document.createElement('div');
        pedidoDiv.classList.add('pedido');
        pedidoDiv.innerHTML = `
            <p>ID Pedido: ${pedido.idPedido}</p>
            <p>Producto: ${pedido.producto.nombreProducto}</p>
            <p>Cantidad: ${pedido.cantidad}</p>
            <p>Precio Venta: ${pedido.precioVenta}</p>
            <p>Comentarios: ${pedido.comentarios}</p>
        `;

        // Agregar un círculo del color correspondiente al estado
        const circleDiv = document.createElement('div');
        circleDiv.classList.add('circle');
        circleDiv.style.backgroundColor = getColorForStatus(pedido.estatus);
        pedidoDiv.appendChild(circleDiv);

        // Botones para cambiar el estatus
        const button1 = document.createElement('button');
        button1.textContent = 'Cambiar a Preparando';
        button1.classList.add('btn', 'btn-warning' , 'btn-sm'); // Agregar la clase custom-button al botón
        button1.addEventListener('click', function () {
            console.log('ID Pedido:', pedido.idPedido);
            console.log('Nuevo estatus:', 2);
            cambiarEstatus(pedido.idPedido, 2);
        });

        const button2 = document.createElement('button');
        button2.textContent = 'Cambiar a Por Entregar';
        button2.classList.add('btn', 'btn-primary','btn-sm', 'ml-3'); // Agregar la clase custom-button al botón
        button2.addEventListener('click', function () {
            console.log('ID Pedido:', pedido.idPedido);
            console.log('Nuevo estatus:', 3);
            cambiarEstatus(pedido.idPedido, 3);
        });

        pedidoDiv.appendChild(button1);
        pedidoDiv.appendChild(button2);

        pedidosDiv.appendChild(pedidoDiv);
    });
}

function getColorForStatus(status) {
    switch (status) {
        case 1:
            return 'red'; // Cambiar por el color correspondiente al estado 1
        case 2:
            return 'orange'; // Cambiar por el color correspondiente al estado 2
        case 3:
            return 'yellow'; // Cambiar por el color correspondiente al estado 3
        case 4:
            return 'green'; // Cambiar por el color correspondiente al estado 4
    }
}

function cambiarEstatus(idPedido, estatus) {
    const url = `http://localhost:8080/kuai/api/detalle-pedido/actualizarEstatus?idPedido=${idPedido}&estatus=${estatus}`;

    fetch(url, { method: 'POST' })
        .then(response => {
            if (!response.ok) {
                throw new Error('No se pudo cambiar el estado del pedido.');
            }
            console.log('Estado actualizado correctamente');
            // Recargar los pedidos después de actualizar el estado
            fetchPedidosPorEstado(1, mostrarPedidosEnPagina); // Cambiar el 1 por el estado correcto
        })
        .catch(error => {
            console.error('Error al actualizar el estado:', error);
        });
}



function init() {
    const selectEstado = document.getElementById('estado');

    selectEstado.addEventListener('change', function () {
        const selectedValue = this.value;
        fetchPedidosPorEstado(selectedValue, mostrarPedidosEnPagina);
    });

    // Mostrar pedidos con estado 1 al cargar la página
    fetchPedidosPorEstado(1, mostrarPedidosEnPagina);
}

init(); // Iniciar la aplicación al cargar la página
