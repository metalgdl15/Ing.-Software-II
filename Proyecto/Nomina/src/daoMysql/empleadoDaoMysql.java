/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoMysql;
import Entity.Empleado;
import dao.empleadoDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Adan
 */
public class empleadoDaoMysql implements empleadoDao {

    @Override
    public void agrega(Empleado empleado) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Conexion conexion = new Conexion();        
        conexion.newConnection();
        String query = "INSERT INTO empleado (nombre, apellidoP , apellidoM, sueldoDiario, fechaIngreso, activo) VALUES (?,?,?,?,?,?)";
        
        PreparedStatement stmt;
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            
            java.sql.Date fechaActual= new java.sql.Date( empleado.getFehcaIngreso().getTime());
            
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellidoP());
            stmt.setString(3, empleado.getApellidoM());
            stmt.setDouble(4, empleado.getSueldo());
            stmt.setDate(5, fechaActual);
            stmt.setInt(6, 1);
            
            stmt.executeUpdate();
            stmt.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(empleadoDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.Salir();
        }

    }

    @Override
    public void Actualiza(Empleado empleado) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        try {
            String query;
            
            
            PreparedStatement stmt;
            
            //Valida los campos que no fueron nulos concatenando la sintaxis del query
            
            //Nombre
            if (!"".equals(empleado.getNombre())){
                query = "UPDATE empleado SET nombre=? WHERE codigo=?";
                stmt = conexion.getConnection().prepareStatement(query);
                stmt.setString(1, empleado.getNombre());
                stmt.setInt(2, empleado.getCodigo());
                stmt.executeUpdate();
            }
            
            //Apellido Paterno
            if (!"".equals(empleado.getApellidoP())){
                query = "UPDATE empleado SET apellidoP=? WHERE codigo=?";
                stmt = conexion.getConnection().prepareStatement(query);
                stmt.setString(1, empleado.getApellidoP());
                stmt.setInt(2, empleado.getCodigo());
                stmt.executeUpdate();
            }
            
            //ApellidoMaterno
            if (!"".equals(empleado.getApellidoM())){
                query = "UPDATE empleado SET apellidoM=? WHERE codigo=?";
                stmt = conexion.getConnection().prepareStatement(query);
                stmt.setString(1, empleado.getApellidoM());
                stmt.setInt(2, empleado.getCodigo());
                stmt.executeUpdate();
            }
            
            //Fecha de ingreso
            if(empleado.getFehcaIngreso() != null){
                java.sql.Date fechaActual= new java.sql.Date( empleado.getFehcaIngreso().getTime());
                query = "UPDATE empleado SET fechaIngreso=? WHERE codigo=?";
                stmt = conexion.getConnection().prepareStatement(query);
                stmt.setDate(1, fechaActual);
                stmt.setInt(2, empleado.getCodigo());
                stmt.executeUpdate();
            }
            
            //Sueldo Diario
            if (empleado.getSueldo() != 0.0){
                query = "UPDATE empleado SET sueldoDiario=? WHERE codigo=?";
                stmt = conexion.getConnection().prepareStatement(query);
                stmt.setDouble(1, empleado.getSueldo());
                stmt.setInt(2, empleado.getCodigo());
                stmt.executeUpdate();
            }
            

        } catch (SQLException ex) {
            System.out.println("ERROR");
            JOptionPane.showMessageDialog(null, "No se pudo modificar \n verifique sus derechos con el administrador o vuelva intentar");
        }finally{
            conexion.Salir();
        }   
        
    }

    @Override
    public void Elimina(Empleado empleado) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Conexion conexion = new Conexion();        
        conexion.newConnection();
        
        String query= "UPDATE empleado SET activo=? WHERE codigo=?";
        
        PreparedStatement stmt;
        try{
            stmt = conexion.getConnection().prepareStatement(query);
            
            stmt.setInt(1, 0);
            stmt.setInt(2, empleado.getCodigo());
            
            stmt.executeUpdate();
        }catch(SQLException ex){
               System.out.println("ERROR");
        }finally{
            conexion.Salir();
        }
    }
    
    @Override
    public List<Empleado> obtenTodos() {
        List <Empleado> listaEmp = new ArrayList<Empleado>();
        
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        /**
         *El QUERYEMPLEADO SIRVE PARA PODER SACAR LA ANTIGUEDAD DEL EMPLEADO YA QUE ES UN METODO CALCULADO
         * JUNTO CON TODOS LOS DEMAS CAMPOS DEL EMPLEADO
         * Y DESPUES CONFORMME A LA ANTIGUEDAD SACAR EL SUELDO DIARIO INTEGRADO (SDI) HACIENDO 
         * INNER JOIN ANTIGUEDAD->AÑO CON IMSS. LA RAZON ES POR QUE NO LEE ANTIGUEDAD COMO CAMPO 
         * SINO ALIAS Y LOS ALIAS NO SE PUEDEN USAR PARA LAS RESTRICCIONES "WHERE"
         */
        String queryEmpleado="SELECT *, 1+TIMESTAMPDIFF(YEAR,fechaIngreso,CURDATE()) AS ant FROM empleado";
        String query =" SELECT e.*, i.sdi*e.sueldoDiario FROM ("+queryEmpleado+") e "
                + "INNER JOIN imss i ON e.ant = i.anyo WHERE e.activo=? ORDER BY e.apellidoP, e.apellidoM ";
        PreparedStatement stmt;
        
        try {
            
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, 1);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Empleado empleado = new  Empleado();
                empleado.setCodigo(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidoP(rs.getString(3));
                empleado.setApellidoM(rs.getString(4));
                empleado.setFehcaIngreso(rs.getDate(5));
                empleado.setSueldo(rs.getDouble(6));
                //Omite el 7 ya que representa si esta activo a no en mysql 
                empleado.setAntiguedad(rs.getShort(8));
                empleado.setSdi(rs.getDouble(9));
                listaEmp.add(empleado);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(empleadoDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            conexion.Salir();
        }
        return listaEmp;
    }
    
    @Override
    public List<Empleado> obtenEmpleado(int codigo) {
        List <Empleado> listaEmp = new ArrayList<Empleado>();
        
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        String queryEmpleado="SELECT *, 1+TIMESTAMPDIFF(YEAR,fechaIngreso,CURDATE()) AS ant FROM empleado";
        String query =" SELECT e.*, i.sdi*e.sueldoDiario FROM ("+queryEmpleado+") e "
                + "INNER JOIN imss i ON e.ant = i.anyo WHERE e.activo=? AND codigo =? ORDER BY e.apellidoP, e.apellidoM ";
        
        /*String query = "SELECT *, 1+TIMESTAMPDIFF(YEAR,fechaIngreso,CURDATE()) AS ant,  i.sdi "
                + "FROM empleado WHERE activo=? AND codigo =? ORDER BY apellidoP, apellidoM ";*/
        PreparedStatement stmt;
        
        try {
            
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setInt(2, codigo);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Empleado empleado = new  Empleado();
                empleado.setCodigo(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidoP(rs.getString(3));
                empleado.setApellidoM(rs.getString(4));
                empleado.setFehcaIngreso(rs.getDate(5));
                empleado.setSueldo(rs.getDouble(6));
                //Omite el 7 ya que es el acmpo activo de mysql
                empleado.setAntiguedad(rs.getShort(8));
                empleado.setSdi(rs.getDouble(9));
                
                listaEmp.add(empleado);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(empleadoDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            conexion.Salir();
        }
        return listaEmp;
    }

    @Override
    public List<Empleado> obtenMayorSueldo(Double sueldo) {
        List <Empleado> listaEmp = new ArrayList<Empleado>();
        
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        String queryEmpleado="SELECT *, 1+TIMESTAMPDIFF(YEAR,fechaIngreso,CURDATE()) AS ant FROM empleado";
        String query =" SELECT e.*, i.sdi*e.sueldoDiario FROM ("+queryEmpleado+") e "
                + "INNER JOIN imss i ON e.ant = i.anyo WHERE e.activo=? AND e.sueldoDiario*i.sdi >=? ORDER BY e.apellidoP, e.apellidoM ";
        /*String query = "SELECT *, 1+TIMESTAMPDIFF(YEAR,fechaIngreso,CURDATE()) AS ant,  i.sdi "
                + "FROM empleado WHERE activo=? AND sueldoDiario >=? ORDER BY apellidoP, apellidoM ";*/
        PreparedStatement stmt;
        
        try {
            
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setDouble(2, sueldo);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Empleado empleado = new  Empleado();
                empleado.setCodigo(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidoP(rs.getString(3));
                empleado.setApellidoM(rs.getString(4));
                empleado.setFehcaIngreso(rs.getDate(5));
                empleado.setSueldo(rs.getDouble(6));
                //omite el 7 ya que es el campo activo de myqsl
                empleado.setAntiguedad(rs.getShort(8));
                empleado.setSdi(rs.getDouble(9));
                
                listaEmp.add(empleado);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(empleadoDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            conexion.Salir();
        }
        return listaEmp;
    }

    @Override
    public List<Empleado> obtenMenorSueldo(Double sueldo) {
        List <Empleado> listaEmp = new ArrayList<Empleado>();
        
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        String queryEmpleado="SELECT *, 1+TIMESTAMPDIFF(YEAR,fechaIngreso,CURDATE()) AS ant FROM empleado";
        String query =" SELECT e.*, i.sdi*e.sueldoDiario FROM ("+queryEmpleado+") e "
                + "INNER JOIN imss i ON e.ant = i.anyo WHERE e.activo=? AND e.sueldoDiario*i.sdi <=? ORDER BY e.apellidoP, e.apellidoM ";
        
        /*String query = "SELECT *, 1+TIMESTAMPDIFF(YEAR,fechaIngreso,CURDATE()) AS ant FROM empleado WHERE activo=? AND sueldoDiario <=? ORDER BY apellidoP, apellidoM ";*/
        PreparedStatement stmt;
        
        try {
            
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setDouble(2, sueldo);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Empleado empleado = new  Empleado();
                empleado.setCodigo(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidoP(rs.getString(3));
                empleado.setApellidoM(rs.getString(4));
                empleado.setFehcaIngreso(rs.getDate(5));
                empleado.setSueldo(rs.getDouble(6));
                //Omite el 7 ya que es el campo activo de mysql
                empleado.setAntiguedad(rs.getShort(8));
                empleado.setSdi(rs.getDouble(9));
                
                listaEmp.add(empleado);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(empleadoDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            conexion.Salir();
        }
        return listaEmp;
    }
    
    @Override
    public List<Empleado> ontenMayorAntiguedad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empleado> obtenMenotAntiguedad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
