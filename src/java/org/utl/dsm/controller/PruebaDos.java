/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.controller;

import java.io.IOException;
import org.utl.dsm.bd.ConexionMysql;
import org.utl.dsm.model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.model.Empleado;
import org.utl.dsm.model.Mesa;
import org.utl.dsm.model.Persona;
import org.utl.dsm.model.Producto;


/**
 *
 * @author monto
 */
public class PruebaDos {
    public static void main(String[] args) {
probarEncriptacion();
//probarAcceso();
//probarConexion();
//probarLogout();
//probarObtenerEmpleadoPorUsuario();
//probarObtenerEmpleadoPorUsuarioo();
      //probarDesactivarProducto(1); // Aquí debes poner el ID del producto a desactivar
        //probarActivarProducto(1);
   //  probarBuscarProducto("Naranjada");
    // probarInsertarEmpleado();
     //probarActualizarEmpleado();
     //probarObtenerEmpleados();
    //activarEmpleado();
    //probarInsertarMesa();
    //probarMostrarMesas();
    //probarObtenerMeseros();
    //probarActualizarMesa();
    
    }
 public static void probarEncriptacion(){
    Usuario us = new Usuario();
    us.setNombreUsuario("mgonzalez");
    us.setContrasenia("password2");
    us.encriptar();
   us.generarToken();
     System.out.println(us.toString());
 }
 public static void probarConexion(){
     ConexionMysql objConexion = new ConexionMysql();
        Connection c = objConexion.open();
        System.out.println(c);
        objConexion.close();
        System.out.println(c.toString());
 }
 public static void probarAcceso(){
    AccesoController abjAC = new AccesoController();
    Usuario u = new Usuario();
    // credenciales correctas
    u.setNombreUsuario("MeseroJM");
    u.setContrasenia("mesero123");
    u.encriptar();
        try {
            abjAC.validarLogin(u);
            u.generarToken();
            abjAC.guardarToken(u);
        } catch (SQLException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
     System.out.println(u.toString());
}
 
 
 public static void probarLogout(){
     AccesoController obAC = new AccesoController();
        try {
            obAC.eliminarToken("4bcfc87b1c60c1d5deeb4bdeb529c60e");
        } catch (SQLException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 public static void probarObtenerEmpleadoPorUsuario() {
    Usuario usuario = new Usuario();
    usuario.setIdUsuario(2); // Establecer el ID del usuario según tu lógica
    
    AccesoController ac = new AccesoController(); // Suponiendo que así se llama tu clase Gestor de Empleados

    try {
        Empleado empleado = ac.obtenerEmpleadoPorUsuario(usuario);
     
        if (empleado != null) {
            System.out.println("Empleado encontrado:");
            System.out.println("ID Empleado: " + empleado.getIdEmpleado());
            // Si lo deseas, podrías imprimir más información del empleado aquí
        } else {
            System.out.println("No se encontró ningún empleado para el usuario.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(PruebaDos.class.getName()).log(Level.SEVERE, null, ex);
    }
}

 public static void probarObtenerEmpleadoPorUsuarioo() {
    Usuario usuario = new Usuario();
    usuario.setIdUsuario(2); // Establecer el ID del usuario según tu lógica
    
    AccesoController ac = new AccesoController(); // Suponiendo que así se llama tu clase AccesoController

    try {
        Empleado empleado = ac.obtenerEmpleadoPorUsuarioo(usuario);
     
        if (empleado != null) {
            System.out.println("Empleado encontrado:");
            System.out.println("ID Empleado: " + empleado.getIdEmpleado());
            System.out.println("Nombre: " + empleado.getPersona().getNombre());
            System.out.println("Apellido Paterno: " + empleado.getPersona().getApellidoPaterno());
            System.out.println("Puesto: " + empleado.getPuesto());
        } else {
            System.out.println("No se encontró ningún empleado para el usuario.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(PruebaDos.class.getName()).log(Level.SEVERE, null, ex);
    }
}
 public static void probarDesactivarProducto(int idProducto) {
        ContorllerProducto controller = new ContorllerProducto();
        
        controller.desactivarProducto(idProducto);
        System.out.println("Producto desactivado exitosamente.");
    }
    
    public static void probarActivarProducto(int idProducto) {
        ContorllerProducto controller = new ContorllerProducto();
        
        controller.activarProducto(idProducto);
        System.out.println("Producto activado exitosamente.");
    }
    public static void probarBuscarProducto(String criterio) {
    ContorllerProducto controller = new ContorllerProducto();

    try {
        List<Producto> productosEncontrados = controller.buscarProducto(criterio);

        if (!productosEncontrados.isEmpty()) {
            System.out.println("Productos encontrados:");
            for (Producto producto : productosEncontrados) {
                System.out.println("ID Producto: " + producto.getIdProducto());
                System.out.println("Nombre: " + producto.getNombreProducto());
                System.out.println("Ruta de la imagen: " + producto.getRutaImagen());
                System.out.println("ID de categoría: " + producto.getIdCategoria());
                System.out.println("Descripción: " + producto.getDescripcion());
                System.out.println("Precio unitario: " + producto.getPrecioUnitario());
                System.out.println("Estatus: " + producto.getEstatus());
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("No se encontraron productos para el criterio de búsqueda proporcionado.");
        }
    } catch (SQLException ex) {
        System.out.println("Error al buscar productos: " + ex.getMessage());
    }
}
    public static void probarInsertarEmpleado() {
    ControllerEmpleado controllerEmpleado = new ControllerEmpleado();

    // Crear una instancia de Persona con los datos del nuevo empleado
    Persona nuevoEmpleado = new Persona();
    nuevoEmpleado.setNombre("Juan");
    nuevoEmpleado.setApellidoPaterno("Pérez");
    nuevoEmpleado.setApellidoMaterno("González");
    nuevoEmpleado.setGenero("M");
    // Asignar la fecha de nacimiento (Asegúrate de tener la fecha en el formato adecuado)
    nuevoEmpleado.setFechaNacimiento("1995-01-01");
    nuevoEmpleado.setRfc("PERJ950101ABC");
    nuevoEmpleado.setDomicilio("Calle 123, Col. Centro");
    nuevoEmpleado.setTelefono("555-123-4567");

    // Crear una instancia de Empleado con los datos específicos del empleado
    Empleado datosEmpleado = new Empleado();
    datosEmpleado.setPuesto("Atencion Cliente");
    datosEmpleado.setRol("Mesero");
    datosEmpleado.setSalarioBruto(10000); // Ejemplo de salario

    // Crear una instancia de Usuario con los datos de inicio de sesión
    Usuario datosUsuario = new Usuario();
    datosUsuario.setNombreUsuario("MeseroJPG");
    datosUsuario.setContrasenia("password123");
    datosUsuario.setRolUsuario("Usuario");

    // Asignar los datos del empleado y usuario a la instancia de Persona
    nuevoEmpleado.setEmpleado(datosEmpleado);
    nuevoEmpleado.setUsuario(datosUsuario);

    // Insertar el nuevo empleado usando el controlador
    Persona empleadoInsertado = controllerEmpleado.insertarEmpleado(nuevoEmpleado);

    // Verificar si se insertó correctamente
    if (empleadoInsertado != null) {
        System.out.println("Empleado insertado correctamente con ID: " + empleadoInsertado.getIdPersona());
    } else {
        System.out.println("Error al insertar empleado.");
    }
}
    public static void probarActualizarEmpleado() {
    ControllerEmpleado controllerEmpleado = new ControllerEmpleado();

    // Crear una instancia de Persona con los datos del empleado a actualizar
    Persona empleadoActualizar = new Persona();
    empleadoActualizar.setIdPersona(1); // ID del empleado a actualizar
    empleadoActualizar.setNombre("Sarahi");
    empleadoActualizar.setApellidoPaterno("Dominguez");
    empleadoActualizar.setApellidoMaterno("Zendejas");
    empleadoActualizar.setGenero("F"); // Nuevo género
    // Asignar la nueva fecha de nacimiento (Asegúrate de tener la fecha en el formato adecuado)
    empleadoActualizar.setFechaNacimiento("1990-01-01");
    empleadoActualizar.setRfc("NVA123456789"); // Nuevo RFC
    empleadoActualizar.setDomicilio("Colonia Jardines de Jerez");
    empleadoActualizar.setTelefono("555-987-6543");

    // Crear una instancia de Empleado con los nuevos datos específicos del empleado
    Empleado datosEmpleado = new Empleado();
    datosEmpleado.setPuesto("Mesero");
    datosEmpleado.setRol("Atencion Cliente");
    datosEmpleado.setSalarioBruto(12000); // Nuevo salario

    // Crear una instancia de Usuario con los nuevos datos de inicio de sesión
    Usuario datosUsuario = new Usuario();
    datosUsuario.setNombreUsuario("MeseroSDZ");
    datosUsuario.setContrasenia("nuevacontraseña123");
    datosUsuario.setRolUsuario("Usuario");

    // Asignar los nuevos datos del empleado y usuario a la instancia de Persona
    empleadoActualizar.setEmpleado(datosEmpleado);
    empleadoActualizar.setUsuario(datosUsuario);

    // Actualizar el empleado usando el controlador
    Persona empleadoActualizado = controllerEmpleado.actualizarEmpleado(empleadoActualizar);

    // Verificar si se actualizó correctamente
    if (empleadoActualizado != null) {
        System.out.println("Empleado actualizado correctamente con ID: " + empleadoActualizado.getIdPersona());
    } else {
        System.out.println("Error al actualizar empleado.");
    }
}

public static void mostrarEmpleados(List<Empleado> empleados){
    if(empleados.isEmpty()){
        System.out.println("No se encontraron empleados");
    } else {
        System.out.println("Empleados encontrados:");
        for (Empleado empleado :  empleados) {
            System.out.println(empleado);
        }
}
}
public static void probarObtenerEmpleados() {
    // Instanciar la clase ControllerEmpleado
    ControllerEmpleado controllerEmpleado = new ControllerEmpleado();

    // Obtener la lista de empleados
    List<Empleado> empleados = controllerEmpleado.getAll();

    // Verificar si se obtuvieron empleados
    if (!empleados.isEmpty()) {
        System.out.println("Empleados encontrados:");
        for (Empleado empleado : empleados) {
            System.out.println("ID Empleado: " + empleado.getIdEmpleado());
            System.out.println("Nombre: " + empleado.getPersona().getNombre());
            System.out.println("Apellido Paterno: " + empleado.getPersona().getApellidoPaterno());
            System.out.println("Puesto: " + empleado.getPuesto());
            System.out.println("Rol: " + empleado.getRol());
            System.out.println("--------------------------------------");
        }
    } else {
        System.out.println("No se encontraron empleados.");
    }
}

    
public static void desactivarEmpleado() {
    ControllerEmpleado prueba = new ControllerEmpleado();
    int idPersona = 1; // Cambia este valor al ID del empleado que deseas desactivar
    prueba.desactivarEmpleado(idPersona);
}

public static void activarEmpleado() {
    ControllerEmpleado prueba = new ControllerEmpleado();
  
    int idPersona = 1; // Cambia este valor al ID del empleado que deseas activar
    prueba.activarEmpleado(idPersona);
}
public static void probarInsertarMesa() {
    ControllerMesa prueba = new ControllerMesa(); // Instancia de la clase de prueba
    int idEmpleado = 6; // ID del empleado asociado a la mesa (cambia este valor según tu lógica)
    
    try {
        int numMesaInsertada = prueba.insertarMesa(idEmpleado); // Llama al método para insertar la mesa
        if (numMesaInsertada > 0) {
            System.out.println("Mesa insertada correctamente con el número: " + numMesaInsertada);
        }
        // No imprimir ningún mensaje de error si la inserción se realiza correctamente
    } catch (SQLException | IOException ex) {
        System.out.println("Error al insertar la mesa: " + ex.getMessage());
    }
}
public static void probarMostrarMesas() {
    // Crear una instancia del controlador o gestor de mesas
    ControllerMesa accesoController = new ControllerMesa();
    
    // Llamar al método para mostrar las mesas
    List<Mesa> mesas = accesoController.mostrarMesas();
    
    // Verificar si se obtuvieron mesas
    if (!mesas.isEmpty()) {
        // Iterar sobre las mesas obtenidas
        for (Mesa mesa : mesas) {
            System.out.println("Número de Mesa: " + mesa.getNumMesa());
            System.out.println("Estatus: " + mesa.getEstatus());
            System.out.println("Avisos: " + mesa.getAvisos());
            
            // Obtener el empleado asociado a la mesa
            Empleado empleado = mesa.getEmpleado();
            if (empleado != null) {
                System.out.println("ID Empleado: " + empleado.getIdEmpleado());
                
                // Obtener la persona asociada al empleado
                Persona persona = empleado.getPersona();
                if (persona != null) {
                    System.out.println("Nombre del Empleado: " + persona.getNombre());
                    System.out.println("Apellido Paterno del Empleado: " + persona.getApellidoPaterno());
                } else {
                    System.out.println("Empleado sin datos de persona asociados.");
                }
            } else {
                System.out.println("Mesa sin empleado asociado.");
            }
            
            System.out.println("--------------------------------------");
        }
    } else {
        System.out.println("No se encontraron mesas.");
    }
}

public static void probarObtenerMeseros() {
    ControllerMesa controllerEmpleado = new ControllerMesa();

    // Obtener la lista de empleados meseros
    List<Empleado> meseros = controllerEmpleado.obtenerMeseros();

    // Verificar si se obtuvieron los meseros correctamente
    if (!meseros.isEmpty()) {
        System.out.println("Empleados meseros encontrados:");
        for (Empleado mesero : meseros) {
            System.out.println("ID Empleado: " + mesero.getIdEmpleado());
            System.out.println("Nombre: " + mesero.getPersona().getNombre());
            System.out.println("Apellido Paterno: " + mesero.getPersona().getApellidoPaterno());
            System.out.println("--------------------------------------");
        }
    } else {
        System.out.println("No se encontraron empleados meseros.");
    }
}
public static void probarActualizarMesa() {
    // Crear una instancia del controlador de mesas
    ControllerMesa controllerMesa = new ControllerMesa();
    
    int numMesa = 1; // Número de mesa a actualizar
    int idEmpleado = 8; // ID del empleado que atenderá la mesa
    
    try {
        // Llamar al método para actualizar la mesa con el empleado especificado
        controllerMesa.actualizarIdEmpleadoMesa(numMesa, idEmpleado);
        
        // Imprimir mensaje de éxito si la actualización se realiza correctamente
        System.out.println("Mesa actualizada correctamente con el empleado ID: " + idEmpleado);
    } catch (SQLException | IOException ex) {
        // Imprimir mensaje de error si ocurre alguna excepción
        System.out.println("Error al actualizar la mesa: " + ex.getMessage());
    }
}







}
