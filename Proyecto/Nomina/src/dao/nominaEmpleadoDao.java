/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.nominaEmpleado;
import java.util.List;

/**
 *
 * @author Adan
 */
public interface nominaEmpleadoDao {
        public void Agrega(nominaEmpleado nominaEmp);
        public void Actualiza (nominaEmpleado nominaEmp);
        public void Elimina (int id); 
        public List <nominaEmpleado> obtenTodos();
        public List <nominaEmpleado> registroNominaEmpleado(int tipo);
        public List <nominaEmpleado> obtenNominaEmpleado(int id);
        public List <nominaEmpleado> obtenNominaFecha(int idNomina);
}
