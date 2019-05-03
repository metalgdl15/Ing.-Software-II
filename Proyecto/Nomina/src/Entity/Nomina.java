/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Adan
 */
public class Nomina {

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
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    
    
    private int id;
    private int tipo;
    private Date fechaInicio;
    private Date fechaFin;
    
    
   /* public static void main(String[] args) {
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        //fecha.setMonth(fecha.getMonth()+1);
        //fecha.setHours(fecha.getHours()+168);
        int dia= cal.get(Calendar.DAY_OF_MONTH);
        
        compararQuincena(1, 31);
        //System.out.println(cal.get(Calendar.DAY_OF_MONTH));
    }
    
    private static void compararQuincena(int mes, int dia){
        if (mes == 1){
            if(dia != 15 || dia !=31 )
                System.out.println("Fecha Invalida");
            }else{
                System.out.println("Fecha Valida");
            }
    }*/

    /**
     * @return the idNomina
     */
    
    
}
