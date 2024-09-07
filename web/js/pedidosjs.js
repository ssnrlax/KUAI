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
                circleDiv.classList.add(getColorForStatus(pedido.estatus));
                pedidoDiv.appendChild(circleDiv);
                
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

function cambiarEstatus(idPedido, nuevoEstatus) {
    actualizarEstadoPedido(idPedido, nuevoEstatus);
    // Luego puedes actualizar la vista de ser necesario
}

function actualizarEstadoPedido(idPedido, nuevoEstatus) {
    const url = `http://localhost:8080/kuai/api/detalle-pedido/estatus/${idPedido}`;
    const data = { estatus: nuevoEstatus };

    fetch(url, {
        method: 'POST', // Cambiado a POST
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        console.log('Estado actualizado correctamente');
        console.log(data);
    })
    .catch(error => {
        console.error('Error updating status:', error);
    });
}

function init() {
    const selectEstado = document.getElementById('estado');

    selectEstado.addEventListener('change', function() {
        const selectedValue = this.value;
        fetchPedidosPorEstado(selectedValue, mostrarPedidosEnPagina);
    });

    // Mostrar pedidos con estado 1 al cargar la página
    fetchPedidosPorEstado(1, mostrarPedidosEnPagina);
}

init(); // Iniciar la aplicación al cargar la página