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

import es.cic.curso.grupo5.ejercicio027.frontend.secundarios.GridDummie;

@Theme("myTheme")
public class FormPrincipal extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1598331557630407298L;

	@Override
	protected void init(VaadinRequest request) {
		
		final VerticalLayout panelTodo = new VerticalLayout();
		panelTodo.setMargin(true);
		panelTodo.setSpacing(true);
		panelTodo.setWidth("100%");
		
		Label labelDummie = new Label("Prueba Dummie De La Puesta a Punto De Vaadin");

		final VerticalLayout gridFiguras = new GridDummie();

//		final VerticalLayout annadir = new AnnadirDummie();
//		final VerticalLayout eliminar = new EliminarDummie();
//		final VerticalLayout actualizar = new ActualizarDummie();
//		final VerticalLayout listar = new ListarDummie();

		TabSheet menu = new TabSheet();
        menu.setHeight(100.0f, Unit.PERCENTAGE);
        menu.addTab(gridFiguras, "Añadir Dummie");
        menu.addTab(gridFiguras, "Eliminar Dummie");
        menu.addTab(gridFiguras, "Actualizar Dummie");
        menu.addTab(gridFiguras, "Listar Dummie");
	
		panelTodo.addComponents(menu,labelDummie,gridFiguras);
		
		setContent(panelTodo);
	}
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FormPrincipal.class)
	public static class Servlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = -796390695740401802L;
	}
}
