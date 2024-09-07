  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.Producto;

/**
 *
 * @author Paulina
 */
public class ContorllerProducto {
  private final ConexionMysql conexionMysql;

    public ContorllerProducto() {
        this.conexionMysql = new ConexionMysql();
    }
public List<Producto> getProductos() {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT idProducto ,idCategoria ,nombreProducto, rutaImagen, descripcion, precioUnitario,estatus FROM producto";
    
    try (Connection conn = conexionMysql.open();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            int idProducto = rs.getInt("idProducto");
            String nombreProducto = rs.getString("nombreProducto");
            String rutaImagen = rs.getString("rutaImagen");
            byte[] imagenBytes = Files.readAllBytes(Paths.get(rutaImagen));
            String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
            int idCategoria = rs.getInt("idCategoria");
            String descripcion = rs.getString("descripcion");
            float precioUnitario = rs.getFloat("precioUnitario");
            int estatus = rs.getInt("estatus");
            
           Producto producto= new Producto(idProducto, nombreProducto, imagenBase64, idCategoria, descripcion, precioUnitario,estatus);
            productos.add(producto);
        }
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
    
    return productos;
}
    public List<Producto> getProductosCategoriaBebidas() {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT idProducto ,idCategoria ,nombreProducto, rutaImagen, descripcion, precioUnitario,estatus FROM producto WHERE idCategoria = (SELECT idCategoria FROM categoria WHERE nombre = 'bebidas')";
    
    try (Connection conn = conexionMysql.open();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            int idProducto = rs.getInt("idProducto");
            String nombreProducto = rs.getString("nombreProducto");
            String rutaImagen = rs.getString("rutaImagen");
            byte[] imagenBytes = Files.readAllBytes(Paths.get(rutaImagen));
            String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
            int idCategoria = rs.getInt("idCategoria");
            String descripcion = rs.getString("descripcion");
            float precioUnitario = rs.getFloat("precioUnitario");
            int estatus = rs.getInt("estatus");
            
           Producto producto= new Producto(idProducto, nombreProducto, imagenBase64, idCategoria, descripcion, precioUnitario,estatus);
            productos.add(producto);
        }
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
    
    return productos;
}
    
    
     public List<Producto> getProductosCategoriaPostres() {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT idProducto ,idCategoria ,nombreProducto, rutaImagen, descripcion, precioUnitario, estatus FROM producto WHERE idCategoria = (SELECT idCategoria FROM categoria WHERE nombre = 'postre')";
    
    try (Connection conn = conexionMysql.open();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            int idProducto = rs.getInt("idProducto");
            String nombreProducto = rs.getString("nombreProducto");
            String rutaImagen = rs.getString("rutaImagen");
            byte[] imagenBytes = Files.readAllBytes(Paths.get(rutaImagen));
            String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
            int idCategoria = rs.getInt("idCategoria");
            String descripcion = rs.getString("descripcion");
            float precioUnitario = rs.getFloat("precioUnitario");
             int estatus = rs.getInt("estatus");
            
           Producto producto= new Producto(idProducto, nombreProducto, imagenBase64, idCategoria, descripcion, precioUnitario,estatus);
            productos.add(producto);
        }
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
    
    return productos;
}
      public List<Producto> getProductosCategoriaPlatillos() {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT idProducto ,idCategoria ,nombreProducto, rutaImagen, descripcion, precioUnitario,estatus FROM producto WHERE idCategoria = (SELECT idCategoria FROM categoria WHERE nombre = 'platillos')";
    
    try (Connection conn = conexionMysql.open();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            int idProducto = rs.getInt("idProducto");
            String nombreProducto = rs.getString("nombreProducto");
            String rutaImagen = rs.getString("rutaImagen");
            byte[] imagenBytes = Files.readAllBytes(Paths.get(rutaImagen));
            String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
            int idCategoria = rs.getInt("idCategoria");
            String descripcion = rs.getString("descripcion");
            float precioUnitario = rs.getFloat("precioUnitario");
             int estatus = rs.getInt("estatus");
           Producto producto= new Producto(idProducto, nombreProducto, imagenBase64, idCategoria, descripcion, precioUnitario,estatus);
            productos.add(producto);
        }
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
    
    return productos;
}
    
