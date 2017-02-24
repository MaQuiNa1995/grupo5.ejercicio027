package es.cic.curso.grupo5.ejercicio027.frontend.principal;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
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

@Theme("mytheme")
public class MyUI extends UI {
 
	private static final long serialVersionUID = -8592441450121387521L;
	
	private TabSheet pestania;
	private NativeButton aniadirHistorico;
	private Grid gridHistorico;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		final VerticalLayout layout = new VerticalLayout();
		creaMargenes(layout);
		final VerticalLayout vlHistorico = new VerticalLayout();
		creaMargenes(vlHistorico);
		Label titulo = new Label("CONTROL DE ACCESOS");
		
		pestania = new TabSheet();
		pestania.setHeight(100.0f, Unit.PERCENTAGE);
		pestania.addTab(vlHistorico, "HISTORICO");
		
		HorizontalLayout hlGrids= new HorizontalLayout();
		hlGrids.setSpacing(true);
		hlGrids.setSizeFull();
		
	
		aniadirHistorico = new NativeButton("Añadir Registro");
		aniadirHistorico.setIcon(FontAwesome.PLUS);
		
		gridHistorico = new Grid();
		gridHistorico.setSizeFull();
		gridHistorico.setColumns("id","nombre","rol","operacion","hora");

		vlHistorico.addComponents(gridHistorico,aniadirHistorico);
		hlGrids.addComponents(pestania);
		layout.addComponents(titulo,hlGrids);	
		setContent(layout);
			
	}
	private void creaMargenes(final VerticalLayout lay) {
		lay.setMargin(true);
		lay.setSpacing(true);
		lay.setWidth("100%");
	}
	   @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	    public static class MyUIServlet extends VaadinServlet {
	 
			private static final long serialVersionUID = -2808018817235399677L;
	    }
}
