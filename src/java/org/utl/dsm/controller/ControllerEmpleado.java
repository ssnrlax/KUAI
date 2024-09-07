/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.controller;

import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.Empleado;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import org.utl.dsm.model.Persona;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.model.Usuario;

public class ControllerEmpleado {

    public Persona insertarEmpleado(Persona p) {
        String query = "{call InsertarEmpleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            CallableStatement cstmt = conn.prepareCall(query);

            // Establecer los parámetros del procedimiento almacenado
            cstmt.setString(1, p.getNombre());
            cstmt.setString(2, p.getApellidoPaterno());
            cstmt.setString(3, p.getApellidoMaterno());
            cstmt.setString(4, p.getGenero());
            cstmt.setString(5, p.getFechaNacimiento());
            cstmt.setString(6, p.getRfc());
            cstmt.setString(7, p.getDomicilio());
            cstmt.setString(8, p.getTelefono());
            cstmt.setString(9, p.getEmpleado().getPuesto());
            cstmt.setString(10, p.getEmpleado().getRol());
            cstmt.setFloat(11, p.getEmpleado().getSalarioBruto());
            cstmt.setString(12, p.getUsuario().getNombreUsuario());
            cstmt.setString(13, p.getUsuario().getContrasenia());
            cstmt.setString(14, p.getUsuario().getRolUsuario());

            // Ejecutar el procedimiento almacenado
            cstmt.execute();
            ResultSet rs = cstmt.getGeneratedKeys();
            if (rs.next()) {
                int idEmpleado = rs.getInt(1); // Obtener el ID del primer resultado
                p.getEmpleado().setIdEmpleado(idEmpleado); // Asignar el ID del empleado a la persona

            }

            cstmt.close();
            connMysql.close();
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
        return p;
    }

    public Persona actualizarEmpleado(Persona p) {
        String query = "{call actualizarEmpleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            CallableStatement cstmt = conn.prepareCall(query);

            // Establecer los parámetros del procedimiento almacenado
            cstmt.setInt(1, p.getIdPersona());
            cstmt.setString(2, p.getNombre());
            cstmt.setString(3, p.getApellidoPaterno());
            cstmt.setString(4, p.getApellidoMaterno());
            cstmt.setString(5, p.getGenero());
            cstmt.setString(6, p.getFechaNacimiento());
            cstmt.setString(7, p.getRfc());
            cstmt.setString(8, p.getDomicilio());
            cstmt.setString(9, p.getTelefono());
            cstmt.setString(10, p.getEmpleado().getPuesto());
            cstmt.setString(11, p.getEmpleado().getRol());
            cstmt.setFloat(12, p.getEmpleado().getSalarioBruto());
            cstmt.setString(13, p.getUsuario().getNombreUsuario());
            cstmt.setString(14, p.getUsuario().getContrasenia());
            cstmt.setString(15, p.getUsuario().getRolUsuario());

            // Ejecutar el procedimiento almacenado
            cstmt.execute();
            ResultSet rs = cstmt.getGeneratedKeys();
            if (rs.next()) {
                int idEmpleado = rs.getInt(1); // Obtener el ID del primer resultado
                p.getEmpleado().setIdEmpleado(idEmpleado); // Asignar el ID del empleado a la persona

            }

            cstmt.close();
            connMysql.close();
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
        return p;
    }

    public List<Empleado> getAll() {
        List<Empleado> empleados = new ArrayList<>();

        String query = "SELECT * FROM kuai.vista_empleado_usuario";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Empleado emp = new Empleado();
                Persona persona = new Persona();
                Usuario usuario = new Usuario();

                // Set Empleado fields
                emp.setIdEmpleado(rs.getInt("idEmpleado"));
                emp.setPuesto(rs.getString("puesto"));
                emp.setRol(rs.getString("rol"));
                emp.setEstatus(rs.getInt("estatus"));
                emp.setSalarioBruto(rs.getFloat("salarioBruto"));

                // Set Persona fields
                persona.setIdPersona(rs.getInt("idPersona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellidoPaterno(rs.getString("apellidoPaterno"));
                persona.setApellidoMaterno(rs.getString("apellidoMaterno"));
                persona.setGenero(rs.getString("genero"));
                persona.setFechaNacimiento(rs.getString("fechaNacimiento"));
                persona.setRfc(rs.getString("rfc"));
                persona.setDomicilio(rs.getString("domicilio"));
                persona.setTelefono(rs.getString("telefono"));

                // Set Persona in Empleado
                emp.setPersona(persona);

                // SET de usuario
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setContrasenia(rs.getString("contrasenia"));
                usuario.setRolUsuario(rs.getString("rolUsuario"));

                emp.setUsuario(usuario);
                // Add Empleado to the list
                empleados.add(emp);
            }
            rs.close();
            pstmt.close();
            connMysql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }

    public void desactivarEmpleado(int idPersona) {
        String query = "{call desactivarEmpleado(?)}";

        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            CallableStatement cstmt = conn.prepareCall(query);

            cstmt.setInt(1, idPersona);

            cstmt.execute();

            cstmt.close();
            connMysql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activarEmpleado(int idPersona) {
        String query = "{call activarEmpleado(?)}";

        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            CallableStatement cstmt = conn.prepareCall(query);

            cstmt.setInt(1, idPersona);

            cstmt.execute();

            cstmt.close();
            connMysql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
