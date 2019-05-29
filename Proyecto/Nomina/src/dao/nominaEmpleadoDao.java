/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Usuario;
import Entity.nominaEmpleado;
import java.util.List;

/**
 *
 * @author Adan
 */
public interface nominaEmpleadoDao {
    
        public Usuario getUsuario();
        public void setUsuario(Usuario usuario);
    
        public void Agrega(nominaEmpleado nominaEmp);
        public void Actualiza (nominaEmpleado nominaEmp);
        public void Elimina (int id); 
        public List <nominaEmpleado> obtenTodos(int tipo);
        public List <nominaEmpleado> registroNominaEmpleado(int tipo);
        public List <nominaEmpleado> obtenNominaEmpleado(int id);
        public List <nominaEmpleado> obtenNominaFecha(int idNomina);
        public List <nominaEmpleado> obtenNominaEmpleadoFecha(int idNomina, int idEmpleado);
}
