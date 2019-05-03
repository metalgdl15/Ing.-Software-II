/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoMysql;

import Entity.Empleado;
import Entity.Nomina;
import dao.empleadoDao;
import dao.nominaDao;
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
public class nominaDaoMysql implements nominaDao{

    @Override
    public void Agrega(Nomina nomina) {
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        String query = "INSERT INTO nomina (fecha_inicio, fecha_fin, tipo) VALUES (?,?,?)";
        
        PreparedStatement stmt;
        
        java.sql.Date fechaInicio= new java.sql.Date(nomina.getFechaInicio().getTime());
        java.sql.Date fechaFin= new java.sql.Date(nomina.getFechaFin().getTime());
        
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            
            stmt.setDate(1, fechaInicio);
            stmt.setDate(2, fechaFin);
            stmt.setInt(3, nomina.getTipo());
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(nominaDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.Salir();
        }
    }

    @Override 
    public void Actualiza(Nomina nomina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Elimina(Nomina nomina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Nomina> obtenTodos() {
        
        Conexion conexion = new Conexion();
        conexion.newConnection();
        List <Nomina> listaNomina =new ArrayList<Nomina>();
        String query = "SELECT * FROM nomina";
        PreparedStatement stmt;
        
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            //stmt.setInt(1,tipo);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Nomina nomina = new Nomina();
                
                nomina.setId(rs.getInt(1));
                nomina.setFechaInicio(rs.getDate(2));
                nomina.setFechaFin(rs.getDate(3));
                nomina.setTipo(rs.getInt(4));
                
                listaNomina.add(nomina);
            }    
            stmt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(nominaDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.Salir();
        }
        return listaNomina;
    }

    @Override
    public List<Nomina> obtenTipo(int tipo) {
        Conexion conexion = new Conexion();
        conexion.newConnection();
        List <Nomina> listaNomina =new ArrayList<Nomina>();
        String query = "SELECT * FROM nomina WHERE tipo =?";
        PreparedStatement stmt;
        
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1,tipo);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Nomina nomina = new Nomina();
                
                nomina.setId(rs.getInt(1));
                nomina.setFechaInicio(rs.getDate(2));
                nomina.setFechaFin(rs.getDate(3));
                nomina.setTipo(rs.getInt(4));
                
                listaNomina.add(nomina);
            }    
            stmt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(nominaDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.Salir();
        }
        return listaNomina;
    }

    @Override
    public Nomina obtenNomina(int id) {
        Conexion conexion = new Conexion();
        conexion.newConnection();
        Nomina nomina = new Nomina();
        String query = "SELECT * FROM nomina WHERE id=?";
        PreparedStatement stmt;
        
        try {
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1,id);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                
                nomina.setId(rs.getInt(1));
                nomina.setFechaInicio(rs.getDate(2));
                nomina.setFechaFin(rs.getDate(3));
                nomina.setTipo(rs.getInt(4));
            }    
            stmt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(nominaDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.Salir();
        }
        return nomina;
    }
    

}
