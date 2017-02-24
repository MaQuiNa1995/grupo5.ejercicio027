/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.grupo5.ejercicio027.backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.repository.UsuarioRepository;


/**
 *
 * @author MaQuiNa
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;

    @Override
	public Usuario aniadirUsuario(Usuario usuario) {
        return usuarioRepository.add(usuario);
	}

    @Override
    public Usuario modificarUsuario(Usuario usuario) {
    	return usuarioRepository.update(usuario);
    }
   
    @Override
    public void borrarUsuario(Long id) {
    	Usuario UsuarioABorrar = obtenerUsuario(id);
    	usuarioRepository.delete(UsuarioABorrar);
    }

    @Override
    public Usuario obtenerUsuario(Long id) {
        return usuarioRepository.read(id);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.list();
    }
    
}