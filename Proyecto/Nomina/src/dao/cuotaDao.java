/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Cuota;
import java.util.List;

/**
 *
 * @author Adan
 */
public interface cuotaDao {
    public Integer agrega(Cuota cuota);
        public void Actualiza (Cuota cuota);
        public void Elimina (Cuota cuota); 
        public List <Cuota> obtenTodos();
        public List <Cuota> obtenCuota();
}
