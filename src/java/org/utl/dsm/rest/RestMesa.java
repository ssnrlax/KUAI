/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.rest;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.controller.ContorllerProducto;
import org.utl.dsm.controller.ControllerMesa;
import org.utl.dsm.model.Empleado;
import org.utl.dsm.model.Mesa;
import org.utl.dsm.model.Producto;

/**
 *
 * @author Paulina
 */
@Path("mesa")
public class RestMesa {
    private final ControllerMesa controlador;

    public RestMesa() {
        this.controlador = new ControllerMesa();
    }
    @Path("mostrarMeseros")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
  public String obtenerMeseros() throws IOException {
    Gson gson = new Gson();
    List<Empleado> meseros = controlador.obtenerMeseros();
    return gson.toJson(meseros);
}
   @Path("mostrarMesas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
  public String obtenerMesas() throws IOException {
    Gson gson = new Gson();
    List<Mesa> mesas = controlador.mostrarMesas();
    return gson.toJson(mesas);
}
  @Path("insertarMesa")
  @POST
  
    public Response insertarMesa(@FormParam("idEmpleado") int idEmpleado) {
        
        try {
            
            ControllerMesa cm = new ControllerMesa();
            
          cm.insertarMesa(idEmpleado);
            return Response.status(Response.Status.CREATED).entity("Mesa insertada correctamente").build();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al insertar la mesa.").build();
        }
    }
    @Path("editarMesa")
  @POST
  
    public Response actualizarMesa(@FormParam("numMesa") int numMesa,@FormParam("idEmpleado") int idEmpleado) {
        
        try {
            
            ControllerMesa cm = new ControllerMesa();
            
          cm.actualizarIdEmpleadoMesa(numMesa, idEmpleado);
            return Response.status(Response.Status.CREATED).entity("Mesa actualizada correctamente").build();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar la mesa.").build();
        }
    }
}
