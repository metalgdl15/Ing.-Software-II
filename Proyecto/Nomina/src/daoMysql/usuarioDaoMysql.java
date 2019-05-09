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
        String query = "SELECT u.*, e.nombre, e.apellidoP, e.apellidoM FROM usuario u "
                + "INNER JOIN empleado e ON u.codigo=e.codigo WHERE u.codigo = ?";
        
        Usuario usuario = new Usuario();

        PreparedStatement stmt;
        try {
            
            stmt = conexion.getConnection().prepareStatement(query);
            
            stmt.setInt(1, codigo);
            
            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while (rs.next()){
                i++;
                //EL 1 SE IGNORA
                usuario.setCodigo(rs.getInt(2));
                usuario.setRole(rs.getString(3));               
                usuario.setNombre(rs.getString(4));
                usuario.setApellidoP(rs.getString(5));
                usuario.setApellidoM(rs.getString(6));
                
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
        PreparedStatement stmtII;
        try{
               
                String query = "CREATE USER ?@'localhost' IDENTIFIED BY ? ";
                
                stmt = conexion.getConnection().prepareStatement(query);
                stmt.setString(1, Integer.toString(usuario.getCodigo()));
                stmt.setString(2, usuario.getContrasena());
                int consulta = stmt.executeUpdate();
                
                
                String ingresa = "INSERT INTO usuario (codigo,role) VALUES (?,?)";
                stmtII = conexion.getConnection().prepareStatement(ingresa);
                
                stmtII.setInt(1, usuario.getCodigo());
                stmtII.setString(2, usuario.getRole());
                stmtII.executeUpdate();
                
                
                if (consulta > 0){
                    JOptionPane.showMessageDialog(null, "Se ha registrado nuevo usuario");
                }
                
                stmt.close();
                stmtII.close();
                
                 System.out.println("REgistro");
            }catch(SQLException ex){
                Logger.getLogger(usuarioDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void Actualiza(Usuario usuario) {
        Conexion conexion= new Conexion();
        
        try{
            conexion.newConnection();
            PreparedStatement stmt = null;
            
            String query;
                for (int i =0; i<4; i++){
                       if (!usuario.cuoPriv[i].equals("")){
                            query = "GRANT "+usuario.cuoPriv[i]+" ON nomina.cuota TO "+usuario.getCodigo()+"@localhost";
                            stmt = conexion.getConnection().prepareStatement(query);
                            stmt.executeUpdate();
                       }
                       
                       if (!usuario.empPriv[i].equals("")){
                           query = "GRANT "+usuario.cuoPriv[i]+" ON nomina.empleado TO "+usuario.getCodigo()+"@localhost";
                           stmt = conexion.getConnection().prepareStatement(query);
                           stmt.executeUpdate();
                       }
                       
                       if (!usuario.nomPriv[i].equals("")){
                            query = "GRANT "+usuario.cuoPriv[i]+" ON nomina.nomina TO "+usuario.getCodigo()+"@localhost";
                            stmt = conexion.getConnection().prepareStatement(query);
                            stmt.executeUpdate();
                            
                            query = "GRANT "+usuario.cuoPriv[i]+" ON nomina.nominaEmpleado TO "+usuario.getCodigo()+"@localhost";
                            stmt = conexion.getConnection().prepareStatement(query);
                            stmt.executeUpdate();
                       }
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
        
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        PreparedStatement stmt;
        
        try {
            
            String query= "DROP USER ?@'localhost'";
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1, Integer.toString(usuario.getCodigo()));
            stmt.executeUpdate();
            
            query = "DELETE FROM usuario WHERE codigo = ?";
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, usuario.getCodigo());
            stmt.executeUpdate();
            
            query= "FLUSH PRIVILEGES";
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.executeUpdate();
            
       
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Usuario> obtenTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> obtenUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void quitarPrivilegios(Usuario usuario) {
     Conexion conexion = new Conexion();
        conexion.newConnection();
        
        PreparedStatement stmt;
        
        try {
            
            String query= "REVOKE ALL ON nomina.* FROM '"+usuario.getCodigo()+"'@'localhost'";
            stmt = conexion.getConnection().prepareStatement(query);
         
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void agregaTodos(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
