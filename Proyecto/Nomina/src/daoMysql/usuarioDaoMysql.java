/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoMysql;
import Entity.Usuario;
import dao.usuarioDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Adan
 */
public class usuarioDaoMysql implements usuarioDao{

    @Override
    public Usuario iniciaSesion(int codigo, String contrasena) {
        boolean entrar = false;
        Conexion conexion = new Conexion();
        conexion.newConnection();
        String query = "SELECT * FROM usuario WHERE codigo = ? AND contrasena=?";
        
        Usuario usuario = new Usuario();

        PreparedStatement stmt;
        try {
            
            stmt = conexion.getConnection().prepareStatement(query);
            
            stmt.setInt(1, codigo);
            stmt.setString(2, contrasena);
            
            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while (rs.next()){
                i++;
                usuario.setCodigo(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellidoP(rs.getString(3));
                usuario.setApellidoM(rs.getString(4));
                usuario.setContrasena(rs.getString(5));
                usuario.setRole(rs.getString(6));
            }
            
            if (i == 0){
                usuario.setRole("");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }

    @Override
    public void agrega(Usuario usuario) {
        
        Conexion conexion= new Conexion();
        conexion.newConnection();
        
        PreparedStatement stmt;
        try{
               
                String query = "CREATE USER '"+usuario.getCodigo()+"'@'localhost' IDENTIFIED BY '"+usuario.getContrasena()+"'";
                stmt = conexion.getConnection().prepareStatement(query);
                stmt.executeUpdate();
                
                
                String ingresa = "INSERT INTO usuario (codigo,nombre,apellidoP,apellidoM) VALUES (?,?,?,?,?)";
                stmt = conexion.getConnection().prepareStatement(ingresa);
                stmt.executeUpdate();
                
                int consulta = stmt.executeUpdate();
                if (consulta > 0){
                    JOptionPane.showMessageDialog(null, "Se ha registrado nuevo usuario");
                }
                
                 
            }catch(SQLException ex){
                Logger.getLogger(usuarioDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void Actualiza(Usuario usuario, String permisos[]) {
        Conexion conexion= new Conexion();
        
        try{
            conexion.newConnection();
            PreparedStatement stmt = null;
            
            String query;
                if (!permisos[0].equals("")){
                    query = "GRANT insert ON nomina.* TO "+usuario.getCodigo()+"@localhost";
                    stmt = conexion.getConnection().prepareStatement(query);
                    stmt.executeUpdate();
                }
                if (!permisos[1].equals("")){
                    query = "GRANT delete ON nomina.* TO "+usuario.getCodigo()+"@localhost";
                    stmt = conexion.getConnection().prepareStatement(query);
                    stmt.executeUpdate();
                }
                if (!permisos[2].equals("")){
                    query = "GRANT update ON nomina.* TO "+usuario.getCodigo()+"@localhost";
                    stmt = conexion.getConnection().prepareStatement(query);
                    stmt.executeUpdate();
                }
                if (!permisos[3].equals("")){
                    query = "GRANT select ON nomina.* TO "+usuario.getCodigo()+"@localhost";
                    stmt = conexion.getConnection().prepareStatement(query);
                    stmt.executeUpdate();
                }
                    
                stmt.close();
        }catch(SQLException ex){
            Logger.getLogger(usuarioDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            conexion.Salir();
        }
    }

    @Override
    public void Elimina(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> obtenTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> obtenUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
