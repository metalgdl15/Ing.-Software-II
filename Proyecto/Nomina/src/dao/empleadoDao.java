/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import Entity.Empleado;
import java.util.List;

/**
 *
 * @author Adan
 */
public interface empleadoDao {
        public void agrega(Empleado empleado);
        public void Actualiza (Empleado empleado);
        public void Elimina (Empleado empleado); 
        public List <Empleado> obtenTodos();
        public List <Empleado> obtenEmpleado(int codigo);
        public List <Empleado> obtenMayorSueldo(Double sueldo);
        public List <Empleado> obtenMenorSueldo(Double sueldo);
        public List <Empleado> ontenMayorAntiguedad();
        public List <Empleado> obtenMenotAntiguedad();
}
