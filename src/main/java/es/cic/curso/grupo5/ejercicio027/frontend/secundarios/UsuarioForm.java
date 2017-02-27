package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;

public class UsuarioForm extends FormLayout {
	 
	private static final long serialVersionUID = -8212581707579739708L;

	@PropertyId("nombre")
	private TextField nombre;
	@PropertyId("password")
	protected PasswordField password;
	@PropertyId("email")
	private TextField email;	
	@PropertyId("rol")
	protected ComboBox roles;
	
	private NativeButton confirmar;
	private NativeButton cancelar;	
	private Usuario usuario;

	@SuppressWarnings("unused")
	private GestionUsuarios padre;
	
	public UsuarioForm(GestionUsuarios padre) {
		this.padre = padre;

		final HorizontalLayout horizontal1 = new HorizontalLayout();
		final HorizontalLayout horizontal2 = new HorizontalLayout();
		final HorizontalLayout horizontal3 = new HorizontalLayout();
		final HorizontalLayout espacio = new HorizontalLayout();
		final HorizontalLayout horizontal5 = new HorizontalLayout();
		horizontal1.setSpacing(true);
		horizontal2.setSpacing(true);
		horizontal3.setSpacing(true);
		horizontal5.setSpacing(true);

		List<String> listaRoles = new ArrayList<>();
		listaRoles.add("administrador");
		listaRoles.add("supervisor");		
		listaRoles.add("editor");		
		listaRoles.add("invitado");
			
		nombre = new TextField("Nombre");
		nombre.setWidth(300, Unit.PIXELS);
		
		password = new PasswordField("Password");
		password.setWidth(300, Unit.PIXELS);
		
		email = new TextField("Email");
		email.setWidth(300, Unit.PIXELS);
		
		roles = new ComboBox("Rol",listaRoles);
		roles.setNullSelectionAllowed(false);
		roles.select(1);
		roles.setImmediate(true);
		roles.setWidth(300, Unit.PIXELS);
		
		confirmar = new NativeButton("Registrar/modificar");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);
	 
	
		confirmar.addClickListener(e->{
			if(roles.getValue()==null||"".equals(nombre.getValue())|| "".equals(password.getValue()) || "".equals(email.getValue())){
				Notification sample = new Notification("Rellene todos los campos");
				mostrarNotificacion(sample);	
			}else{
				Notification notificacionOperacion = 
						new Notification("El usuario : "+ nombre.getValue()+" ha sido dado de alta/modificado");
				
				mostrarNotificacion(notificacionOperacion);
				padre.cargaGridUsuarios(usuario);

			}
		});

		cancelar.addClickListener(e->{
		padre.cargaGridUsuarios(null);

		});

		horizontal1.addComponents(nombre, password);
		horizontal2.addComponents(roles);
		horizontal3.addComponents(email);
		horizontal5.addComponents(confirmar,cancelar);
		addComponents(horizontal1,horizontal2,horizontal3,espacio,horizontal5);	

		setUsuario(null);	
	}
	private void mostrarNotificacion(Notification notificacion) {
		notificacion.setDelayMsec(2000);
		notificacion.show(Page.getCurrent());
	}
	public void setUsuario(Usuario usuario) {
		this.setVisible(usuario != null);
		this.usuario = usuario;

		if (usuario != null) {
			BeanFieldGroup.bindFieldsUnbuffered(usuario, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Usuario(), this);
		}
	}
		
}
