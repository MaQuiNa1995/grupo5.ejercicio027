package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.NativeButton;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;
import es.cic.curso.grupo5.ejercicio027.backend.dto.HistoricoConverter;
import es.cic.curso.grupo5.ejercicio027.backend.dto.HistoricoDTO;
import es.cic.curso.grupo5.ejercicio027.backend.service.HistoricoService;
import es.cic.curso.grupo5.ejercicio027.backend.service.UsuarioService;
import es.cic.curso.grupo5.ejercicio027.frontend.principal.MyUI;

public class GestionHistoricos  extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683850118394414599L;

	private NativeButton aniadirHistorico;
	Grid gridHistorico;
	private HistoricoForm detalleHistorico;
	private Historico historico; 
	private HistoricoDTO historicoDTO;
	private HistoricoService historicoService;
	@SuppressWarnings("unused")
	private MyUI padre;

	private HistoricoConverter conv = new HistoricoConverter();
	private UsuarioService usuarioService;
	private List<HistoricoDTO> listaHistoricos = new ArrayList<>();;
	
	public GestionHistoricos(MyUI padre){
		this.padre = padre;
		historicoService = ContextLoader.getCurrentWebApplicationContext().getBean(HistoricoService.class);	
		usuarioService = ContextLoader.getCurrentWebApplicationContext().getBean(UsuarioService.class);	

		
		aniadirHistorico = new NativeButton("Añadir Registro");
		aniadirHistorico.setIcon(FontAwesome.PLUS);
		gridHistorico = new Grid();
		
		gridHistorico.setWidth(1000, Unit.PIXELS);
		
		
		gridHistorico.setColumns("usuario","operacion","hora");
		gridHistorico.addSelectionListener(e -> 
		{		
			historicoDTO = null;
			if (!e.getSelected().isEmpty() ) {
				
				historicoDTO = (HistoricoDTO) e.getSelected().iterator().next();
				detalleHistorico.setVisible(true);
				aniadirHistorico.setVisible(false);
				detalleHistorico.atualizarUsuarios();
				
			} else{
				
			  detalleHistorico.setVisible(false);
			  aniadirHistorico.setVisible(true);
				
			}
			detalleHistorico.setHistorico(historico);	
		});
		detalleHistorico = new HistoricoForm(this);

		
		aniadirHistorico.addClickListener(e->{	
			aniadirHistorico.setVisible(false);
			detalleHistorico.atualizarUsuarios();
			aniadirHistorico();
		});
		
		cargaHistoricos(null);
		gridHistorico.setFrozenColumnCount(1);
		gridHistorico.setSelectionMode(SelectionMode.SINGLE);	
		
		addComponents(gridHistorico,aniadirHistorico,detalleHistorico);
		
	}
	private void aniadirHistorico() {	
		
		detalleHistorico.setVisible(true);
		Historico h = new Historico("","",null);
		detalleHistorico.setHistorico(h);
		gridHistorico.setContainerDataSource(
				new BeanItemContainer<>(HistoricoDTO.class, listaHistoricos)
				);
	}


	public void cargaHistoricos(Historico historico){	
		List<Usuario> u = usuarioService.listarUsuario();

		aniadirHistorico.setVisible(true);
		detalleHistorico.setVisible(false);
		if(historico!=null){
			
			for(Usuario user: u){
				
				if(historico.getUsuario().getNombre().equals(user.getNombre())){
					HistoricoDTO d = new HistoricoDTO();
					d = conv.entityToDto(historico, user);
					listaHistoricos.add(d);

				}
				 
			}
		}
	

	
		 
		List<HistoricoDTO> lista  = new ArrayList<>();
		lista = listaHistoricos;
	 	
		lista.sort(Comparator.comparing(HistoricoDTO::getHora).reversed());

		
		gridHistorico.setContainerDataSource(

	  
				new BeanItemContainer<>(HistoricoDTO.class, lista)
				);
		detalleHistorico.setHistorico(null);

	}

}

