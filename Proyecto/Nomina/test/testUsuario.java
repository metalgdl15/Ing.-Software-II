/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Usuario;
import dao.usuarioDao;
import daoMysql.usuarioDaoMysql;
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
public class testUsuario {
    
    public testUsuario() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void agregar(){
        usuarioDao dao = new usuarioDaoMysql();
        
        Usuario usuario = new Usuario();
        usuario.setCodigo(12);
        usuario.setContrasena("123");
        usuario.setRole("ADMIN");
        
        dao.agrega(usuario);
    }
    
    @Test
    public void elimina(){
        usuarioDao dao = new usuarioDaoMysql();
        
        Usuario usuario = new Usuario();
        usuario.setCodigo(12);
        usuario.setContrasena("123");
        usuario.setRole("ADMIN");
        
        dao.Elimina(usuario);    
    }
}
