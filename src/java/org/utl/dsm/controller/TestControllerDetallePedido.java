/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.controller;
import org.utl.dsm.controller.ControllerDetallePedido;
import org.utl.dsm.model.DetallePedido;

import java.util.List;

public class TestControllerDetallePedido {

    public static void main(String[] args) {
        testGetDetallesPorEstado();
        testActualizarEstatusDetallePedido();
    }

    private static void testGetDetallesPorEstado() {
        ControllerDetallePedido controllerDetallePedido = new ControllerDetallePedido();

        // Simular un estatus de pedido existente en tu base de datos
        int estatus = 1;

        List<DetallePedido> detalles = controllerDetallePedido.getDetallesPorEstado(estatus);

        // Imprimir los detalles obtenidos
        System.out.println("Detalles de pedidos con estatus " + estatus + ":");
        for (DetallePedido detalle : detalles) {
            System.out.println(detalle); // Asegúrate de tener un método toString en tu clase DetallePedido
        }
    }

    private static void testActualizarEstatusDetallePedido() {
        ControllerDetallePedido controllerDetallePedido = new ControllerDetallePedido();

        // ID del pedido que deseas actualizar
        int idPedido = 1;
        // Nuevo estatus que deseas asignar al pedido
        int nuevoEstatus = 1;

        // Actualizar el estatus del pedido
        controllerDetallePedido.actualizarEstatusDetallePedido(idPedido, nuevoEstatus);

        // Imprimir mensaje de confirmación
        System.out.println("Estatus del pedido con ID " + idPedido + " actualizado a " + nuevoEstatus);
    }
}

