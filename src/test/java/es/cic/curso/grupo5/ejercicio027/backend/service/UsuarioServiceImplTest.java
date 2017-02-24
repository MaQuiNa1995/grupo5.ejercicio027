package es.cic.curso.grupo5.ejercicio027.backend.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.service.UsuarioService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/grupo5.ejercicio027/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class UsuarioServiceImplTest {

    @PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	private UsuarioService usuarioService;

	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	
	
	@Before
	public void setUp() throws Exception {
		inicializaBaseDeDatos();
	}

	@Test
	public void testAniadirUuario() {
		usuarioService.aniadirUsuario(usuario1);
		usuarioService.aniadirUsuario(usuario2);
		usuarioService.aniadirUsuario(usuario3);
		
		assertNotNull(usuario1.getId());
		assertNotNull(usuario2.getId());
		assertNotNull(usuario3.getId());
	}

	@Test
	public void testModificarUsuario() {
		
		usuario2.setNombre("Jose");
		usuarioService.modificarUsuario(usuario2);
		assertEquals(usuario2.getNombre(), "Jose");
	}

	@Test
	public void testBorrarUsuario() {
		Usuario usuarioABorrar = new Usuario("juan", "juan", "administrador", "juan@hotmail.com");
		usuarioService.aniadirUsuario(usuarioABorrar);
		usuarioService.borrarUsuario(usuarioABorrar.getId());
		List<Usuario> listaUsuario = usuarioService.listarUsuario();
		assertEquals(listaUsuario.size(), 3);
	}

	@Test
	public void testListarUsuario() {
		List<Usuario> listaUsuario = usuarioService.listarUsuario();
		for (Usuario u : listaUsuario) {
			assertNotNull(u.getId());
		}

	}
	
	private void inicializaBaseDeDatos() {
		usuario1 = new Usuario("juan", "juan", "administrador", "juan@hotmail.com");
		usuario2 = new Usuario("pepe", "pepe", "invitado", "pepe@hotmail.com");
		usuario3 = new Usuario("pedro", "pedro", "inivitado", "pedro@hotmail.com");

		entityManager.persist(usuario1);
		entityManager.persist(usuario2);
		entityManager.persist(usuario3);

	}


}