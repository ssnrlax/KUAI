/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.utl.dsm.model.Empleado;
import org.utl.dsm.model.Usuario;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.Persona;

/**
 *
 * @author Paulina
 */
public class AccesoController {
    public void validarLogin(Usuario us) throws SQLException{
        String query = """
                       select idUsuario from usuario where nombreUsuario= "%s" && contrasenia= "%s";
                       """;
        query = String.format(query, us.getNombreUsuario(), us.getContrasenia());
        
        ConexionMysql objConexion = new ConexionMysql();
        Connection obConnection = objConexion.open();
        Statement stmt = obConnection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        
        if (rs.next()){
            us.setIdUsuario(rs.getInt("idUsuario")); 
        }
        rs.close();
        stmt.close();
        obConnection.close();
        objConexion.close();
        
      
        
    }
    
     public Empleado obtenerEmpleadoPorUsuario(Usuario us) throws SQLException {
        Empleado empleado = null;
        // EXPRESIONES REGULARES nos ayudan a obtener informacion 
        String query = """
                       SELECT idEmpleado , rol , puesto
                       FROM empleado 
                       WHERE idUsuario = %d;
                       """;
        query = String.format(query, us.getIdUsuario());
        // abrimos conexiones 
        ConexionMysql objConexion = new ConexionMysql();
        Connection obConnection = objConexion.open();
        Statement stmt = obConnection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        if (rs.next()) {
            empleado = new Empleado();
            empleado.setIdEmpleado(rs.getInt("idEmpleado"));
//            empleado.setIdSucursal(rs.getInt("idSucursal"));
        }
        
        rs.close();
        stmt.close();
        obConnection.close();
        objConexion.close();
        
        
        return empleado;
    }
    
    public void guardarToken(Usuario us ) throws SQLException
    {
          String query = """
                       UPDATE usuario SET token="%s" WHERE idUsuario="%s";
                       """;
        query = String.format(query, us.getToken(), us.getIdUsuario());
        ConexionMysql ConnMySQL = new ConexionMysql();
        Connection conn = ConnMySQL.open();
        Statement stmt = conn.createStatement();
        stmt.execute(query);
        stmt.close();
        ConnMySQL.close();
        conn.close();
        
    }
    
    public void eliminarToken(String token) throws SQLException{
        String query = """
                       update usuario set token =""  where token="%s";
                       """;
        query = String.format(query, token);
        ConexionMysql objConexion = new ConexionMysql();
        Connection obConnection = objConexion.open();
        Statement stmt = obConnection.createStatement();
        stmt.execute(query);
        stmt.close();
        obConnection.close();
        objConexion.close();
    }
    
    public boolean validarToken(String token) throws SQLException{
        boolean respuesta = false;
        
        String query = "SELECT * FROM usuario WHERE token='"+token+"';";
        
        ConexionMysql connMySQL = new ConexionMysql();
        Connection conn = connMySQL.open();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()){
            respuesta = true;
        }
        rs.close();
        stmt.close();
        conn.close();
        connMySQL.close();
        
        return respuesta;
    }
   public Empleado obtenerEmpleadoPorUsuarioo(Usuario us) throws SQLException {
    Empleado empleado = null;
    String query = """
                   SELECT e.idEmpleado, p.nombre, e.puesto, p.apellidoPaterno
                   FROM empleado e
                   INNER JOIN persona p ON e.idPersona = p.idPersona
                   WHERE e.idUsuario = %d;
                   """;
    query = String.format(query, us.getIdUsuario());
    ConexionMysql objConexion = new ConexionMysql();
    Connection obConnection = objConexion.open();
    Statement stmt = obConnection.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    
    if (rs.next()) {
        empleado = new Empleado();
        empleado.setIdEmpleado(rs.getInt("idEmpleado"));
        empleado.setPuesto(rs.getString("puesto"));
        
        // Crear un objeto Persona y establecer sus atributos
        Persona persona = new Persona();
        persona.setNombre(rs.getString("nombre"));
        persona.setApellidoPaterno(rs.getString("apellidoPaterno"));
        // Ajusta este código según los atributos de la clase Persona
        
        // Establecer el objeto Persona en el Empleado
        empleado.setPersona(persona);
    }
    
    rs.close();
    stmt.close();
    obConnection.close();
    objConexion.close();
    
    return empleado;
}
}