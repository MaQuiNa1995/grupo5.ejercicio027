package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.service.UsuarioService;
import es.cic.curso.grupo5.ejercicio027.frontend.principal.MyUI;

public class GestionUsuarios extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7897900102340873208L;
	private UsuarioService usuarioService;
	private List<Usuario> listaUsuarios;
	private UsuarioForm detalleUsuario;
	private NativeButton aniadirUsuario;
	private NativeButton modificar;
	private Grid gridUsuarios;
	@SuppressWarnings("unused")
	private MyUI padre;
	private ComboBox usuarios=new ComboBox();
	List<String> listaNombres = new ArrayList<>();
	//TODO añadir boton cancelar en modificar
	private NativeButton cancelar;

	public GestionUsuarios(MyUI padre){
		this.padre = padre;

		usuarioService = ContextLoader.getCurrentWebApplicationContext().getBean(UsuarioService.class);
		listaUsuarios = usuarioService.listarUsuario();
		final VerticalLayout  vertical = new VerticalLayout();
		vertical.setSpacing(true);
		final VerticalLayout  extra = new VerticalLayout();
		extra.setSpacing(true);

		if(listaUsuarios.isEmpty()){	
			usuarioService.generaBBDD();
		}

		aniadirUsuario = new NativeButton("Añadir Usuario");
		aniadirUsuario.setIcon(FontAwesome.PLUS);
		modificar = new NativeButton("modificar");
		modificar.setIcon(FontAwesome.PENCIL);
		
		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);

		gridUsuarios = new Grid();
		gridUsuarios.setWidth(1000, Unit.PIXELS);
		gridUsuarios.setColumns("nombre","password","rol","email");
		gridUsuarios.setFrozenColumnCount(1);
		gridUsuarios.setSelectionMode(SelectionMode.NONE);

		detalleUsuario = new UsuarioForm(this);

		aniadirUsuario.addClickListener(e->{	
			aniadirUsuario.setVisible(false);
			modificar.setVisible(false);
			aniadirUsuarios();
		});
		
		cancelar.addClickListener(e->{});
		modificar.addClickListener(e->{

			listaUsuarios = usuarioService.listarUsuario();
			listaNombres.clear();
			for(Usuario user :listaUsuarios){	
				listaNombres.add(user.getNombre());
			}
			usuarios = new ComboBox("Nombre",listaNombres);
			usuarios.setInputPrompt("Seleccione usuario a madificar");
			usuarios.setNullSelectionAllowed(false);
			usuarios.select(1);
			usuarios.setImmediate(true);
			usuarios.setWidth(300, Unit.PIXELS);

			aniadirUsuario.setVisible(false);
			modificar.setVisible(false);
			usuarios.setVisible(true);
			cancelar.setVisible(true);

			extra.addComponents(usuarios,cancelar);

			usuarios.addValueChangeListener(a->{


				for(Usuario user :listaUsuarios){
					if(usuarios.getValue()==(user.getNombre())){
						detalleUsuario.setVisible(true);
						detalleUsuario.setUsuario(user);
						cancelar.setVisible(false);
					}					
				}		
			});

			cancelar.addClickListener(a->{
				
				usuarios.setVisible(false);
				cancelar.setVisible(false);
				aniadirUsuario.setVisible(true);
				modificar.setVisible(true);
				
				
			});
			
			
			
			
		});

		vertical.addComponents(aniadirUsuario,modificar,extra,detalleUsuario);
		addComponents(gridUsuarios,vertical);

		cargaGridUsuarios(null);	
	}
	private void aniadirUsuarios() {	
		detalleUsuario.setVisible(true);
		Usuario u = new Usuario("","","","",false);
		detalleUsuario.setUsuario(u);
		gridUsuarios.setContainerDataSource(
				new BeanItemContainer<>(Usuario.class, listaUsuarios)
				);
	}

	public void cargaGridUsuarios(Usuario user) {
		modificar.setVisible(true);
		aniadirUsuario.setVisible(true);

		detalleUsuario.setVisible(false);
		usuarios.setVisible(false);

		if(user != null){
			usuarioService.modificarUsuario(user);
		}	

		listaUsuarios= usuarioService.listarUsuario();

		gridUsuarios.setContainerDataSource(
				new BeanItemContainer<>(Usuario.class, listaUsuarios)
				);
		detalleUsuario.setUsuario(null);
	}
}
