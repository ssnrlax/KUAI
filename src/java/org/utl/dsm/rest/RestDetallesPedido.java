package org.utl.dsm.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import org.utl.dsm.controller.ControllerDetallePedido;
import org.utl.dsm.model.DetallePedido;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("detalle-pedido")
public class RestDetallesPedido {

    private final ControllerDetallePedido controllerDetallePedido;

    public RestDetallesPedido() {
        this.controllerDetallePedido = new ControllerDetallePedido();
    }

    @GET
    @Path("estatus/{estatus}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDetallesPorEstado(@PathParam("estatus") int estatus) {
        List<DetallePedido> detalles = controllerDetallePedido.getDetallesPorEstado(estatus);
        Gson gson = new Gson();
        return gson.toJson(detalles);
    }

    @POST
@Path("actualizarEstatus")
@Consumes(MediaType.APPLICATION_JSON)
public void actualizarEstatusPedido(@QueryParam("idPedido") int idPedido, @QueryParam("estatus") int estatus, DetallePedido detallePedido) {
    // Aquí puedes utilizar idPedido, nuevoEstatus y detallePedido para actualizar el estado en la base de datos
    controllerDetallePedido.actualizarEstatusDetallePedido(idPedido, estatus);
}

}
