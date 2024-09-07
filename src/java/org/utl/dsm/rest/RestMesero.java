/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.rest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.controller.ControllerEmpleado;
import org.utl.dsm.controller.ControllerMesero;
import org.utl.dsm.model.Empleado;
import org.utl.dsm.model.Mesa;
import org.utl.dsm.model.Pedido;

/**
 *
 * @author monto
 */

@Path("mesero")
public class RestMesero extends Application {
 @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("idEmpleado") int idEmpleado) {
       try {
            // Aquí llamamos al método del controlador para obtener los pedidos del mesero
            ControllerMesero controllerMesero = new ControllerMesero();
            List<Pedido> pedidos = controllerMesero.obtenerPedidosDeMesero(idEmpleado);
            
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(pedidos);

             return Response.status(Response.Status.OK).entity(jsonResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la solicitud de empleados").build();
        }
    
    }
}