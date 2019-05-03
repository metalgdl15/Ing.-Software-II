/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoMysql;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entity.Usuario;
/**
 *
 * @author Adan
 */


public class Conexion {
    
    private Usuario usuario;
    private Connection conn;
    
    private static final String url = "jdbc:mysql://localhost:3306/nomina";
    private static final String driver = "com.mysql.jdbc.Driver";
    private String user;
    private String password;
    
    //Iniciar Conexion (Constructor)
    Conexion (){  
        
    }
    
    private void conectar(){
        try{
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
            //conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/nomina","root","");
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            System.out.println("Cuenta invalida");
        }
    }
    
    public void newConnetionCont(String user, String password) throws SQLException{
        this.user=user;
        this.password=password;
        conectar();
    }
    
    public void newConnection(){
        user = "root";
        password="";
        conectar();
    }
    
    public Connection getConnection() throws SQLException{
        return conn;
    }
    
    public void Salir(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al salir");
        }
    }
    
}
