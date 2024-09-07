/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.model;

import java.util.List;

/**
 *
 * @author monto
 */
public class Pedido {
    private int idPedido;
    private String fechaPedido;
    private int estatus;
    private Mesa mesa;
    private Usuario usuario;
    private List<DetallePedido> listaDP;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{");
        sb.append("idPedido=").append(idPedido);
        sb.append(", fechaPedido=").append(fechaPedido);
        sb.append(", estatus=").append(estatus);
        sb.append(", mesa=").append(mesa);
        sb.append(", usuario=").append(usuario);
        sb.append(", listaDP=").append(listaDP);
        sb.append('}');
        return sb.toString();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetallePedido> getListaDP() {
        return listaDP;
    }

    public void setListaDP(List<DetallePedido> listaDP) {
        this.listaDP = listaDP;
    }

    public Pedido() {
    }

    public Pedido(int idPedido, String fechaPedido, int estatus, Mesa mesa, Usuario usuario, List<DetallePedido> listaDP) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.estatus = estatus;
        this.mesa = mesa;
        this.usuario = usuario;
        this.listaDP = listaDP;
    }
    
    
}
