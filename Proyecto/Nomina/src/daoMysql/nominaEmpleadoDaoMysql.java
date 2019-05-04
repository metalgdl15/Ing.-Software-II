/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoMysql;

import Entity.Empleado;
import Entity.Nomina;
import Entity.nominaEmpleado;
import dao.nominaEmpleadoDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adan
 */
public class nominaEmpleadoDaoMysql implements nominaEmpleadoDao{

    //Contstante de sueldo minimo
    private static final double threeUMA=241.8;
    
    /**
     * Para calcular el efecto de isr y spe
     */
    public double obtenerSpe_Isr(double sueldo, int tipo){
        Conexion conexion = new Conexion();
        conexion.newConnection();
        double spe_isr=0;
        
        String query = "SELECT (s.casMensual-(i.cuotaFija+((?-i.limiteInf)*i.excedentePorcentaje))) AS SPE_ISR "
                + "FROM isr i, subsidio s WHERE (i.limiteInf<=? AND i.limiteSup>=?) AND (s.paraIngresos<=? AND s.hastaIngresos>=?)"
                + "AND (i.tipo=? AND s.tipo=?)";
        
        PreparedStatement stmt;
        
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setDouble(1, sueldo);
            stmt.setDouble(2, sueldo);
            stmt.setDouble(3, sueldo);
            stmt.setDouble(4, sueldo);
            stmt.setDouble(5, sueldo);
            stmt.setInt(6, tipo);
            stmt.setInt(7, tipo);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                spe_isr = rs.getDouble(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("ERROR EN ISR_SPE");
            Logger.getLogger(nominaDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return spe_isr;
    }
    
    
    @Override
    public void Agrega(nominaEmpleado nominaEmp) {
       //PRIMERO SE HACE LOS CALCULOS
        double sdi = nominaEmp.getEmpleado().getSdi();
        double sueldoTrabajo = nominaEmp.getDiasTrabajados()*nominaEmp.getEmpleado().getSueldo();
        double isr_spe = obtenerSpe_Isr(sueldoTrabajo,nominaEmp.getNomina().getTipo()); //Verificar
        
        double sueldoNeto = 0;
        double excedenteSMG = 0;
        double prestacionesDinero = 0;
        double prestacionesEspecie = 0;
        double invalidez = 0;
        double cuotaImss = 0;
        double censatiaVejez = 0;
        
        if (threeUMA < sdi){
            excedenteSMG = ((sdi-threeUMA)*nominaEmp.getDiasTrabajados())*0.40;
        }
        
        prestacionesDinero = sdi* nominaEmp.getDiasTrabajados() * 0.0025;
        prestacionesEspecie = sdi*nominaEmp.getDiasTrabajados() * 0.00375; 
        invalidez = sdi*nominaEmp.getDiasTrabajados()*0.00625;
        
        cuotaImss = excedenteSMG + prestacionesDinero + prestacionesEspecie + invalidez;
        censatiaVejez = sdi*.01125*nominaEmp.getDiasTrabajados();
        sueldoNeto = sueldoTrabajo + isr_spe - nominaEmp.getInfonavit() - excedenteSMG - cuotaImss - censatiaVejez;
        
        //DEDES AQUI SE INSERTA A LA TABLA 
        String query = "INSERT INTO NominaEmpleado (idNomina,codigoEmpleado,SueldoDiario,sdi,"
                + "diasTrabajados,spe_isr, infonavit, cuotaImss,CesantiaVejez,SueldoNeto)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        PreparedStatement stmt;
        
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, nominaEmp.getNomina().getId());
            stmt.setInt(2, nominaEmp.getEmpleado().getCodigo());
            stmt.setDouble(3, nominaEmp.getEmpleado().getSueldo());
            stmt.setDouble(4, sdi);
            stmt.setInt(5, nominaEmp.getDiasTrabajados());
            stmt.setDouble(6, isr_spe);
            stmt.setDouble(7, nominaEmp.getInfonavit());
            stmt.setDouble(8, cuotaImss);
            stmt.setDouble(9, censatiaVejez);
            stmt.setDouble(10, sueldoNeto);
            
            stmt.executeUpdate();
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(nominaDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.Salir();
        }
    }

    @Override
    public void Actualiza(nominaEmpleado nominaEmp) {
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        String query = "UPDATE nominaEmpleado SET";
    }

    @Override
    public void Elimina(int id) {
        String query = "DELETE FROM nominaEmpleado WHERE id =?";
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        PreparedStatement stmt;
        
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(nominaEmpleadoDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.Salir();
        }
    }

    @Override
    public List<nominaEmpleado> obtenTodos(int tipo) {
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        List <nominaEmpleado> nominaList = new ArrayList();
        
        String query = "SELECT ne.*,n.fecha_inicio,n.fecha_fin,n.tipo, e.nombre, e.apellidoP, e.apellidoM "
                + "FROM nominaEmpleado ne "
                + "INNER JOIN empleado e ON ne.codigoEmpleado=e.codigo "
                + "INNER JOIN nomina n ON ne.idNomina = n.id WHERE n.tipo =?";
        PreparedStatement stmt;
        
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, tipo);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                nominaEmpleado nominaEmp = new nominaEmpleado();
                Nomina nomina = new Nomina();
                Empleado empleado = new Empleado();
                
                //Obtener los datos de la nomina
                nomina.setId(2);
                nomina.setFechaInicio(rs.getDate(13));
                nomina.setFechaFin(rs.getDate(14));
                nomina.setTipo(15);
                
                //obtener los datos de empleado
                empleado.setCodigo(3);
                empleado.setSueldo(rs.getDouble(4));
                empleado.setSdi(rs.getDouble(5));
                empleado.setNombre(rs.getString(16));
                empleado.setApellidoP(rs.getString(17));
                empleado.setApellidoM(rs.getString(18));
                
                //obtener los datos de la nomina del empleado
                nominaEmp.setId(rs.getInt(1));
                nominaEmp.setEmpleado(empleado);
                nominaEmp.setNomina(nomina);
                nominaEmp.setDiasTrabajados(rs.getInt(6));  
                nominaEmp.setSueldoTrabajo(rs.getDouble(7));
                nominaEmp.setSpe_isr(rs.getDouble(8));
                nominaEmp.setInfonavit(rs.getDouble(9));
                nominaEmp.setCuotaImss(rs.getDouble(10));
                nominaEmp.setCensantiaVejez(rs.getDouble(11));
                nominaEmp.setSueldoNeto(rs.getDouble(12));

                nominaList.add(nominaEmp);
            }
        } catch (SQLException ex) {
            System.out.println("ERROOOOR");
            Logger.getLogger(nominaEmpleadoDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nominaList;
    }

    @Override
    public List<nominaEmpleado> registroNominaEmpleado(int tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<nominaEmpleado> obtenNominaEmpleado(int id) {
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        List <nominaEmpleado> nominaList = new ArrayList();
        
        String query = "SELECT ne.*,n.fecha_inicio,n.fecha_fin,n.tipo, e.nombre, e.apellidoP, e.apellidoM "
                + "FROM nominaEmpleado ne "
                + "INNER JOIN empleado e ON ne.codigoEmpleado=e.codigo "
                + "INNER JOIN nomina n ON ne.idNomina = n.id WHERE e.codigo=?";
        PreparedStatement stmt;
        
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                nominaEmpleado nominaEmp = new nominaEmpleado();
                Nomina nomina = new Nomina();
                Empleado empleado = new Empleado();
                
                //Obtener los datos de la nomina
                nomina.setId(2);
                nomina.setFechaInicio(rs.getDate(13));
                nomina.setFechaFin(rs.getDate(14));
                nomina.setTipo(15);
                
                //obtener los datos de empleado
                empleado.setCodigo(3);
                empleado.setSueldo(rs.getDouble(4));
                empleado.setSdi(rs.getDouble(5));
                empleado.setNombre(rs.getString(16));
                empleado.setApellidoP(rs.getString(17));
                empleado.setApellidoM(rs.getString(18));
                
                //obtener los datos de la nomina del empleado
                nominaEmp.setId(rs.getInt(1));
                nominaEmp.setEmpleado(empleado);
                nominaEmp.setNomina(nomina);
                nominaEmp.setDiasTrabajados(rs.getInt(6));  
                nominaEmp.setSueldoTrabajo(rs.getDouble(7));
                nominaEmp.setSpe_isr(rs.getDouble(8));
                nominaEmp.setInfonavit(rs.getDouble(9));
                nominaEmp.setCuotaImss(rs.getDouble(10));
                nominaEmp.setCensantiaVejez(rs.getDouble(11));
                nominaEmp.setSueldoNeto(rs.getDouble(12));

                nominaList.add(nominaEmp);
            }
        } catch (SQLException ex) {
            System.out.println("ERROOOOR");
            Logger.getLogger(nominaEmpleadoDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nominaList;
    }

    @Override
    public List<nominaEmpleado> obtenNominaFecha(int idNomina) {
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        List <nominaEmpleado> nominaList = new ArrayList();
        
        String query = "SELECT ne.*,n.fecha_inicio,n.fecha_fin,n.tipo, e.nombre, e.apellidoP, e.apellidoM "
                + "FROM nominaEmpleado ne "
                + "INNER JOIN empleado e ON ne.codigoEmpleado=e.codigo "
                + "INNER JOIN nomina n ON ne.idNomina = n.id WHERE n.id=?";
        PreparedStatement stmt;
        
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, idNomina);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                nominaEmpleado nominaEmp = new nominaEmpleado();
                Nomina nomina = new Nomina();
                Empleado empleado = new Empleado();
                
                //Obtener los datos de la nomina
                nomina.setId(2);
                nomina.setFechaInicio(rs.getDate(13));
                nomina.setFechaFin(rs.getDate(14));
                nomina.setTipo(15);
                
                //obtener los datos de empleado
                empleado.setCodigo(3);
                empleado.setSueldo(rs.getDouble(4));
                empleado.setSdi(rs.getDouble(5));
                empleado.setNombre(rs.getString(16));
                empleado.setApellidoP(rs.getString(17));
                empleado.setApellidoM(rs.getString(18));
                
                //obtener los datos de la nomina del empleado
                nominaEmp.setId(rs.getInt(1));
                nominaEmp.setEmpleado(empleado);
                nominaEmp.setNomina(nomina);
                nominaEmp.setDiasTrabajados(rs.getInt(6));  
                nominaEmp.setSueldoTrabajo(rs.getDouble(7));
                nominaEmp.setSpe_isr(rs.getDouble(8));
                nominaEmp.setInfonavit(rs.getDouble(9));
                nominaEmp.setCuotaImss(rs.getDouble(10));
                nominaEmp.setCensantiaVejez(rs.getDouble(11));
                nominaEmp.setSueldoNeto(rs.getDouble(12));

                nominaList.add(nominaEmp);
            }
        } catch (SQLException ex) {
            System.out.println("ERROOOOR");
            Logger.getLogger(nominaEmpleadoDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nominaList;
    }
    
}
