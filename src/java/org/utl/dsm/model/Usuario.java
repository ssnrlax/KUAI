/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.model;

import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author monto
 */
public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String rolUsuario;
    private String token;

    public Usuario() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("idUsuario=").append(idUsuario);
        sb.append(", nombreUsuario=").append(nombreUsuario);
        sb.append(", contrasenia=").append(contrasenia);
        sb.append(", rolUsuario=").append(rolUsuario);
        sb.append(", token=").append(token);
        sb.append('}');
        return sb.toString();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario(int idUsuario, String nombreUsuario, String contrasenia, String rolUsuario, String token) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rolUsuario = rolUsuario;
        this.token = token;
    }
    
    public void encriptar(){
        this.nombreUsuario = DigestUtils.sha3_512Hex(this.nombreUsuario);
        this.contrasenia = DigestUtils.sha3_512Hex(this.contrasenia);
    }
    public void generarToken(){
        String p1= this.nombreUsuario;
        String p2= "Admins";
        Date fecha= new Date();  
        String p3= fecha.toString();
        String cadena = p1 + ":" + p2 + ":" + p3;
        String token= DigestUtils.md5Hex(cadena);
        this.token=token;
        
    } 
}
