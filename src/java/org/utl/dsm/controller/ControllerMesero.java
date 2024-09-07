/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.controller;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.DetallePedido;
import org.utl.dsm.model.Mesa;
import org.utl.dsm.model.Pedido;
import org.utl.dsm.model.Producto;

/**
 *
 * @author monto
 */
public class ControllerMesero {
   public List<Pedido> obtenerPedidosDeMesero(int idEmpleado) {
    List<Pedido> pedidos = new ArrayList<>();
    ConexionMysql conexion = new ConexionMysql();

    try (Connection connection = conexion.open();
         CallableStatement statement = connection.prepareCall("{call obtenerPedidosDeMesero(?)}")) {

        // Establecer el parámetro del procedimiento almacenado
        statement.setInt(1, idEmpleado);

        // Ejecutar el procedimiento almacenado
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int idPedido = rs.getInt("idPedido");
                Pedido pedido = null;

                // Buscar si el pedido ya existe en la lista
                for (Pedido existingPedido : pedidos) {
                    if (existingPedido.getIdPedido() == idPedido) {
                        pedido = existingPedido;
                        break;
                    }
                }

                // Si el pedido no existe, crear uno nuevo
                if (pedido == null) {
                    pedido = new Pedido();
                    pedido.setIdPedido(idPedido);
                    pedido.setFechaPedido(rs.getString("fechaPedido"));
                    pedido.setEstatus(rs.getInt("estatus"));

                    Mesa mesa = new Mesa();
                    mesa.setNumMesa(rs.getInt("idMesa"));
                    pedido.setMesa(mesa);

                    // Inicializar la lista de detalles de pedido
                    pedido.setListaDP(new ArrayList<>());

                    pedidos.add(pedido);
                }

                // Agregar los detalles del pedido
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setIdCategoria(rs.getInt("idCategoria"));
                producto.setPrecioUnitario(rs.getFloat("precioUnitario"));
                producto.setEstatus(rs.getInt("estatus"));

                DetallePedido detalle = new DetallePedido();
                detalle.setProducto(producto);
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioVenta(rs.getDouble("precioVenta"));
                detalle.setComentarios(rs.getString("comentarios"));

                pedido.getListaDP().add(detalle);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ControllerMesero.class.getName()).log(Level.SEVERE, null, ex);
    }

    return pedidos;
}
}
