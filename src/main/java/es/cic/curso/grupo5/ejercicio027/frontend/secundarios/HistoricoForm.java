package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.service.HistoricoService;
import es.cic.curso.grupo5.ejercicio027.backend.service.UsuarioService;


public class HistoricoForm extends FormLayout {
	 
	private static final long serialVersionUID = -8212581707579739708L;
	private UsuarioService usuarioService;

	@SuppressWarnings("unused")
	private GestionHistoricos padre;

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
	private ComboBox nombreUser=new ComboBox();
	private Historico historico;
	private List<Usuario> listaUsuarios;
	private final HorizontalLayout horizontal1;
 
	private HistoricoService historicoService;
 
	private List<String> listaNombres= new ArrayList<>();
	private NativeButton actualizar;
 

	public HistoricoForm(GestionHistoricos padre) {
		this.padre = padre;
		historico = new Historico();
		usuarioService = ContextLoader.getCurrentWebApplicationContext().getBean(UsuarioService.class);	
		historicoService = ContextLoader.getCurrentWebApplicationContext().getBean(HistoricoService.class);	

		horizontal1 = new HorizontalLayout();
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
        
        
		operacion = new ComboBox("Operación",listaOperaciones);
		operacion.setNullSelectionAllowed(false);
		operacion.select(1);
		operacion.setImmediate(true);
		operacion.setWidth(300, Unit.PIXELS);
		operacion.setInputPrompt("seleccione operacion");
		

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
	 
		

		
		confirmar = new NativeButton("Registrar histórico");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);
	 	
		
				
		confirmar.addClickListener(e->{
			if(operacion.getValue()==null||horas.getValue()==null|| minutos.getValue()==null || historico.getUsuario()==null){	
				Notification sample = new Notification("Rellene todos los campos");
				mostrarNotificacion(sample);	
			}
			else{
				 
				historico.setHora(horas.getValue() +":"+minutos.getValue());
 
				historicoService.aniadirHistorico(historico);
			
				
 
				Notification notificacionOperacion = new Notification(nombreUser.getValue()+""
						+ "realizo la operacion de: "+operacion.getValue());
				
				mostrarNotificacion(notificacionOperacion);
				nombreUser.clear();
				nombreUser.setVisible(false);
				horas.clear();
				minutos.clear();
				padre.cargaHistoricos(historico);
				operacion.clear();
				setHistorico(null);
			}
		});

		cancelar.addClickListener(e->{


			nombreUser.clear();
			nombreUser.setVisible(false);
			horas.clear();
			minutos.clear();
			padre.cargaHistoricos(null);
			operacion.clear();

		});
		 
		horizontal2.addComponents(operacion);
		horizontal3.addComponents(horas,minutos);
	

		addComponents(horizontal1,horizontal2,horizontal3,horizontal4,confirmar,cancelar);	


		
	}
	public void atualizarUsuarios() {
		listaUsuarios = usuarioService.listarUsuario();
		listaNombres.clear();
		for(Usuario user :listaUsuarios){	
			if(user.isActivo()){
			listaNombres.add(user.getNombre());
			}
		}
		
		nombreUser = new ComboBox("Nombre",listaNombres);
		nombreUser.setNullSelectionAllowed(false);
		nombreUser.select(1);
		nombreUser.setImmediate(true);
		nombreUser.setInputPrompt("seleccione usuario");
		nombreUser.setWidth(300, Unit.PIXELS);
		
		nombreUser.addValueChangeListener(a->{
			for(Usuario user :listaUsuarios){
				if(nombreUser.getValue()==(user.getNombre())){
					Notification sample = new Notification("Usuario con permisos de : "+user.getRol());
					mostrarNotificacion(sample);
					historico.setUsuario(user);
				}
			}			
		});
		
		horizontal1.addComponent(nombreUser);
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
