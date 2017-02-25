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
import com.vaadin.ui.Label;
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
	private TextField roles;
	private ComboBox nombres;

	private Historico historico;
	private List<Usuario> listaUsuarios;

	public HistoricoForm(MyUI padre) {
		this.padre = padre;

		usuarioService = ContextLoader.getCurrentWebApplicationContext().getBean(UsuarioService.class);	

		final HorizontalLayout horizontal1 = new HorizontalLayout();
		final HorizontalLayout horizontal2 = new HorizontalLayout();
		final HorizontalLayout horizontal3 = new HorizontalLayout();	
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
		
		nombres = new ComboBox("Nombre",listaNombres);
		nombres.setNullSelectionAllowed(false);
		nombres.select(1);
		nombres.setImmediate(true);
		nombres.setSizeFull();

		operacion = new ComboBox("Operación",listaOperaciones);
		operacion.setNullSelectionAllowed(false);
		operacion.select(1);
		operacion.setImmediate(true);
		operacion.setSizeFull();

		horas = new ComboBox("Hora",listaHoras);
		horas.setNullSelectionAllowed(false);
		horas.select(1);
		horas.setImmediate(true);
		horas.setWidth(80, Unit.PIXELS);
 
		minutos = new ComboBox("Minuto",listaMinutos);
		minutos.setNullSelectionAllowed(false);
		minutos.select(1);
		minutos.setImmediate(true);
		minutos.setWidth(80, Unit.PIXELS);
		
		roles = new TextField("rol del usuario");
		roles.setReadOnly(true);
		
		confirmar = new NativeButton("Confirmar histórico");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.TRASH);
		
//		nombres.addValueChangeListener(e->{
//			
//			for(Usuario user :listaUsuarios){
//				
//				if(nombres.getValue().equals(user.getNombre())){
//					
//				roles.setValue(user.getRol());	
//				 
//				}
//			}
//		});
		
				
		confirmar.addClickListener(e->{	

				if(operacion.getValue()==null||horas.getValue()==null|| minutos.getValue()==null || nombres.getValue()==null){	
				Notification sample = new Notification("Rellene todos los campos");
				mostrarNotificacion(sample);	
			}
			else{
				for(Usuario user :listaUsuarios){
					
					if(nombres.getValue().equals(user.getNombre())){
						
						historico.setUsuario(user);
					}
				}
				historico.setHora(horas.getValue() +":"+minutos.getValue());
				Notification sample = new Notification(nombres.getValue()+" realizo la operacion de: "+operacion.getValue());
				mostrarNotificacion(sample);
				nombres.clear();
				horas.clear();
				minutos.clear();
				padre.cargaGridHistorico(historico);
				
			}

		});

		cancelar.addClickListener(e->{

			nombres.clear();
			operacion.clear();
			horas.clear();
			minutos.clear();
			padre.cargaGridHistorico(null);

		});

		horizontal1.addComponents(nombres);
		horizontal2.addComponents(operacion,horas,minutos);
		horizontal3.addComponents(cancelar,confirmar);
		addComponents(horizontal1,horizontal2,horizontal3);	

		setHistorico(null);	
	}
	private void mostrarNotificacion(Notification sample2) {
		sample2.setDelayMsec(2000);
		sample2.show(Page.getCurrent());
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
