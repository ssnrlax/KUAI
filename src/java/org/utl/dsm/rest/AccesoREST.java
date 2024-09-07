    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.utl.dsm.controller.AccesoController;
import org.utl.dsm.model.Empleado;
import org.utl.dsm.model.Persona;
import org.utl.dsm.model.Usuario;

/**
 *
 * @author Ana Paulina Duran Martinez
 * Fecha de actualizacion : 04/04/2024
 */

@Path("acceso")
public class AccesoREST {
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(@FormParam("usuario") @DefaultValue("") String usuario){
        Gson objGS = new Gson();
        
        Usuario u = objGS.fromJson(usuario, Usuario.class);
        System.out.println(u.toString());
        u.encriptar();
        
          
        String out = "";
        AccesoController objAc= new AccesoController();
       try {
           // Manda a llamar los metodos necesarios 
            objAc.validarLogin(u);
            u.generarToken();
            objAc.guardarToken(u);
            Empleado empleado = objAc.obtenerEmpleadoPorUsuarioo(u);
            
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("nombreUsuario", u.getNombreUsuario());
            responseJson.addProperty("idUsuario",u.getIdUsuario());
            responseJson.addProperty("token", u.getToken());
            responseJson.addProperty("rolUsuario",u.getRolUsuario());
            
            if (empleado != null) {
                // Agregar los datos del empleado al objeto JSON de respuesta
                responseJson.addProperty("idEmpleado", empleado.getIdEmpleado());
                responseJson.addProperty("puesto", empleado.getPuesto());
                
                //Agregar los datos de la persona al objeto JSON de respuesta
                Persona persona = empleado.getPersona();
                if (persona != null) {
                    responseJson.addProperty("nombre", persona.getNombre());
                    responseJson.addProperty("apellidoPaterno", persona.getApellidoPaterno());
                }
              
            }
            out= objGS.toJson(responseJson);
        } catch (SQLException ex) {
           out ="""
                {"Error":"Error interno de BD"}
                """;
        }
        
      
        return Response.status(Response.Status.OK).entity(out).build();
    }
    // iniciamos servicio para el cierre de sesion 
      @Path("logout")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logOut(@FormParam ("t") @DefaultValue("") String t){
        String out = "";
        AccesoController obAC = new AccesoController();
        try {
            // elimina el tokne de la sesion para que no haya registro de una sesion 
            obAC.eliminarToken(t);
            out = """
                  {"result":"OK"}
                  """;
        } catch (SQLException ex) {
            Logger.getLogger(AccesoREST.class.getName()).log(Level.SEVERE, null, ex);
             out = """
                  {"result":"Problemas con la BD, contacta al administrador del sistema"}
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
