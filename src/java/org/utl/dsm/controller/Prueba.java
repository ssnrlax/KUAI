/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.dsm.model.Producto;

/**
 *
 * @author Paulina
 */
public class Prueba {
     public static void main(String[] args) {
        //probarObtenerImagenes();
//       probarInsertarProducto();
//probarObtenerImagenesPlatillos();
//probarObtenerImagenesS();
//probarInsertarProducto();
//probarActualizarProducto();
//probarActualizarProductoSinImagen();
//probarObtenerProductos();
probarEntradas();
    }
    public static void probarObtenerImagenes() {
        ContorllerProducto controller = new ContorllerProducto();
        List<Producto> producto = controller.getProductosCategoriaBebidas();
        System.out.println("Rutas de las imágenes obtenidas de la base de datos:");
        for (Producto ruta : producto) {
            System.out.println(ruta);
        }
    }
     public static void probarObtenerImagenesS() {
        ContorllerProducto controller = new ContorllerProducto();
        List<Producto> producto = controller.getProductosCategoriaPostres();
        System.out.println("Rutas de las imágenes obtenidas de la base de datos:");
        for (Producto ruta : producto) {
            System.out.println(ruta);
        }
    }
      public static void probarObtenerImagenesPlatillos() {
        ContorllerProducto controller = new ContorllerProducto();
        List<Producto> producto = controller.getProductosCategoriaPlatillos();
        System.out.println("Rutas de las imágenes obtenidas de la base de datos:");
        for (Producto ruta : producto) {
            System.out.println(ruta);
        }
    }
      public static void probarEntradas() {
        ContorllerProducto controller = new ContorllerProducto();
        List<Producto> producto = controller.getProductosCategoriaEntradas();
        System.out.println("Rutas de las imágenes obtenidas de la base de datos:");
        for (Producto ruta : producto) {
            System.out.println(ruta);
        }
    }
       public static void probarObtenerProductos() {
        ContorllerProducto controller = new ContorllerProducto();
        List<Producto> producto = controller.getProductos();
        System.out.println("Rutas de las imágenes obtenidas de la base de datos:");
        for (Producto ruta : producto) {
            System.out.println(ruta);
        }
    }
     public static void probarInsertarProducto() {
        ContorllerProducto controller = new ContorllerProducto();
        try {
            controller.insertarProducto("Naranjada", "C:\\Users\\Paulina\\Pictures\\logofosi.png", 2, "500ml", 9.99,1);
            System.out.println("Producto insertado exitosamente.");
        } catch (SQLException | IOException e) {
            System.err.println("Error al insertar producto en la base de datos: " + e.getMessage());
        }
    }
public static void probarActualizarProducto() {
        ContorllerProducto controller = new ContorllerProducto();
        try {
            controller.actualizarProducto(1, "Naranjada Mejorada", "C:\\Users\\Paulina\\Pictures\\logofosi.png", 2, "Nueva receta mejorada", 12.99, 1);
            System.out.println("Producto actualizado exitosamente.");
        } catch (SQLException | IOException e) {
            System.err.println("Error al actualizar producto en la base de datos: " + e.getMessage());
        }
    }
public static void probarActualizarProductoSinImagen() {
    ContorllerProducto controller = new ContorllerProducto();
    try {
        // Aquí se asume que 1 es el ID del producto que se quiere actualizar
        controller.actualizarProductoDos(1, "Nuevo nombre de producto","C:\\Users\\Paulina\\Pictures\\Imagen2.png", 2, "Nueva descripción del producto", 12.99, 1);
        System.out.println("Producto actualizado exitosamente sin cambiar la imagen.");
    } catch (SQLException | IOException e) {
        System.err.println("Error al actualizar producto en la base de datos: " + e.getMessage());
    }
}


}
