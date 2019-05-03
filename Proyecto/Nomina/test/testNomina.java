/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Empleado;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import Entity.Nomina;
import Entity.nominaEmpleado;
import Entity.tipoRegistro;
import dao.nominaDao;
import dao.empleadoDao;
import dao.nominaEmpleadoDao;
import daoMysql.nominaDaoMysql;
import daoMysql.empleadoDaoMysql;
import daoMysql.nominaEmpleadoDaoMysql;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Adan
 */
public class testNomina {
    
    nominaDao daoN = new nominaDaoMysql();
    nominaEmpleadoDao daoNE = new nominaEmpleadoDaoMysql();
    empleadoDao daoE = new empleadoDaoMysql();
    
    public testNomina() {
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    /*
    @Test
    public void agregarTest() throws ParseException{
        Nomina nomina = new Nomina();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        String inicio="2019-12-1";
        String fin ="2019-12-31";
        
        Date fechaInicio = format.parse(inicio);
        Date fechaFin = format.parse(fin);
        
        nomina.setFechaInicio(fechaInicio);
        nomina.setFechaFin(fechaFin);
        nomina.setTipo(3);
        daoN.Agrega(nomina);
        
        
        ////
        Nomina nomina2 = new Nomina();
        inicio = "2019-08-1";
        fin = "2019-08-15";
        
        fechaInicio = format.parse(inicio);
        fechaFin = format.parse(fin);
        
        nomina2.setFechaInicio(fechaInicio);
        nomina2.setFechaFin(fechaFin);
        nomina2.setTipo(2);
        daoN.Agrega(nomina2);
        
        ////
        Nomina nomina3 = new Nomina();
        inicio = "2019-02-13";
        fin = "2019-02-20";
        
        fechaInicio = format.parse(inicio);
        fechaFin = format.parse(fin);
        
        nomina3.setFechaInicio(fechaInicio);
        nomina3.setFechaFin(fechaFin);
        nomina3.setTipo(1);
        
        
        daoN.Agrega(nomina3);
        
    }
    */
    @Test
    public void agregarEmpleadoNomina(){
        Random ran = new Random();
        Nomina nomina = daoN.obtenNomina(35);
        List <Empleado> empleadoList = daoE.obtenTodos();
        
        /*for (int i=0; i<nominaList.size(); i++){
            Nomina nomina = nominaList.get(i);
            tipoRegistro tipo = tipoRegistro.registro(nomina.getTipo());
            System.out.println(nomina.getId()+" "+nomina.getFechaInicio()+" "+nomina.getFechaFin()+" "+" "+tipo);
        }*/
        
        int n = ran.nextInt(empleadoList.size());
        int j = ran.nextInt(7)+1;
        
        Empleado empleado = empleadoList.get(n);
        nominaEmpleado nominaEmp = new nominaEmpleado();
        
        nominaEmp.setEmpleado(empleado);
        nominaEmp.setNomina(nomina);
        
        nominaEmp.setDiasTrabajados(j);
        nominaEmp.setInfonavit(80);
        
        daoNE.Agrega(nominaEmp);
    }
    
    @Test
    public void mostrarNominas(){
        List <nominaEmpleado> nominaList = daoNE.obtenTodos();
        
        for (int i=0; i<nominaList.size(); i++){
            System.out.println(nominaList.get(i).getEmpleado().getNombre());
            System.out.println(nominaList.get(i).getNomina().getFechaInicio() +" - "+nominaList.get(i).getNomina().getFechaFin());
            System.out.println(nominaList.get(i).getSueldoNeto());
        }
    }
    
    @Test
    public void mostrarNominasEmpleados(){
        List <nominaEmpleado> nominaList = daoNE.obtenNominaEmpleado(18);
        
        for (int i=0; i<nominaList.size(); i++){
            System.out.println(nominaList.get(i).getEmpleado().getNombre());
            System.out.println(nominaList.get(i).getNomina().getFechaInicio() +" - "+nominaList.get(i).getNomina().getFechaFin());
            System.out.println(nominaList.get(i).getSueldoNeto());
        }
    }
    
    @Test
    public void mostrarNominasFecha(){
        List <nominaEmpleado> nominaList = daoNE.obtenNominaFecha(35);
        
        for (int i=0; i<nominaList.size(); i++){
            System.out.println(nominaList.get(i).getEmpleado().getNombre());
            System.out.println(nominaList.get(i).getNomina().getFechaInicio() +" - "+nominaList.get(i).getNomina().getFechaFin());
            System.out.println(nominaList.get(i).getSueldoNeto());
        }
    }
}
