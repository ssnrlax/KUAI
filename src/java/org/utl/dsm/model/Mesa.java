package org.utl.dsm.model;

public class Mesa {
    
    private int numMesa;
    private int estatus;
    private Empleado empleado;
    private String avisos;

    public Mesa(int numMesa, int estatus, Empleado empleado, String avisos) {
        this.numMesa = numMesa;
        this.estatus = estatus;
        this.empleado = empleado;
        this.avisos = avisos;
    }

    public Mesa() {
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "numMesa=" + numMesa +
                ", estatus=" + estatus +
                ", empleado=" + empleado +
                ", avisos='" + avisos + '\'' +
                '}';
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numeroMesa) {
        this.numMesa = numeroMesa;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getAvisos() {
        return avisos;
    }

    public void setAvisos(String avisos) {
        this.avisos = avisos;
    }
}
