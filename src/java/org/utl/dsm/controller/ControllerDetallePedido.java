package org.utl.dsm.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.DetallePedido;
import org.utl.dsm.model.Pedido;
import org.utl.dsm.model.Producto;

public class ControllerDetallePedido {

    private final ConexionMysql conexionMysql;

    public ControllerDetallePedido() {
        this.conexionMysql = new ConexionMysql();
    }

    public List<DetallePedido> getDetallesPorEstado(int estatus) {
        List<DetallePedido> detalles = new ArrayList<>();
        String sql = "SELECT pe.idPedido, dp.idProducto, dp.cantidad, dp.precioVenta, dp.comentarios, p.nombreProducto, pe.estatus "
                + "FROM detallePedido dp "
                + "JOIN producto p ON dp.idProducto = p.idProducto "
                + "JOIN pedido pe ON dp.idPedido = pe.idPedido "
                + "WHERE pe.estatus = ?";

        try (Connection conn = conexionMysql.open(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, estatus);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idProducto = rs.getInt("idProducto");
                    int cantidad = rs.getInt("cantidad");
                    double precioVenta = rs.getDouble("precioVenta");
                    String comentarios = rs.getString("comentarios");
                    String nombreProducto = rs.getString("nombreProducto");
                    int idPedido = rs.getInt("idPedido");

                    Producto producto = new Producto(idProducto, nombreProducto, null, 0, null, 0, 0);
                    DetallePedido detalle = new DetallePedido();
                    detalle.setIdPedido(idPedido);
                    detalle.setProducto(producto);
                    detalle.setCantidad(cantidad);
                    detalle.setPrecioVenta(precioVenta);
                    detalle.setComentarios(comentarios);
                    detalle.setEstatus(rs.getInt("estatus")); // Asignar el estatus del pedido

                    detalles.add(detalle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalles;
    }

    public void actualizarEstatusDetallePedido(int idPedido, int estatus) {
        String sql = "UPDATE pedido SET estatus = ? WHERE idPedido = ?";

        try (Connection conn = conexionMysql.open(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, estatus); // Utiliza nuevoEstatus en lugar de estatus
            pstmt.setInt(2, idPedido);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
