/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Nomina;
import Entity.Empleado;
import Entity.Usuario;
import java.util.List;
/**
 *
 * @author Adan
 */
public interface nominaDao {
    
        public Usuario getUsuario();
        public void setUsuario(Usuario usuario);
        
        public void Agrega(Nomina nomina);
        public void Actualiza (Nomina nomina);
        public void Elimina (Nomina nomina); 
        public List <Nomina> obtenTodos();
        public List <Nomina> obtenTipo(int tipo);
        public Nomina obtenNomina(int id);
        
        
}
