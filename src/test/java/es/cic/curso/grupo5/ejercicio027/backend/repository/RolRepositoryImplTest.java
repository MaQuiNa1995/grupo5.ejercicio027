package es.cic.curso.grupo5.ejercicio027.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Rol;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Operacion;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:es/cic/curso/grupo5.ejercicio027/applicationContext.xml"}
)

public class RolRepositoryImplTest extends AbstractRepositoryImplTest<Long, Rol> {

    @Autowired
    private RolRepository sut;
    
    Usuario usuario,usuario2,usuario3;
    Rol rol1;
    Rol rol2;
    Rol rol3;
    Operacion operacion;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        operacion = new Operacion("Copiar");
        em.persist(operacion);
        rol1 = new Rol("administrador", operacion);
        rol2 = new Rol("invitado", operacion);
        rol3 = new Rol("editor", operacion);
        
    }

    @Override
    public Rol getInstanceDeTParaNuevo() {
    	
    	Rol claseRol = new Rol();
        claseRol.setRol("administrador");
        claseRol.setOperacion(operacion);

        return claseRol;
    }

    @Override
    public Rol getInstanceDeTParaLectura() {
    	
    	Rol claseRol = new Rol();
        claseRol.setRol("administrador");
        claseRol.setOperacion(operacion);

        return claseRol;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public Rol getInstanceDeTParaModificar(Long clave) {
    	
    	Rol claseRol = getInstanceDeTParaLectura();
        
        claseRol.setId(clave);
        claseRol.setRol("invitado");
        claseRol.setOperacion(operacion);
        
        return claseRol;
    }

    @Override
    public IRepository<Long, Rol> getRepository() {
        return sut;
    }

    @Override
    public boolean sonDatosIguales(Rol t1, Rol t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }
        if (!t1.getRol().equals(t2.getRol())) {
            return false;
        }
        
        if (!t1.getOperacion().equals(t2.getOperacion())) {
            return false;
        }
        
       
        
        return true;
    }
}
