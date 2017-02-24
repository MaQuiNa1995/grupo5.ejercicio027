package es.cic.curso.grupo5.ejercicio027.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.repository.HistoricoRepository;
import es.cic.curso.grupo5.ejercicio027.backend.repository.IRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:es/cic/curso/grupo5.ejercicio027/applicationContext.xml"}
)

public class HistoricoRepositoryImplTest extends AbstractRepositoryImplTest<Long, Historico> {

    @Autowired
    private HistoricoRepository sut;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public Historico getInstanceDeTParaNuevo() {
    	
    	Usuario usuario = new Usuario("Christian","curso18","Admin","maquina1995@gmail.com");
    	
    	
        Historico claseHistorico = new Historico();
        claseHistorico.setOperacion("Tirar la basura");
        claseHistorico.setHora("16:56");
        
        claseHistorico.setUsuario(usuario);

        return claseHistorico;
    }

    @Override
    public Historico getInstanceDeTParaLectura() {
    	Usuario usuario = new Usuario("Christian","curso18","Admin","maquina1995@gmail.com");
    	
    	
        Historico claseHistorico = new Historico();
        claseHistorico.setOperacion("Tirar la basura");
        claseHistorico.setHora("16:56");
        claseHistorico.setUsuario(usuario);

        return claseHistorico;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public Historico getInstanceDeTParaModificar(Long clave) {
        Historico claseHistorico = getInstanceDeTParaLectura();
        claseHistorico.setId(clave);
        return claseHistorico;
    }

    @Override
    public IRepository<Long, Historico> getRepository() {
        return sut;
    }

    @Override
    public boolean sonDatosIguales(Historico t1, Historico t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }

        if (!(t1.getId()==t2.getId())) {
            return false;
        }
        
        if (!t1.getOperacion().equals(t2.getOperacion())) {
            return false;
        }
        
        if (!t1.getHora().equals(t2.getHora())) {
            return false;
        }
        
        return true;
    }
}
