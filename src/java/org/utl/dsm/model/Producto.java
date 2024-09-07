/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.model;

/**
 *
 * @author monto
 */
public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String rutaImagen;
    private int idCategoria;
    private String descripcion;
    private double precioUnitario;
    private int estatus;

    public Producto() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{");
        sb.append("idProducto=").append(idProducto);
        sb.append(", nombreProducto=").append(nombreProducto);
        sb.append(", rutaImagen=").append(rutaImagen);
        sb.append(", idCategoria=").append(idCategoria);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", precioUnitario=").append(precioUnitario);
        sb.append(", estatus=").append(estatus);
        sb.append('}');
        return sb.toString();
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Producto(int idProducto, String nombreProducto, String rutaImagen, int idCategoria, String descripcion, double precioUnitario, int estatus) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.rutaImagen = rutaImagen;
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.estatus = estatus;
    }

}