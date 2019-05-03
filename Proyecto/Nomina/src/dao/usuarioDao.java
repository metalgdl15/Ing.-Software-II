/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Usuario;
import java.util.List;

/**
 *
 * @author Adan
 */
public interface usuarioDao {
        public Usuario iniciaSesion(int codigo, String contrasena);
        public void agrega(Usuario usuario);
        public void Actualiza (Usuario usuario, String permisos[]);
        public void Elimina (Usuario usuario); 
        public List <Usuario> obtenTodos();
        public List <Usuario> obtenUsuario();
}
