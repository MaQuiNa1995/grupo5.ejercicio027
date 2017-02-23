package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Dummie;
import es.cic.curso.grupo5.ejercicio027.frontend.principal.FormPrincipal2;

public class DummieForm extends FormLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4792710372897838701L;
	
	@PropertyId("palabra")
	protected TextField palabra;
	
	@PropertyId("numero")
	protected TextField numero;
	
	@PropertyId("numeroGran")
	protected TextField numeroGran;
	
	@PropertyId("numeroComas")
	protected TextField numeroComas;
	
	private Button accion;
	
	private Button accionBorrar;
	
	private Dummie pelicula;
	
	private FormPrincipal2 padre;
	
	public DummieForm(FormPrincipal2 padre) {
		this.padre = padre;
		
		palabra = new TextField("Nombre: ");
		numero= new TextField("Apellido: ");
		numeroGran = new TextField("Año: ");
		numeroComas = new TextField ("Duración: ");
		
		accion = new Button("Actualizar");
		accion.addClickListener(e -> {padre.cargaGrid();
				setDummie(null);
		});
		
		
		accionBorrar = new Button("Borrar");
		accionBorrar.addClickListener(e -> padre.borrarGrid(this.pelicula));
	
		
		addComponents(palabra,numero,numeroGran,numeroComas, accion, accionBorrar);
		setDummie(null);
	}

	public void setDummie(Dummie pelicula) {
		this.setVisible(pelicula != null);
		this.pelicula = pelicula;

		if (pelicula != null) {
			BeanFieldGroup.bindFieldsUnbuffered(pelicula, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Dummie(), this);
		}
	}
}
