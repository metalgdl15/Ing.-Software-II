/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Empleado;
import dao.empleadoDao;
import daoMysql.empleadoDaoMysql;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adan
 */
public class testEmpleado {
    
    public testEmpleado() {
    }
        Empleado empleado=new Empleado();
        empleadoDao dao= new empleadoDaoMysql();
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
    @Test
    public  void Agregatest()
    {
        
        empleado.setNombre("test");
        empleado.setApellidoM("materno");
        empleado.setApellidoP("apellidop");
        empleado.setSueldo(13.00);
        empleado.setFehcaIngreso(new Date());
        dao.agrega(empleado);   
    }
    
    @Test
    public void EliminarTest(){
        empleado.setCodigo(8);
        dao.Elimina(empleado);
    }
    
    @Test
    public void ModificarTest(){
        //TEST 1
        empleado.setCodigo(2);
        
        empleado.setNombre("test2");
        empleado.setApellidoM("materno2");
        empleado.setApellidoP("apellidop2");
        empleado.setSueldo(142.5);
        dao.Actualiza(empleado);
        
        //TEST 2
        empleado.setCodigo(8);
        
        empleado.setNombre("test3");
        empleado.setApellidoM("materno3");
        empleado.setApellidoP("apellidop3");
        empleado.setSueldo(0.0);
        empleado.setFehcaIngreso(new Date());
        dao.Actualiza(empleado);
    }
}
