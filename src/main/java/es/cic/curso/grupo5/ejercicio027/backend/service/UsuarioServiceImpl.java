package es.cic.curso.grupo5.ejercicio027.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.repository.UsuarioRepository;

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
    	Usuario usuarioABorrar = obtenerUsuario(id);
    	usuarioRepository.delete(usuarioABorrar);
    }

    @Override
    public Usuario obtenerUsuario(Long id) {
        return usuarioRepository.read(id);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.list();
    }
    
    @Override
    public void generaBBDD(){
    	
    	Usuario usuario1 = new Usuario("Juan González del Olmo", "juan", "administrador", "juan@hotmail.com",true);
		Usuario usuario2 = new Usuario("Jose Giménez Sánchez", "pepe", "invitado", "pepe@hotmail.com",false);
		Usuario usuario3 = new Usuario("Pedro de la torre García", "pedro", "supervisor", "pedro@hotmail.com",true);
		Usuario usuario4 = new Usuario("María Suarez Fernandez", "mery", "editor", "laMery@hotmail.com",true);
		
		usuarioRepository.add(usuario1);
		usuarioRepository.add(usuario2);
		usuarioRepository.add(usuario3);
		usuarioRepository.add(usuario4);
    }
    
}