    public void insertarProducto(String nombreProducto, String rutaImagen, int idCategoria, String descripcion, double precioUnitario,int estatus) throws SQLException, IOException {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = conexionMysql.open();
        String sql = "INSERT INTO producto (nombreProducto, rutaImagen, idCategoria, descripcion, precioUnitario,estatus) VALUES (?, ?, ?, ?, ?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nombreProducto);
        pstmt.setString(2, rutaImagen);
        pstmt.setInt(3, idCategoria);
        pstmt.setString(4, descripcion);
        pstmt.setDouble(5, precioUnitario);
        pstmt.setInt(6, estatus);
        pstmt.executeUpdate();
    } finally {
        if (pstmt != null) pstmt.close();
        if (conn != null) conexionMysql.close();
    }
}
    public void actualizarProducto(int idProducto, String nombreProducto, String rutaImagen, int idCategoria, String descripcion, double precioUnitario, int estatus) throws SQLException, IOException {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = conexionMysql.open();
        String sql = "UPDATE producto SET nombreProducto = ?, rutaImagen = ?, idCategoria = ?, descripcion = ?, precioUnitario = ?, estatus = ? WHERE idProducto = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nombreProducto);
        pstmt.setString(2, rutaImagen);
        pstmt.setInt(3, idCategoria);
        pstmt.setString(4, descripcion);
        pstmt.setDouble(5, precioUnitario);
        pstmt.setInt(6, estatus);
        pstmt.setInt(7, idProducto);
        pstmt.executeUpdate();
    } finally {
        if (pstmt != null) pstmt.close();
        if (conn != null) conexionMysql.close();
    }
}
public void actualizarProductoDos(int idProducto, String nombreProducto, String rutaImagen, int idCategoria, String descripcion, double precioUnitario, int estatus) throws SQLException, IOException {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = conexionMysql.open();
        String sql;
        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            // Se proporcionó una nueva ruta de imagen, actualizarla junto con los otros datos
            sql = "UPDATE producto SET nombreProducto = ?, rutaImagen = ?, idCategoria = ?, descripcion = ?, precioUnitario = ?, estatus = ? WHERE idProducto = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreProducto);
            pstmt.setString(2, rutaImagen);
            pstmt.setInt(3, idCategoria);
            pstmt.setString(4, descripcion);
            pstmt.setDouble(5, precioUnitario);
            pstmt.setInt(6, estatus);
            pstmt.setInt(7, idProducto);
        } else {
            // No se proporcionó una nueva ruta de imagen, mantener la ruta actual
            sql = "UPDATE producto SET nombreProducto = ?, idCategoria = ?, descripcion = ?, precioUnitario = ?, estatus = ? WHERE idProducto = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreProducto);
            pstmt.setInt(2, idCategoria);
            pstmt.setString(3, descripcion);
            pstmt.setDouble(4, precioUnitario);
            pstmt.setInt(5, estatus);
            pstmt.setInt(6, idProducto);
        }
        pstmt.executeUpdate();
    } finally {
        if (pstmt != null) pstmt.close();
        if (conn != null) conexionMysql.close();
    }
}
public void desactivarProducto(int idProducto) {
        String query = "{CALL DesactivarProducto(?)}";

        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            CallableStatement cstm = (CallableStatement) conn.prepareCall(query);

            // Configurar los parámetros del procedimiento almacenado
            cstm.setInt(1, idProducto);

            // Ejecutar el procedimiento almacenado
            cstm.execute();

            // Cerrar recursos
            cstm.close();
            connMysql.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones, podrías también lanzar una excepción personalizada si es necesario.
        }
    }

    public void activarProducto(int idProducto) {
        String query = "{CALL ActivarProducto(?)}";

        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            CallableStatement cstm = (CallableStatement) conn.prepareCall(query);

            // Configurar los parámetros del procedimiento almacenado
            cstm.setInt(1, idProducto);

            // Ejecutar el procedimiento almacenado
            cstm.execute();

            // Cerrar recursos
            cstm.close();
            connMysql.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones, podrías también lanzar una excepción personalizada si es necesario.
        }
    }
 public List<Producto> buscarProducto(String criterio) throws SQLException {
        String query = "{CALL buscarProducto(?)}";
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = connMysql.open();
        CallableStatement cstm = conn.prepareCall(query);

        try {
            cstm.setString(1, criterio); // Asigna el criterio de búsqueda al parámetro de la consulta SQL
            ResultSet rs = cstm.executeQuery();

            List<Producto> resultados = new ArrayList<>();

            while (rs.next()) {
                Producto producto = new Producto();

                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setRutaImagen(rs.getString("rutaImagen"));
            producto.setIdCategoria(rs.getInt("idCategoria"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecioUnitario(rs.getFloat("precioUnitario"));
            producto.setEstatus(rs.getInt("estatus"));

                resultados.add(producto);
            }
            rs.close();
            return resultados;
        } finally {
            connMysql.close();
            conn.close();
        }
    }
  public List<Producto> getProductosCategoriaEntradas() {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT idProducto ,idCategoria ,nombreProducto, rutaImagen, descripcion, precioUnitario,estatus FROM producto WHERE idCategoria = (SELECT idCategoria FROM categoria WHERE nombre = 'entradas')";
    
    try (Connection conn = conexionMysql.open();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        while (rs.next()) {
            int idProducto = rs.getInt("idProducto");
            String nombreProducto = rs.getString("nombreProducto");
            String rutaImagen = rs.getString("rutaImagen");
            byte[] imagenBytes = Files.readAllBytes(Paths.get(rutaImagen));
            String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
            int idCategoria = rs.getInt("idCategoria");
            String descripcion = rs.getString("descripcion");
            float precioUnitario = rs.getFloat("precioUnitario");
            int estatus = rs.getInt("estatus");
            
           Producto producto= new Producto(idProducto, nombreProducto, imagenBase64, idCategoria, descripcion, precioUnitario,estatus);
            productos.add(producto);
        }
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
    
    return productos;
}
}