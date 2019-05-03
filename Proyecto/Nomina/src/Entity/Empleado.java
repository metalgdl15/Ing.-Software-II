/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Adan
 */
public class Empleado {
    private int codigo;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private Date fehcaIngreso;
    private short antiguedad;
    private double sueldo;
    private double sdi;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  

    /**
     * @return the fehcaIngreso
     */
    public Date getFehcaIngreso() {
        return fehcaIngreso;
    }

    /**
     * @param fehcaIngreso the fehcaIngreso to set
     */
    public void setFehcaIngreso(Date fehcaIngreso) {
        this.fehcaIngreso = fehcaIngreso;
    }

    /**
     * @return the antiguedad
     */
    public short getAntiguedad() {
        return antiguedad;
    }

    /**
     * @param antiguedad the antiguedad to set
     */
    public void setAntiguedad(short antiguedad) {
        this.antiguedad = antiguedad;
    }

    /**
     * @return the sueldo
     */
    public double getSueldo() {
        return sueldo;
    }

    /**
     * @param sueldo the sueldo to set
     */
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * @return the sdi
     */
    public double getSdi() {
        return sdi;
    }

    /**
     * @param sdi the sdi to set
     */
    public void setSdi(double sdi) {
        this.sdi = sdi;
    }

    /**
     * @return the apellidoM
     */
    public String getApellidoM() {
        return apellidoM;
    }

    /**
     * @param apellidoM the apellidoM to set
     */
    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    /**
     * @return the apellidoP
     */
    public String getApellidoP() {
        return apellidoP;
    }

    /**
     * @param apellidoP the apellidoP to set
     */
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String toString(){
        return getApellidoP() + " " + getApellidoM()+ " " + getNombre();
    }
}

