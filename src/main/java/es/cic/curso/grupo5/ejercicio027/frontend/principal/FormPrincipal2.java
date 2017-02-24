package es.cic.curso.grupo5.ejercicio027.frontend.principal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Dummie;
import es.cic.curso.grupo5.ejercicio027.backend.service.HistoricoService;
import es.cic.curso.grupo5.ejercicio027.frontend.secundarios.DummieForm;

@Theme("mytheme")
public class FormPrincipal2 extends UI {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -199465035744553464L;
	private Grid maestro;
	private DummieForm detalle;
	private List<Dummie> listaPeliculas;
	

	@Autowired
	private HistoricoService peliculaService;
	
	private Button accionAniadir;
	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        listaPeliculas = new ArrayList<>();
        listaPeliculas.add(new Dummie("PruebaPatatas", true, 777, 9127487528l, 1857f));
        listaPeliculas.add(new Dummie("PruebaPatatas2", true, 777, 9127487528l, 1857f));
        
        maestro = new Grid();
        maestro.setColumns("palabra","apagado","numero","numeroGran","numeroComas");
        
        cargaGrid();
        
        maestro.addSelectionListener(e -> 
        	{
        		Dummie p = null;
        		if (!e.getSelected().isEmpty() ) {
	        		p = (Dummie) e.getSelected().iterator().next();
        		} 
        		detalle.setDummie(p);
        	});
        
        detalle = new DummieForm(this);
        
        accionAniadir = new Button("Añadir");
		
		accionAniadir.addClickListener(e -> aniadirGrid());
		
		
        
        layout.addComponents(accionAniadir, maestro, detalle );
        layout.setMargin(true);
        layout.setSpacing(true);
       
        
        setContent(layout);
    }

	public void cargaGrid() {
		maestro.setContainerDataSource(
        		new BeanItemContainer<>(Dummie.class, listaPeliculas)
        );
	}
	
	public void aniadirGrid() {
		
		Dummie p = new Dummie("PruebaPatatas", true, 777, 9127487528l, 1857f);
		detalle.setDummie(p);
		peliculaService.aniadirClaseDummie("PruebaPatatas", true, 777, 9127487528l, 1857f);
		listaPeliculas.add(p);
		maestro.setContainerDataSource(
				
				new BeanItemContainer<>(Dummie.class, listaPeliculas)
        		
        );
	}
	
	public void borrarGrid(Dummie pelicula) {
		
		listaPeliculas.remove(pelicula);
		maestro.setContainerDataSource(
				new BeanItemContainer<>(Dummie.class, listaPeliculas)
        		
        );
		
		
	}
	
	@WebServlet(value = "/prueba2", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FormPrincipal2.class)
	public static class Servlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = -796390695740401802L;
	}
}
