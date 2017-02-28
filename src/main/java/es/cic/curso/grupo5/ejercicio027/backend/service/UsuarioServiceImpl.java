package es.cic.curso.grupo5.ejercicio027.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Operacion;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Rol;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.repository.OperacionRepository;
import es.cic.curso.grupo5.ejercicio027.backend.repository.RolRepository;
import es.cic.curso.grupo5.ejercicio027.backend.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private OperacionRepository operacionRepository;

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
    	Operacion operacion = new Operacion("Copiar");
    	operacionRepository.add(operacion);
    	
    	Rol rol1= new Rol("adminitrador", operacion);
    	Rol rol2= new Rol("invitado", operacion);
    	Rol rol3= new Rol("supervisor", operacion);
    	Rol rol4= new Rol("editor", operacion);
    	rolRepository.add(rol1);
    	rolRepository.add(rol2);
    	rolRepository.add(rol3);
    	rolRepository.add(rol4);
    	
    	Usuario usuario1 = new Usuario("Juan González del Olmo", "juan", rol1, "juan@hotmail.com",true);
		Usuario usuario2 = new Usuario("Jose Giménez Sánchez", "pepe", rol2, "pepe@hotmail.com",true);
		Usuario usuario3 = new Usuario("Pedro de la torre García", "pedro", rol3, "pedro@hotmail.com",true);
		Usuario usuario4 = new Usuario("María Suarez Fernandez", "mery", rol4, "laMery@hotmail.com",true);
		
		usuarioRepository.add(usuario1);
		usuarioRepository.add(usuario2);
		usuarioRepository.add(usuario3);
		usuarioRepository.add(usuario4);
    }
    
}