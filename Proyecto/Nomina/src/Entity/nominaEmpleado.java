/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Adan
 */
public class nominaEmpleado {

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nomina
     */
    public Nomina getNomina() {
        return nomina;
    }

    /**
     * @param nomina the nomina to set
     */
    public void setNomina(Nomina nomina) {
        this.nomina = nomina;
    }

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the diasTrabajados
     */
    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    /**
     * @param diasTrabajados the diasTrabajados to set
     */
    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    /**
     * @return the sueldoTrabajo
     */
    public double getSueldoTrabajo() {
        return sueldoTrabajo;
    }

    /**
     * @param sueldoTrabajo the sueldoTrabajo to set
     */
    public void setSueldoTrabajo(double sueldoTrabajo) {
        this.sueldoTrabajo = sueldoTrabajo;
    }

    /**
     * @return the spe_isr
     */
    public double getSpe_isr() {
        return spe_isr;
    }

    /**
     * @param spe_isr the spe_isr to set
     */
    public void setSpe_isr(double spe_isr) {
        this.spe_isr = spe_isr;
    }

    /**
     * @return the infonavit
     */
    public double getInfonavit() {
        return infonavit;
    }

    /**
     * @param infonavit the infonavit to set
     */
    public void setInfonavit(double infonavit) {
        this.infonavit = infonavit;
    }

    /**
     * @return the cuotaImss
     */
    public double getCuotaImss() {
        return cuotaImss;
    }

    /**
     * @param cuotaImss the cuotaImss to set
     */
    public void setCuotaImss(double cuotaImss) {
        this.cuotaImss = cuotaImss;
    }

    /**
     * @return the censantiaVejez
     */
    public double getCensantiaVejez() {
        return censantiaVejez;
    }

    /**
     * @param censantiaVejez the censantiaVejez to set
     */
    public void setCensantiaVejez(double censantiaVejez) {
        this.censantiaVejez = censantiaVejez;
    }

    /**
     * @return the sueldoNeto
     */
    public double getSueldoNeto() {
        return sueldoNeto;
    }

    /**
     * @param sueldoNeto the sueldoNeto to set
     */
    public void setSueldoNeto(double sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }
    
    private int id;
    
    private Nomina nomina;
    private Empleado empleado;
    
    private int diasTrabajados;
    private double sueldoTrabajo;
    private double spe_isr;
    private double infonavit;
    private double cuotaImss;
    private double censantiaVejez;
    private double sueldoNeto;
}
