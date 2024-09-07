
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
import org.utl.dsm.model.Producto;
        


@Path("producto")
public class RestProducto {
    private final ContorllerProducto controlador;

    public RestProducto() {
        this.controlador = new ContorllerProducto();
    }
    
    @WebFilter("/*")
    public static class CorsFilter implements Filter {
        @Override
        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) res;
            
            // Agregar los encabezados CORS necesarios
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            
            chain.doFilter(req, res);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            // Método de inicialización del filtro (puede dejarlo vacío)
        }

        @Override
        public void destroy() {
            // Método de destrucción del filtro (puede dejarlo vacío)
        }
    }
@Path("mostrarBebidas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
  public String obtenerBebidas() throws IOException {
    Gson gson = new Gson();
    List<Producto> imagenesBebidas = controlador.getProductosCategoriaBebidas();
    return gson.toJson(imagenesBebidas);
}
  @Path("mostrarEntradas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
  public String obtenerEntradas() throws IOException {
    Gson gson = new Gson();
    List<Producto> imagenesBebidas = controlador.getProductosCategoriaEntradas();
    return gson.toJson(imagenesBebidas);
}
  @Path("mostrarPostres")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
  public String obtenerPostres() throws IOException {
    Gson gson = new Gson();
    List<Producto> imagenesPostres = controlador.getProductosCategoriaPostres();
    return gson.toJson(imagenesPostres);
}
  @Path("mostrarPlatillos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
  public String obtenerPlatillos() throws IOException {
    Gson gson = new Gson();
    List<Producto> imagenesPlatillos= controlador.getProductosCategoriaPlatillos();
    return gson.toJson(imagenesPlatillos);
}
  @Path("mostrar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
  public String obtenerProductos() throws IOException {
    Gson gson = new Gson();
    List<Producto> imagenesPlatillos= controlador.getProductos();
    return gson.toJson(imagenesPlatillos);
}
//  @Path("insertar")
//@POST
//  @Produces(MediaType.TEXT_PLAIN)
//    public Response insertarImagen(@FormParam("ruta") String rutaImagen) throws IOException {
//        try {
//            String rutaCompleta = "C:\\Users\\Paulina\\Pictures\\" + rutaImagen;
//        ImagenController ic = new ImagenController();
//        
//           
//            ic.insertarImagen(rutaCompleta);
//            return Response.status(Response.Status.CREATED).entity("Imagen insertada correctamente.").build();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al insertar la imagen.").build();
//        }
//    }
//
//  
  @Path("insertar")
  @POST
  
    public Response insertarProducto(
            @FormParam("nombreProducto") String nombreProducto,
            @FormParam("rutaImagen") String rutaImagen,
            @FormParam("idCategoria") int idCategoria,
            @FormParam("descripcion") String descripcion,
            @FormParam("precioUnitario") double precioUnitario,
             @FormParam("estatus") int estatus) {
        
        try {
             String rutaCompleta = "C:\\Users\\Paulina\\Pictures\\" + rutaImagen;
            ContorllerProducto pc = new ContorllerProducto();
            
            pc.insertarProducto(nombreProducto, rutaCompleta, idCategoria, descripcion, precioUnitario,estatus);
            return Response.status(Response.Status.CREATED).entity("Producto insertado correctamente.").build();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al insertar el producto.").build();
        }
    }
    
@Path("actualizar")
@POST
public Response actualizarProducto(
        @FormParam("idProducto") int idProducto,
        @FormParam("nombreProducto") String nombreProducto,
        @FormParam("rutaImagen") String rutaImagen,
        @FormParam("idCategoria") int idCategoria,
        @FormParam("descripcion") String descripcion,
        @FormParam("precioUnitario") double precioUnitario,
        @FormParam("estatus") int estatus) {

    try {
        String rutaCompleta = "C:\\Users\\Paulina\\Pictures\\" + rutaImagen;
        ContorllerProducto pc = new ContorllerProducto();

        pc.actualizarProducto(idProducto, nombreProducto, rutaCompleta, idCategoria, descripcion, precioUnitario, estatus);
        return Response.status(Response.Status.OK).entity("Producto actualizado correctamente.").build();
    } catch (SQLException | IOException e) {
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar el producto.").build();
    }
}
@Path("actualizarProducto")
@POST
public Response actualizarP(
        @FormParam("idProducto") int idProducto,
        @FormParam("nombreProducto") String nombreProducto,
        @FormParam("rutaImagen") String rutaImagen,
        @FormParam("idCategoria") int idCategoria,
        @FormParam("descripcion") String descripcion,
        @FormParam("precioUnitario") double precioUnitario,
        @FormParam("estatus") int estatus) {

    try {
        String rutaCompleta = null;
        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            // Se proporcionó una nueva ruta de imagen, construir la ruta completa
            rutaCompleta = "C:\\Users\\Paulina\\Pictures\\" + rutaImagen;
        }
        
        ContorllerProducto pc = new ContorllerProducto();
        pc.actualizarProductoDos(idProducto, nombreProducto, rutaCompleta, idCategoria, descripcion, precioUnitario, estatus);
        
        return Response.status(Response.Status.OK).entity("Producto actualizado correctamente.").build();
    } catch (SQLException | IOException e) {
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar el producto.").build();
    }
}

@Path("delete")
 @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response desactivarProducto(@FormParam("idProducto") int idProducto) {
    ContorllerProducto pc = new ContorllerProducto();
    try {
        pc.desactivarProducto(idProducto);
        return Response.ok("{\"result\":\"Producto desactivado correctamente\"}").build();
    } catch (Exception e) {
        e.printStackTrace();
        return Response.ok("{\"result\":\"Ha ocurrido un error al intentar desactivar el producto\"}").build();
    }
}

@Path("activate")
 @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response activarProducto(@FormParam("idProducto") int idProducto) {
    ContorllerProducto pc = new ContorllerProducto();
    try {
        pc.activarProducto(idProducto);
        return Response.ok("{\"result\":\"Producto activado correctamente\"}").build();
    } catch (Exception e) {
        e.printStackTrace();
        return Response.ok("{\"result\":\"Ha ocurrido un error al intentar activar el producto\"}").build();
    }
}
     @Path("buscar")
@Produces(MediaType.APPLICATION_JSON)
@GET
public Response buscarProducto(@QueryParam("criterio") @DefaultValue("") String criterio) {
        try {
            // Crear instancia del controlador de productos
            ContorllerProducto pc = new ContorllerProducto();
            
            // Llamar al método de búsqueda con el criterio proporcionado
            List<Producto> resultados = pc.buscarProducto(criterio);
            
            // Convertir resultados a JSON
            Gson gson = new Gson();
            String out = gson.toJson(resultados);
            
            // Retornar respuesta exitosa con los resultados en formato JSON
            return Response.ok(out).build();
        } catch (SQLException ex) {
            // Manejar excepción SQLException
            Logger.getLogger(RestProducto.class.getName()).log(Level.SEVERE, "Error al buscar producto", ex);
            // Retornar respuesta de error 500 - Internal Server Error
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al buscar producto").build();
        } catch (Exception ex) {
            // Manejar otras excepciones
            Logger.getLogger(RestProducto.class.getName()).log(Level.SEVERE, "Error en el servidor", ex);
            // Retornar respuesta de error 500 - Internal Server Error
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error en el servidor").build();
        }
    }
}