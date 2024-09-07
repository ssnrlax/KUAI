/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.Empleado;
import org.utl.dsm.model.Mesa;
import org.utl.dsm.model.Persona;

/**
 *
 * @author Paulina
 */
public class ControllerMesa {
     private final ConexionMysql conexionMysql;

    public ControllerMesa() {
        this.conexionMysql = new ConexionMysql();
    }
 public int insertarMesa(int idEmpleado) throws SQLException, IOException {
    String query = "{call InsertarMesa(?)}";
    int numMesa = 0; // Variable para almacenar el número de mesa insertada
    try {
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = connMysql.open();
        CallableStatement cstmt = conn.prepareCall(query);

        // Establecer el parámetro del procedimiento almacenado
        cstmt.setInt(1, idEmpleado);

        // Ejecutar el procedimiento almacenado
        cstmt.execute();
        ResultSet rs = cstmt.getGeneratedKeys();
        if (rs.next()) {
            numMesa = rs.getInt(1); // Obtener el número de mesa generado
        }

        cstmt.close();
        connMysql.close();
    } catch (SQLException e) {
        // Manejo de excepciones
        e.printStackTrace();
    }
    return numMesa; // Devolver el número de mesa insertada
}
 public List<Mesa> mostrarMesas() {
    List<Mesa> mesas = new ArrayList<>();
    String sql = "SELECT * FROM VistaMesa";

    try (Connection conn = conexionMysql.open();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            int numMesa = rs.getInt("numMesa");
            int estatus = rs.getInt("estatus");
            int idEmpleado = rs.getInt("idEmpleado");
            String avisos = rs.getString("avisos");
            int idPersona = rs.getInt("idPersona");
            String nombre = rs.getString("nombre");
            String apellidoPaterno = rs.getString("apellidoPaterno");

            // Crear un objeto Mesa con los datos obtenidos
            Mesa mesa = new Mesa();
            mesa.setNumMesa(numMesa);
            mesa.setEstatus(estatus);
            mesa.setAvisos(avisos);

            // Crear un objeto Empleado y asignarle los datos
            Empleado empleado = new Empleado();
            empleado.setIdEmpleado(idEmpleado);
            
            // Crear un objeto Persona y asignarle los datos
            Persona persona = new Persona();
            persona.setIdPersona(idPersona);
            persona.setNombre(nombre);
            persona.setApellidoPaterno(apellidoPaterno);
            
            // Asignar el objeto Persona al objeto Empleado
            empleado.setPersona(persona);
            
            // Asignar el objeto Empleado al objeto Mesa
            mesa.setEmpleado(empleado);
            
            mesas.add(mesa);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return mesas;
}

public List<Empleado> obtenerMeseros() {
    List<Empleado> meseros = new ArrayList<>();
    String sql = "SELECT e.idEmpleado, p.nombre, p.apellidoPaterno " +
                 "FROM Empleado e " +
                 "JOIN Persona p ON e.idPersona = p.idPersona " +
                 "WHERE e.puesto = 'Mesero'";

    try (Connection conn = conexionMysql.open();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            int idEmpleado = rs.getInt("idEmpleado");
            String nombre = rs.getString("nombre");
            String apellidoPaterno = rs.getString("apellidoPaterno");

            // Crear objeto Empleado y asignarle los datos
            Empleado mesero = new Empleado();
            mesero.setIdEmpleado(idEmpleado);
            
            // Crear objeto Persona y asignarle los datos
            Persona persona = new Persona();
            persona.setNombre(nombre);
            persona.setApellidoPaterno(apellidoPaterno);
            
            // Asignar objeto Persona al Empleado
            mesero.setPersona(persona);
            
            // Agregar el empleado a la lista
            meseros.add(mesero);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return meseros;
}
public void actualizarIdEmpleadoMesa(int numMesa, int idEmpleado) throws SQLException, IOException {
    String query = "{call ActualizarIdEmpleadoMesa(?, ?)}";
    
    try {
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = connMysql.open();
        CallableStatement cstmt = conn.prepareCall(query);

        // Establecer los parámetros del procedimiento almacenado
        cstmt.setInt(1, numMesa);
        cstmt.setInt(2, idEmpleado);

        // Ejecutar el procedimiento almacenado
        cstmt.executeUpdate();

        cstmt.close();
        connMysql.close();
    } catch (SQLException e) {
        // Manejo de excepciones
        e.printStackTrace();
    }
}

}
