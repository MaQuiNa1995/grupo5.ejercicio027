package es.cic.curso.grupo5.ejercicio027.frontend.principal;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import es.cic.curso.grupo5.ejercicio027.frontend.secundarios.GestionHistoricos;
import es.cic.curso.grupo5.ejercicio027.frontend.secundarios.GestionUsuarios;

@Theme("mytheme")
public class MyUI extends UI {
	private static final long serialVersionUID = 1187671189585917081L;
	private TabSheet pestania;

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("100%");
		final HorizontalLayout hlUsuarios = new GestionUsuarios(this);
		hlUsuarios.setMargin(true);
		hlUsuarios.setSpacing(true);
		final HorizontalLayout hlHistorico = new GestionHistoricos(this);
		hlHistorico.setMargin(true);
		hlHistorico.setSpacing(true);
			
		Label titulo = new Label("CONTROL DE ACCESOS");
		pestania = new TabSheet();
		pestania.setHeight(100.0f, Unit.PERCENTAGE);
		pestania.addTab(hlHistorico, "HISTORICO");
		pestania.addTab(hlUsuarios, "USUARIOS");

		layout.addComponents(titulo,pestania);	
		setContent(layout);						
	}
 
	   @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = -692740140427143858L;	 
	   }
}
