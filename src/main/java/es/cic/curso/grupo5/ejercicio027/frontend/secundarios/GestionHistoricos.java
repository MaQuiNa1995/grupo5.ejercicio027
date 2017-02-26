package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Grid.SelectionMode;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.service.HistoricoService;
import es.cic.curso.grupo5.ejercicio027.backend.service.UsuarioService;
import es.cic.curso.grupo5.ejercicio027.frontend.principal.MyUI;

public class GestionHistoricos  extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683850118394414599L;

	private NativeButton aniadirHistorico;
	Grid gridHistorico;
	private HistoricoForm detalleHistorico;
	private Historico historico; // se va a usar para eliminar y modificar
	private HistoricoService historicoService;
	@SuppressWarnings("unused")
	private MyUI padre;
	
	public GestionHistoricos(MyUI padre){
		this.padre = padre;
		historicoService = ContextLoader.getCurrentWebApplicationContext().getBean(HistoricoService.class);	
		
		aniadirHistorico = new NativeButton("Añadir Registro");
		aniadirHistorico.setIcon(FontAwesome.PLUS);
		gridHistorico = new Grid();
		
		gridHistorico.setWidth(1000, Unit.PIXELS);
 
		
		gridHistorico.setColumns("nombre","rol","operacion","hora");
		gridHistorico.addSelectionListener(e -> 
		{		
			historico = null;
			if (!e.getSelected().isEmpty() ) {
				
				historico = (Historico) e.getSelected().iterator().next();
				detalleHistorico.setVisible(true);
				aniadirHistorico.setVisible(false);
				
			} else{
				
			  detalleHistorico.setVisible(false);
			  aniadirHistorico.setVisible(true);
				
			}
			detalleHistorico.setHistorico(historico);	
		});
		detalleHistorico = new HistoricoForm(this);

		
		aniadirHistorico.addClickListener(e->{	
			aniadirHistorico.setVisible(false);
			aniadirHistorico();
		});
		
		cargaGridHistorico(null);
		gridHistorico.setFrozenColumnCount(1);
		gridHistorico.setSelectionMode(SelectionMode.SINGLE);	
		
		addComponents(gridHistorico,aniadirHistorico,detalleHistorico);
		
	}
private void aniadirHistorico() {	
		
		detalleHistorico.setVisible(true);		
		Historico h = new Historico("","",null);
		detalleHistorico.setHistorico(h);
		gridHistorico.setContainerDataSource(
				new BeanItemContainer<>(Historico.class, historicoService.listarHistorico())
				);
	}
	public void cargaGridHistorico(Historico historico) { 
		aniadirHistorico.setVisible(true);
		detalleHistorico.setVisible(false);
		if (historico != null){
			historicoService.modificarHistorico(historico);
		}		
		//TODO pasarle la lista ordenada por hora
		gridHistorico.setContainerDataSource(
				new BeanItemContainer<>(Historico.class, historicoService.listarHistorico())
				);
		detalleHistorico.setHistorico(null);
	}
}
