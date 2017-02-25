package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.context.ContextLoader;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.service.UsuarioService;
import es.cic.curso.grupo5.ejercicio027.frontend.principal.MyUI;

public class HistoricoForm extends FormLayout {
	 
	private static final long serialVersionUID = -8212581707579739708L;
	private UsuarioService usuarioService;

	@SuppressWarnings("unused")
	private MyUI padre;

	@PropertyId("usuario")
	private Usuario usuario;
	@PropertyId("operacion")
	protected ComboBox operacion;
	@PropertyId("hora")
	protected String hora;

	private NativeButton confirmar;
	private NativeButton cancelar;
	private ComboBox horas;
	private ComboBox minutos;
	private TextField roleUser;
	private ComboBox nombreUser;
	private Historico historico;
	private List<Usuario> listaUsuarios;

	public HistoricoForm(MyUI padre) {
		this.padre = padre;

		usuarioService = ContextLoader.getCurrentWebApplicationContext().getBean(UsuarioService.class);	

		final HorizontalLayout horizontal1 = new HorizontalLayout();
		final HorizontalLayout horizontal2 = new HorizontalLayout();
		final HorizontalLayout horizontal3 = new HorizontalLayout();
		final HorizontalLayout horizontal4 = new HorizontalLayout();
		horizontal1.setSpacing(true);
		horizontal2.setSpacing(true);
		horizontal3.setSpacing(true);

		List<String> listaOperaciones = new ArrayList<>();
		listaOperaciones.add("borrar archivos");
		listaOperaciones.add("calcular costes de empresa");		
		listaOperaciones.add("realzar venta por internet");		
		listaOperaciones.add("ingresar nóminas");

		listaUsuarios = usuarioService.listarUsuario();
		List<String> listaNombres = new ArrayList<>();
		for(Usuario user :listaUsuarios){	
			listaNombres.add(user.getNombre());
 		}

		List<String> listaHoras = new ArrayList<>();
		for(int i =0;i<10;i++){		
			listaHoras.add("0"+String.valueOf(i));
		}
		for(int i =10;i<24;i++){		
			listaHoras.add(String.valueOf(i));
		}	 

		List<String> listaMinutos = new ArrayList<>();
		for(int i =0;i<10;i++){
			listaMinutos.add("0"+String.valueOf(i));
		}
		for(int i =10;i<60;i++){
			listaMinutos.add(String.valueOf(i));
		}
		
		nombreUser = new ComboBox("Nombre",listaNombres);
		nombreUser.setNullSelectionAllowed(false);
		nombreUser.select(1);
		nombreUser.setImmediate(true);
		nombreUser.setWidth(300, Unit.PIXELS);

		operacion = new ComboBox("Operación",listaOperaciones);
		operacion.setNullSelectionAllowed(false);
		operacion.select(1);
		operacion.setImmediate(true);
		operacion.setWidth(300, Unit.PIXELS);
		

		horas = new ComboBox("Hora",listaHoras);
		horas.setNullSelectionAllowed(false);
		horas.select(1);
		horas.setImmediate(true);
		horas.setWidth(90, Unit.PIXELS);
 
		minutos = new ComboBox("Minuto",listaMinutos);
		minutos.setNullSelectionAllowed(false);
		minutos.select(1);
		minutos.setImmediate(true);
		minutos.setWidth(90, Unit.PIXELS);
		
		roleUser = new TextField("rol del usuario");
		roleUser.setReadOnly(true);
		
		confirmar = new NativeButton("Registrar histórico");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);
	 	
		nombreUser.addValueChangeListener(e->{
			for(Usuario user :listaUsuarios){
				if(nombreUser.getValue()==(user.getNombre())){
					Notification sample = new Notification("Usuario con permisos de : "+user.getRol());
					mostrarNotificacion(sample); 
				}
			}
		});
				
		confirmar.addClickListener(e->{
			if(operacion.getValue()==null||horas.getValue()==null|| minutos.getValue()==null || nombreUser.getValue()==null ){	
				Notification sample = new Notification("Rellene todos los campos");
				mostrarNotificacion(sample);	
			}
			else{
				for(Usuario user :listaUsuarios){	
					if(nombreUser.getValue().equals(user.getNombre())){	
						historico.setUsuario(user);
					}
				}
				historico.setHora(horas.getValue() +":"+minutos.getValue());

				// Christian: Intento de linkar DTOal grid
//				Usuario usuario = new Usuario(nombreUser.getValue().toString(), "", "Administrador", "");
//				Historico historico = new Historico(operacion.getValue().toString(),
//						horas.getValue().toString()+":"+minutos.getValue().toString(),usuario);
//				Da error porque el container de historico no tiene un campo nombre
				
				Notification notificacionOperacion = new Notification(nombreUser.getValue()+""
						+ "realizo la operacion de: "+operacion.getValue());
				
				mostrarNotificacion(notificacionOperacion);
				nombreUser.clear();
				horas.clear();
				minutos.clear();
				padre.cargaGridHistorico(historico);			
			}
		});

		cancelar.addClickListener(e->{

			nombreUser.clear();
			operacion.clear();
			horas.clear();
			minutos.clear();
			padre.cargaGridHistorico(null);

		});

		horizontal1.addComponents(nombreUser);
		horizontal2.addComponents(operacion);
		horizontal3.addComponents(horas,minutos);
		addComponents(horizontal1,horizontal2,horizontal3,horizontal4,confirmar,cancelar);	

		setHistorico(null);	
	}
	private void mostrarNotificacion(Notification notificacion) {
		notificacion.setDelayMsec(2000);
		notificacion.show(Page.getCurrent());
	}
	public void setHistorico(Historico historico) {
		this.setVisible(historico != null);
		this.historico = historico;

		if (historico != null) {
			BeanFieldGroup.bindFieldsUnbuffered(historico, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Historico(), this);
		}
	}
}
