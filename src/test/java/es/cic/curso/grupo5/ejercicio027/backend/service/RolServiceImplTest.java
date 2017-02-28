package es.cic.curso.grupo5.ejercicio027.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Operacion;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Rol;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/grupo5.ejercicio027/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class RolServiceImplTest {

    @PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	private RolService rolService;
	
	private Rol rol1;
	private Rol rol2;
	private Rol rol3;
	private Operacion operacion;
	
	
	@Before
	public void setUp() throws Exception {
		inicializaBaseDeDatos();
	}

	@Test
	public void testAniadirRol() {
		Rol historicoCreado = rolService.aniadirRol(rol2);
		assertNotNull(historicoCreado.getId());
	}

	@Test
	public void testModificarRol() {
		rol2.setRol("Copiar");
		rolService.modificarRol(rol2);
		assertEquals(rol2.getRol(), "Copiar");
	}

	@Test
	public void testBorrarHistorico() {
		Rol rolABorrar = new Rol("Eliminar", operacion);
		rolService.aniadirRol(rolABorrar);
		rolService.borrarRol(rolABorrar.getId());
		List<Rol> listaRol = rolService.listarRol();
		assertEquals(listaRol.size(), 3);
	}

	@Test
	public void testListarRol() {
		List<Rol> listaRol = rolService.listarRol();
		for (Rol u : listaRol) {
			assertNotNull(u.getId());
		}

	}
	
	private void inicializaBaseDeDatos() {
		
		operacion = new Operacion("Borrar");
		entityManager.persist(operacion);
		
		rol1 = new Rol("administrador", operacion);
		rol2 = new Rol("invitado", operacion);
		rol3 = new Rol("invitado", operacion);
		entityManager.persist(rol1);
		entityManager.persist(rol2);
		entityManager.persist(rol3);
	}


}