/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.dsm.controller.ControllerEmpleado;
import org.utl.dsm.model.Empleado;
import org.utl.dsm.model.Persona;

/**
 *
 * @author Paulina
 */
@Path("empleado")

public class RestEmpleado extends Application{
    @Path("insertEmpleado")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertEmpleado(
            @FormParam("datosEmpleado") @DefaultValue("") String empleado
    ){
        System.out.println(empleado);
        Gson gson = new Gson();
        ControllerEmpleado cp = new ControllerEmpleado();
        Persona p = gson.fromJson(empleado, Persona.class);
        
        try {
            cp.insertarEmpleado(p);
            return Response.ok("{\"result\":\"Empleado registrado correctamente\"}").build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.ok("{\"result\":\"Ha ocurrido un error al intentar registrar al empleado\"}").build();
        }
    }
    @Path("update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmpleado(@FormParam("datosEmpleado") @DefaultValue("{}") String empleado) {
        Gson gson = new Gson();
        ControllerEmpleado cp = new ControllerEmpleado();
        Persona p = gson.fromJson(empleado, Persona.class);

        try {
            Persona resultado = cp.actualizarEmpleado(p);

            if (resultado != null) {
                String resultadoJson = gson.toJson(resultado);
                return Response.ok("{\"result\":\"Empleado modificado correctamente\"}", MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok("{\"result\":\"Error al actualizar el empleado\"}", MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.ok("{\"result\":\"Error en el servidor al procesar la solicitud\"}", MediaType.APPLICATION_JSON).build();
        }
    }
     @Path("getAllEmpleados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmpleados() {
        try {
            ControllerEmpleado controladorEmpleado = new ControllerEmpleado();
            List<Empleado> empleados = controladorEmpleado.getAll();

            Gson gson = new Gson();
            String jsonResponse = gson.toJson(empleados);

            return Response.status(Response.Status.OK).entity(jsonResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la solicitud de empleados").build();
        }
    }
  
    
}
