/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoMysql;
import Entity.Empleado;
import Entity.Usuario;
import dao.usuarioDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        
        String query = "SELECT u.*, e.nombre, e.apellidoP, e.apellidoM FROM usuario u "
                + "INNER JOIN empleado e ON u.codigo=e.codigo WHERE u.codigo = ?";
        
        Usuario usuario = new Usuario();

        PreparedStatement stmt;
        try {
            conexion.newConnetionCont(Integer.toString(codigo), contrasena);
            
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
                
                 System.out.println("Registro");
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
            if(!usuario.getRole().equals("ADMIN")){
                for (int i=0; i<4; i++){
                       if (!usuario.cuoPriv[i].equals("")){
                            query = "GRANT "+usuario.cuoPriv[i]+" ON nomina.cuota TO ?@localhost";
                            stmt = conexion.getConnection().prepareStatement(query);
                            stmt.setString(1, Integer.toString(usuario.getCodigo()));
                            stmt.executeUpdate();
                       }
                       
                       if (!usuario.empPriv[i].equals("")){
                           query = "GRANT "+usuario.empPriv[i]+" ON nomina.empleado TO ?@localhost";
                           stmt = conexion.getConnection().prepareStatement(query);
                           stmt.setString(1, Integer.toString(usuario.getCodigo()));
                           
                           stmt.executeUpdate();
                       }
                       
                       if (!usuario.nomPriv[i].equals("")){
                           query = "GRANT "+usuario.nomPriv[i]+" ON nomina.nomina TO ?@localhost";
                           stmt = conexion.getConnection().prepareStatement(query);
                           stmt.setString(1, Integer.toString(usuario.getCodigo()));
                           
                           stmt.executeUpdate();
                            
                           query = "GRANT "+usuario.nomPriv[i]+" ON nomina.nominaEmpleado TO ?@localhost";
                           stmt = conexion.getConnection().prepareStatement(query);
                           stmt.setString(1, Integer.toString(usuario.getCodigo()));
                           stmt.executeUpdate();
                       }
                }
                
                query = "GRANT SELECT ON nomina.usuario TO ?@localhost";
                stmt = conexion.getConnection().prepareStatement(query);
                stmt.setString(1, Integer.toString(usuario.getCodigo()));
                           
                stmt.executeUpdate();
            }else{
                query = "GRANT ALL ON nomina.* TO ?@localhost";
                stmt = conexion.getConnection().prepareStatement(query);
                stmt.setString(1, Integer.toString(usuario.getCodigo()));
                           
                stmt.executeUpdate();
            }
            
            
            query= "FLUSH PRIVILEGES";
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.executeUpdate();
                    
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
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        List <Usuario> listaUsuario = new ArrayList<Usuario>();
        
        String query = "SELECT e.codigo, e.nombre, e.apellidoP, e.apellidoM, u.role "
                + "FROM usuario u INNER JOIN empleado e ON u.codigo=e.codigo WHERE u.role != 'ADMIN'";
        
        PreparedStatement stmt;
        
        try {
            stmt=conexion.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Usuario usuario = new Usuario();
                
                usuario.setCodigo(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellidoP(rs.getString(3));
                usuario.setApellidoM(rs.getString(4));
                usuario.setRole(rs.getString(5));
                
                listaUsuario.add(usuario);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuario;
    }

    @Override
    public List<Usuario> obtenUsuarioAdmin() {
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        List <Usuario> listaUsuario = new ArrayList<Usuario>();
        
        String query = "SELECT e.codigo, e.nombre, e.apellidoP, e.apellidoM, u.role "
                + "FROM usuario u INNER JOIN empleado e ON u.codigo=e.codigo WHERE u.role = 'ADMIN'";
        
        PreparedStatement stmt;
        
        try {
            stmt=conexion.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Usuario usuario = new Usuario();
                
                usuario.setCodigo(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellidoP(rs.getString(3));
                usuario.setApellidoM(rs.getString(4));
                usuario.setRole(rs.getString(5));
                
                listaUsuario.add(usuario);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return listaUsuario;
    }

    @Override
    public void quitarPrivilegios(Usuario usuario) {
     Conexion conexion = new Conexion();
        conexion.newConnection();
        
        PreparedStatement stmt;
        
        try {
            String query;
            query= "REVOKE ALL ON nomina.empleado FROM '"+usuario.getCodigo()+"'@'localhost'";
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.executeUpdate();
            
            query= "REVOKE ALL ON nomina.nomina FROM '"+usuario.getCodigo()+"'@'localhost'";
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.executeUpdate();
            
            query= "REVOKE ALL ON nomina.nominaEmpleado FROM '"+usuario.getCodigo()+"'@'localhost'";
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.executeUpdate();
            
            query= "REVOKE ALL ON nomina.cuota FROM '"+usuario.getCodigo()+"'@'localhost'";
            stmt = conexion.getConnection().prepareStatement(query);
            stmt.executeUpdate();
            
            String queryF = "FLUSH PRIVILEGES";
            stmt = conexion.getConnection().prepareStatement(queryF);
            
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void agregaTodos(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void otorgaTodosLosDerechos(Usuario usuario) {
        String queery = "GRANT ALL ON nomina.* TO ?@'localhost'";
    }

    @Override
    public List<Empleado> obteneEmpleados() {
        Conexion conexion = new Conexion();
        conexion.newConnection();
        
        List <Empleado> listaEmpleado = new ArrayList<Empleado>();
        
        String query = "SELECT e.* FROM empleado e LEFT JOIN usuario u ON e.codigo = u.codigo WHERE u.codigo IS NULL";
        
        PreparedStatement stmt;
        
        try {
            stmt=conexion.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Empleado empleado = new Empleado();
                
                empleado.setCodigo(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidoP(rs.getString(3));
                empleado.setApellidoM(rs.getString(4));
                empleado.setFehcaIngreso(rs.getDate(5));
                empleado.setSueldo(rs.getDouble(6));
                
                listaEmpleado.add(empleado);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaEmpleado;
    }

   
}
