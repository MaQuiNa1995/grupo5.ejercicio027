/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.grupo5.ejercicio027.backend.service;

import java.util.List;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;

/**
 *
 * @author MaQuiNa
 */
public interface UsuarioService {

   

	Usuario aniadirUsuario(Usuario usuario);

    void borrarUsuario(Long id);
    
    Usuario modificarHistorico(Usuario usuario);

    Usuario obtenerUsuario(Long id);

    List<Usuario> listarUsuario();
}
