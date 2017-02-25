package es.cic.curso.grupo5.ejercicio027.frontend.principal;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.ContextLoader;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.service.HistoricoService;
import es.cic.curso.grupo5.ejercicio027.backend.service.UsuarioService;
import es.cic.curso.grupo5.ejercicio027.frontend.secundarios.GestionUsuarios;
import es.cic.curso.grupo5.ejercicio027.frontend.secundarios.HistoricoForm;

@Theme("mytheme")
public class MyUI extends UI {
	private static final long serialVersionUID = 1187671189585917081L;
	private TabSheet pestania;
	private NativeButton aniadirHistorico;
	private Grid gridHistorico;
	private HistoricoForm detalleHistorico;
	private Historico historico; // se va a usar para eliminar y modificar
	private HistoricoService historicoService;
	private UsuarioService usuarioService;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		historicoService = ContextLoader.getCurrentWebApplicationContext().getBean(HistoricoService.class);	
		usuarioService = ContextLoader.getCurrentWebApplicationContext().getBean(UsuarioService.class);
		
		//======================================================================================================
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("100%");
		final HorizontalLayout hlHistorico = new HorizontalLayout();
		hlHistorico.setMargin(true);
		hlHistorico.setSpacing(true);
		final HorizontalLayout hlUsuarios = new GestionUsuarios(this);
		hlUsuarios.setMargin(true);
		hlUsuarios.setSpacing(true);
		
		Label titulo = new Label("CONTROL DE ACCESOS");
		pestania = new TabSheet();
		pestania.setHeight(100.0f, Unit.PERCENTAGE);
		pestania.addTab(hlHistorico, "HISTORICO");
		pestania.addTab(hlUsuarios, "USUARIOS");
		
		HorizontalLayout hlGrids= new HorizontalLayout();
		hlGrids.setSpacing(true);
		hlGrids.setSizeFull();
				
		aniadirHistorico = new NativeButton("Añadir Registro");
		aniadirHistorico.setIcon(FontAwesome.PLUS);
		gridHistorico = new Grid();
		
		gridHistorico.setWidth(1000, Unit.PIXELS);
		
		//gridHistorico.sort("nombre");
		
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
	
		hlHistorico.addComponents(gridHistorico,aniadirHistorico,detalleHistorico);
		hlGrids.addComponents(pestania);
		layout.addComponents(titulo,hlGrids);	
		setContent(layout);
		
		cargaGridHistorico(null);
		gridHistorico.setFrozenColumnCount(1);
		gridHistorico.setSelectionMode(SelectionMode.SINGLE);				
	}
	//================================================================================================
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
	
	
	   @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = -692740140427143858L;	 
	   }
}
