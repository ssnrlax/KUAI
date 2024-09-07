/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.model;

/**
 *
 * @author monto
 */
public class Empleado {
     private int idEmpleado;
    private String puesto;
  
    private String rol;
    private Persona persona;
    private Usuario usuario;
    private float salarioBruto;
    private int estatus;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado{");
        sb.append("idEmpleado=").append(idEmpleado);
        sb.append(", puesto=").append(puesto);
 
        sb.append(", rol=").append(rol);
        sb.append(", persona=").append(persona);
        sb.append(", usuario=").append(usuario);
        sb.append(", salarioBruto=").append(salarioBruto);
        sb.append(", estatus=").append(estatus);
        sb.append('}');
        return sb.toString();
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

   

  

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(float salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Empleado(int idEmpleado, String puesto, String rol, Persona persona, Usuario usuario, float salarioBruto, int estatus) {
        this.idEmpleado = idEmpleado;
        this.puesto = puesto;
      
        this.rol = rol;
        this.persona = persona;
        this.usuario = usuario;
        this.salarioBruto = salarioBruto;
        this.estatus = estatus;
    }

    public Empleado() {
    }

}