package es.cic.curso.grupo5.ejercicio027.frontend.principal;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.ContextLoader;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.service.HistoricoService;
import es.cic.curso.grupo5.ejercicio027.backend.service.UsuarioService;
import es.cic.curso.grupo5.ejercicio027.frontend.secundarios.HistoricoForm;

@Theme("mytheme")
public class MyUI extends UI {
	private static final long serialVersionUID = 1187671189585917081L;
	private TabSheet pestania;
	private NativeButton aniadirHistorico;
	private Grid gridHistorico;
	private HistoricoForm detalleHistorico;
	private Historico historico;
	private HistoricoService historicoService;
	private UsuarioService usuarioService;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		historicoService = ContextLoader.getCurrentWebApplicationContext().getBean(HistoricoService.class);	
		usuarioService = ContextLoader.getCurrentWebApplicationContext().getBean(UsuarioService.class);	
		
		//TODO generar bbdd meterno en el service=============================================================
		Usuario usuario1 = new Usuario("Juan González del Olmo", "juan", "administrador", "juan@hotmail.com");
		Usuario usuario2 = new Usuario("Jose Giménez Sánchez", "pepe", "invitado", "pepe@hotmail.com");
		Usuario usuario3 = new Usuario("Pedro de la torre García", "pedro", "supervisor", "pedro@hotmail.com");
		
		usuarioService.aniadirUsuario(usuario1);
		usuarioService.aniadirUsuario(usuario2);
		usuarioService.aniadirUsuario(usuario3);
		//======================================================================================================
		final VerticalLayout layout = new VerticalLayout();
		creaMargenes(layout);
		final VerticalLayout hlHistorico = new VerticalLayout();
		creaMargenes(hlHistorico);
		
		Label titulo = new Label("CONTROL DE ACCESOS");
		
		pestania = new TabSheet();
		pestania.setHeight(100.0f, Unit.PERCENTAGE);
		pestania.addTab(hlHistorico, "HISTORICO");
		
		HorizontalLayout hlGrids= new HorizontalLayout();
		hlGrids.setSpacing(true);
		hlGrids.setSizeFull();
		
		
		aniadirHistorico = new NativeButton("Añadir Registro");
		aniadirHistorico.setIcon(FontAwesome.PLUS);
		detalleHistorico = new HistoricoForm(this);
		detalleHistorico.setVisible(false);
		
		aniadirHistorico.addClickListener(e->{
			
			aniadirHistorico.setVisible(false);
			aniadirHistorico();
		});
		
		gridHistorico = new Grid();
		gridHistorico.setSizeFull();
		gridHistorico.setColumns("id","nombre","rol","operacion","hora");
	
		hlHistorico.addComponents(gridHistorico,aniadirHistorico,detalleHistorico);
		hlGrids.addComponents(pestania);
		layout.addComponents(titulo,hlGrids);	
		setContent(layout);
		
		cargaGridHistorico(null);
		gridHistorico.setFrozenColumnCount(1);
		gridHistorico.setSelectionMode(SelectionMode.SINGLE);
				
	}
	
	private void aniadirHistorico() {	
		
		detalleHistorico.setVisible(true);		
		Historico h = new Historico("","",null);
		detalleHistorico.setHistorico(h);
		gridHistorico.setContainerDataSource(
				new BeanItemContainer<>(Historico.class, historicoService.listarHistorico())
				);
	}
	private void creaMargenes(final VerticalLayout lay) {
		lay.setMargin(true);
		lay.setSpacing(true);
		lay.setWidth("100%");
	}
	public void cargaGridHistorico(Historico historico) { 
		aniadirHistorico.setVisible(true);
		if (historico != null){
			historicoService.modificarHistorico(historico);
		}		
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
