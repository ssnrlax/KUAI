document.addEventListener('DOMContentLoaded', function () {
    mostrarMesas();
});

function mostrarMesas() {
    const idEmpleado = localStorage.getItem('idEmpleado');
    if (!idEmpleado) {
        console.error('No se encontró el idEmpleado en el localStorage.');
        return;
    }

    fetchMesas(idEmpleado)
        .then(mesas => {
            const mesasContainer = document.getElementById('mesas-container');
            mesas.forEach(mesa => {
                const card = crearTarjetaMesa(mesa);
                mesasContainer.appendChild(card);
            });
        })
        .catch(error => console.error('Error al obtener las mesas:', error));
}

function fetchMesas(idEmpleado) {
    return fetch(`http://localhost:8080/kuai/api/mesero/getAll?idEmpleado=${idEmpleado}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('No se pudo obtener la lista de mesas');
            }
            return response.json();
        });
}

function crearTarjetaMesa(mesaData) {
    const mesa = mesaData.mesa;
    const listaDP = mesaData.listaDP;

    const card = document.createElement('div');
    card.classList.add('card', 'mb-3');

    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');

    const cardTitle = document.createElement('h5');
    cardTitle.classList.add('card-title');
    cardTitle.textContent = `Mesa ${mesa.numMesa}`;

    const cardText = document.createElement('p');
    cardText.classList.add('card-text');
    cardText.textContent = obtenerDetallePedido(listaDP);

    cardBody.appendChild(cardTitle);
    cardBody.appendChild(cardText);
    card.appendChild(cardBody);

    return card;
}

function obtenerDetallePedido(listaDP) {
    let detallePedido = 'Detalle de pedido:\n';
    listaDP.forEach(item => {
        detallePedido += `${item.producto.nombreProducto} - Cantidad: ${item.cantidad}`;
        if (item.comentarios) {
            detallePedido += ` - Comentarios: ${item.comentarios} -\n`;
        } else {
            detallePedido += '\n';
        }
    });
    return detallePedido;
}
