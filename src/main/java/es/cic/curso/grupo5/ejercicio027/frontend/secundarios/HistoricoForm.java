package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import es.cic.curso.grupo5.ejercicio027.frontend.principal.MyUI;

public class HistoricoForm extends FormLayout {
	
	 private MyUI padre;
	
	 	@PropertyId("nombre")
		private ComboBox nombre;
		@PropertyId("operacion")
		protected ComboBox operacion;
		@PropertyId("rol")
		protected TextField rol;
		@PropertyId("hora")
		protected TextField hora;
		 
		private NativeButton confirmar;
		private NativeButton cancelar;
		
		public HistoricoForm(MyUI padre) {
			this.padre = padre;
			
			
			final HorizontalLayout horizontal1 = new HorizontalLayout();
			final HorizontalLayout horizontal2 = new HorizontalLayout();
			final HorizontalLayout horizontal3 = new HorizontalLayout();	
			horizontal1.setSpacing(true);
			horizontal2.setSpacing(true);
			horizontal3.setSpacing(true);
			
			List<String> listaOperaciones = new ArrayList<>();
			listaOperaciones.add("Operacion 1");
			listaOperaciones.add("Operacion 2");		
			listaOperaciones.add("Operacion 3");		
			listaOperaciones.add("Operacion 4");
			
			List<String> listaUsuarios = new ArrayList<>();
			listaUsuarios.add("Usuario 1");
			listaUsuarios.add("Usuario 2");		
			listaUsuarios.add("Usuario 3");		
			listaUsuarios.add("Usuario 4");
			
			
			
			nombre = new ComboBox("Usuario",listaUsuarios);
			nombre.setNullSelectionAllowed(false);
			nombre.select(1);
			nombre.setImmediate(true);
			nombre.setSizeFull();
			
			operacion = new ComboBox("Operación",listaOperaciones);
			operacion.setNullSelectionAllowed(false);
			operacion.select(1);
			operacion.setImmediate(true);
			operacion.setSizeFull();
			
			rol = new TextField("Rol asociado");
			rol.setReadOnly(true);
			
			hora = new TextField("Hora");
			
			confirmar = new NativeButton("Confirmar histórico");
			confirmar.setIcon(FontAwesome.SAVE);
			
			cancelar = new NativeButton("Cancelar");
			cancelar.setIcon(FontAwesome.TRASH);
			
		
			
			confirmar.addClickListener(e->{	
				
				if(operacion.getValue()==null||nombre.getValue() == null){
					Notification sample = new Notification("Seleccione operacion y usuario");
					mostrarNotificacion(sample);	
				}
				else{
				 
					Notification sample = new Notification(operacion.getValue()+" y "+ nombre.getValue()+" seleccionado");
					mostrarNotificacion(sample);	
			 
				}
				
			});
			
			
			cancelar.addClickListener(e->{
				
				Notification sample = new Notification("Has cancelado");
				mostrarNotificacion(sample);	
				
				
			});
			
			
			horizontal1.addComponents(nombre,rol);
			horizontal2.addComponents(operacion,hora);
			horizontal3.addComponents(cancelar,confirmar);
			addComponents(horizontal1,horizontal2,horizontal3);	
				
		}
		private void mostrarNotificacion(Notification sample2) {
			sample2.setDelayMsec(1000);
			sample2.show(Page.getCurrent());
		}
}
