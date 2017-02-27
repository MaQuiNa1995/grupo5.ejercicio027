package es.cic.curso.grupo5.ejercicio027.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:es/cic/curso/grupo5.ejercicio027/applicationContext.xml"}
)

public class UsuarioRepositoryImplTest extends AbstractRepositoryImplTest<Long, Usuario> {

    @Autowired
    private UsuarioRepository sut;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public Usuario getInstanceDeTParaNuevo() {
    	
        Usuario claseUsuario = new Usuario();
        
        claseUsuario.setNombre("Christian");
        claseUsuario.setPassword("curso18");
        claseUsuario.setRol("Administrador");
        claseUsuario.setEmail("maquina1995@gmail.com");
        
        return claseUsuario;
    }

    @Override
    public Usuario getInstanceDeTParaLectura() {
    	
        Usuario claseUsuario = new Usuario();
        
        claseUsuario.setNombre("Christian");
        claseUsuario.setPassword("curso18");
        claseUsuario.setRol("Administrador");
        claseUsuario.setEmail("maquina1995@gmail.com");

        return claseUsuario;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public Usuario getInstanceDeTParaModificar(Long clave) {
        Usuario claseUsuario = getInstanceDeTParaLectura();
        claseUsuario.setId(clave);
        claseUsuario.setNombre("Christian");
        claseUsuario.setPassword("curso18");
        claseUsuario.setRol("Administrador");
        claseUsuario.setEmail("maquina1995@gmail.com");
        return claseUsuario;
    }

    @Override
    public IRepository<Long, Usuario> getRepository() {
        return sut;
    }

    @Override
    public boolean sonDatosIguales(Usuario t1, Usuario t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }
        
		if (!t1.getNombre().equals(t2.getNombre())) {
			return false;
		}
		
		if (!t1.getPassword().equals(t2.getPassword())) {
			return false;
		}
		
		if (!t1.getRol().equals(t2.getRol())) {
			return false;
		}
		
		if (!t1.getEmail().equals(t2.getEmail())) {
			return false;
		}
        
        return true;
    }
}
