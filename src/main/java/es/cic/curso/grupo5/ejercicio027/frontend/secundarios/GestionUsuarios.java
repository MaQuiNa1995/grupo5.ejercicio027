package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.service.UsuarioService;
import es.cic.curso.grupo5.ejercicio027.frontend.principal.MyUI;


public class GestionUsuarios extends HorizontalLayout {

	private Usuario usuario;
	private UsuarioService usuarioService;
	private List<Usuario> listaUsuarios;
	private UsuarioForm detalleUsuario;
	private NativeButton aniadirUsuario;
	Grid gridUsuarios;
	private MyUI padre;
	
	public GestionUsuarios(MyUI padre){
		this.padre = padre;
		usuarioService = ContextLoader.getCurrentWebApplicationContext().getBean(UsuarioService.class);
		
		listaUsuarios = usuarioService.listarUsuario();

		if(listaUsuarios.isEmpty()){	
			usuarioService.generaBBDD();
		}
		
		
		HorizontalLayout hlGrids= new HorizontalLayout();
		hlGrids.setSpacing(true);
		hlGrids.setSizeFull();
				
		aniadirUsuario = new NativeButton("Añadir Usuario");
		aniadirUsuario.setIcon(FontAwesome.PLUS);
		
		gridUsuarios = new Grid();
		gridUsuarios.setWidth(1000, Unit.PIXELS);
		
		
		gridUsuarios.setColumns("nombre","password","rol","email");
		gridUsuarios.addSelectionListener(e -> 
		{		
			usuario = null;
			if (!e.getSelected().isEmpty() ) {
				
				usuario = (Usuario) e.getSelected().iterator().next();
				detalleUsuario.setVisible(true);
				aniadirUsuario.setVisible(false);
				
			} else{
				
				detalleUsuario.setVisible(false);
				aniadirUsuario.setVisible(true);
				
			}
			detalleUsuario.setUsuario(usuario);	
		});
		detalleUsuario = new UsuarioForm(this);

		
		aniadirUsuario.addClickListener(e->{	
			aniadirUsuario.setVisible(false);
			aniadirUsuarios();
		});
	
		
		cargaGridUsuarios();
		gridUsuarios.setFrozenColumnCount(1);
		gridUsuarios.setSelectionMode(SelectionMode.SINGLE);	
		
		addComponents(gridUsuarios,aniadirUsuario,detalleUsuario);
		
	}
	private void aniadirUsuarios() {	
		
		detalleUsuario.setVisible(true);		
		Usuario u = new Usuario("","","","");
		detalleUsuario.setUsuario(u);
		gridUsuarios.setContainerDataSource(
				new BeanItemContainer<>(Usuario.class, usuarioService.listarUsuario())
				);
	}
	
	public void cargaGridUsuarios(Usuario usuario) { 
		padre.setVisible(false);
		if(usuario != null){
			usuarioService.modificarUsuario(usuario);
		}		
		if(!usuarioService.listarUsuario().isEmpty()){
		gridUsuarios.setContainerDataSource(
				new BeanItemContainer<>(Usuario.class, usuarioService.listarUsuario())
				);
		detalleUsuario.setUsuario(null);
		}
	}
	
	public void cargaGridUsuarios() {
		listaUsuarios = new ArrayList<>();
		listaUsuarios = usuarioService.listarUsuario();
		gridUsuarios.removeAllColumns();
		gridUsuarios.setContainerDataSource(
	    		new BeanItemContainer<>(Usuario.class, listaUsuarios ));
		}

	}
