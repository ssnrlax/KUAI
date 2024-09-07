/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.model;

/**
 *
 * @author monto
 */
public class DetallePedido {
    private int idPedido;
    private Producto producto;
    private Pedido pedido;
    private int estatus;
    private int cantidad;
    private double precioVenta;
    private String comentarios;

    public DetallePedido() {
    }

    public DetallePedido(int idPedido, Producto producto, Pedido pedido, int estatus, int cantidad, double precioVenta, String comentarios) {
        this.idPedido = idPedido;
        this.producto = producto;
        this.pedido = pedido;
        this.estatus = estatus;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.comentarios = comentarios;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    
}
