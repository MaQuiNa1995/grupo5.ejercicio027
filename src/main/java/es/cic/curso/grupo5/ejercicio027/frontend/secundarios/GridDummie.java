package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Dummie;
import es.cic.curso.grupo5.ejercicio027.backend.service.ClaseDummieService;

public class GridDummie extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9073905350776815176L;

	Dummie dummie;
	Grid gridDummies;
	
	private ClaseDummieService dummieService;
	List<Dummie> listaDummies;

	public GridDummie() {
		dummieService = ContextLoader.getCurrentWebApplicationContext().getBean(ClaseDummieService.class);
		
		listaDummies = new ArrayList<>();
		gridDummies = new Grid();
		//gridDummies.setSizeFull();
		
		gridDummies.setColumns("palabra","apagado","numero","numeroGran","numeroComas");
		
		cargarDatosPrueba2();
		
		cargaGridDummie();
		
		
		
		gridDummies.addSelectionListener(e -> 
    	{
    		@SuppressWarnings("unused")
			Dummie dummie = null;
    		if (!e.getSelected().isEmpty() ) {
        		dummie = (Dummie) e.getSelected().iterator().next();
    		}
    		
    	});
		
		gridDummies.setFrozenColumnCount(1);
		gridDummies.setWidth(100.0f,Unit.PERCENTAGE);
		gridDummies.setSelectionMode(SelectionMode.SINGLE);
		
		addComponent(gridDummies);
	}

	public void cargaGridDummie() {
		
		gridDummies.removeAllColumns();
		gridDummies.setContainerDataSource(
			new BeanItemContainer<>(
				Dummie.class, dummieService.obtenerClaseDummies()
			)
		);
	}
	
	public void setDummie(Dummie dummie) {
		this.setVisible(dummie != null);
		this.dummie = dummie;

		if (dummie != null) {
			BeanFieldGroup.bindFieldsUnbuffered(dummie, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Dummie(), this);
		}
	}
	
	public void cargarDatosPrueba2(){
		
		if(!listaDummies.isEmpty()){
			
			Dummie dummieAnadir = new Dummie("PruebaPatatas", true, 777, 9127487528l, 1857f);
			
			/**
			 * mete primer dummie
			 */
			dummieService.aniadirClaseDummie(
					dummieAnadir.getPalabra(), dummieAnadir.isApagado(),
					dummieAnadir.getNumero(), dummieAnadir.getNumeroGran(),
					dummieAnadir.getNumeroComas());
			
			setDummie(dummieAnadir);
			listaDummies.add(dummieAnadir);
			
			dummieAnadir.setPalabra("Filete");
			
			/**
			 * mete segundo dummie
			 */
			dummieService.aniadirClaseDummie(
					dummieAnadir.getPalabra(), dummieAnadir.isApagado(),
					dummieAnadir.getNumero(), dummieAnadir.getNumeroGran(),
					dummieAnadir.getNumeroComas());
			setDummie(dummieAnadir);
			listaDummies.add(dummieAnadir);
			
			dummieAnadir.setPalabra("Aiur");
			
			/**
			 * mete tercer dummie
			 */
			dummieService.aniadirClaseDummie(
					dummieAnadir.getPalabra(), dummieAnadir.isApagado(),
					dummieAnadir.getNumero(), dummieAnadir.getNumeroGran(),
					dummieAnadir.getNumeroComas());
			
			setDummie(dummieAnadir);
			listaDummies.add(dummieAnadir);
		}
		
	}
	
	public void cargarDatosPrueba(){
		
		if(!listaDummies.isEmpty()){
			
			dummieService.aniadirClaseDummie("PruebaPatatas", true, 777, 9127487528l, 1857f);
			dummieService.aniadirClaseDummie("PruebaPatatas2", true, 777, 9127487528l, 1857f);
			dummieService.aniadirClaseDummie("PruebaPatatas3", true, 777, 9127487528l, 1857f);
			setDummie(dummie);
		}
		
	}
//	
//	public void aniadirGrid(Dummie dummie) {
//		setVenta(dummie);
//		dummieService.aniadirClaseDummie(dummie.getPalabra(), dummie.isApagado(),
//				dummie.getNumero(), dummie.getNumeroGran(), dummie.getNumeroComas());
//		listaDummies.add(dummie);
//		
//		gridDummies.setContainerDataSource(
//				new BeanItemContainer<>(Dummie.class, listaDummies)
//        );
//	}

}