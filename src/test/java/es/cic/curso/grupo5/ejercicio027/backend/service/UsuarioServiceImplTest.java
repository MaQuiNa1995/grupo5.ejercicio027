package es.cic.curso.grupo5.ejercicio027.backend.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import es.cic.curso.grupo5.ejercicio027.backend.service.HistoricoService;


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
		Usuario usuarioCreado = usuarioService.aniadirUsuario(usuario);
		assertNotNull(usuarioCreado.getId());
	}

	@Test
	public void testModificarUsuario() {
		
		usuarioCreado.setNombre("Jose");
		usuarioCreado.ModificarUsuario();
		assertEquals();
	}

	@Test
	public void testBorrarClaseDummie() {
		Long idUsuario = 0L;
		usuarioService.borrarUsuario(idUsuario);
		List<Usuario> listaUsuario = usuarioService.listarUsuario();
		assertEquals(listaUsuario.size(), 3);
	}

	@Test
	public void testListarClaseDummie() {
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