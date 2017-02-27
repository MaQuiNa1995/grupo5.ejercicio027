package es.cic.curso.grupo5.ejercicio027.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Grid.SelectionMode;
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
	private Historico historico; // se va a usar para eliminar y modificar
	private HistoricoService historicoService;
	@SuppressWarnings("unused")
	private MyUI padre;
	@Autowired
	private HistoricoConverter conv;
	private UsuarioService usuarioService;
	private List<HistoricoDTO> listaHistoricos;
	
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
			historico = null;
			if (!e.getSelected().isEmpty() ) {
				
				historico = (Historico) e.getSelected().iterator().next();
				detalleHistorico.setVisible(true);
				aniadirHistorico.setVisible(false);
				
			} else{
				
			  detalleHistorico.setVisible(false);
			  aniadirHistorico.setVisible(true);
				
			}
			detalleHistorico.setHistorico(historico);	
		});
		detalleHistorico = new HistoricoForm(this);

		
		aniadirHistorico.addClickListener(e->{	
			aniadirHistorico.setVisible(false);
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
				new BeanItemContainer<>(Historico.class, historicoService.listarHistorico())
				);
	}
/*	public void cargaGridHistorico(Historico historico, Usuario u) { 
		aniadirHistorico.setVisible(true);
		detalleHistorico.setVisible(false);
		if (historico != null){
			historicoService.modificarHistorico(historico);
		}	
		
		List<Historico> lista = historicoService.listarHistorico();
		List<HistoricoDTO> listaHistorico = new ArrayList<>();
		for(Historico h : lista){
			HistoricoDTO d = new HistoricoDTO();
			
			System.out.println("uuuuuuuuuuuuuuuuuuuuuuuu"+u);
			System.out.print(historico.getOperacion());
			if(u!=null){
				d = conv.entityToDto(historico,u);
				System.out.print("dddddddd");
				listaHistorico.add(d);
			}
		}
		
		gridHistorico.setContainerDataSource(
        		new BeanItemContainer<>(HistoricoDTO.class, listaHistorico)
        );
		
		
		
		
		
		//TODO pasarle la lista ordenada por hora
		gridHistorico.setContainerDataSource(
				new BeanItemContainer<>(Historico.class, historicoService.listarHistorico())
				);
		detalleHistorico.setHistorico(null);
	}
	*/
	public void cargaHistoricos(Historico historico){	
		
		//System.out.println("aa"+historico);
		if(historico!=null){
		
		
		List<Usuario> lista = usuarioService.listarUsuario();
		
		for(Usuario s : lista){
			HistoricoDTO v = new HistoricoDTO();
			
			if(historico.getUsuario().getNombre().equals(s.getNombre())){
				
				
				Usuario a = s;
				
				v = conv.entityToDto(historico, a);
				System.out.print("bbbbbbbbbbbbbbbbbbbbbbbbb" + v); 
				listaHistoricos.add(v);
				
				for(HistoricoDTO d: listaHistoricos){
					System.out.println("LISTA :  "+ d);
				}
			}
		}
		
		
		
	}
		gridHistorico.setContainerDataSource(
	    		new BeanItemContainer<>(HistoricoDTO.class, listaHistoricos)
	    );
	}
/*
	List<HistoricoDTO> listaHistoricos = new ArrayList<>();

	List<Usuario> listaUsuarios = usuarioService.listarUsuario();
	
	for(Usuario u : listaUsuarios){
		
			HistoricoDTO v = new HistoricoDTO();
		if(historico!=null){	
		if(historico.getNombre().equals(u.getNombre())){
			v.setUsuario(u.getNombre());
			listaHistoricos.add(v);
		}}
	}
	gridHistorico.setContainerDataSource(
    		new BeanItemContainer<>(HistoricoDTO.class, listaHistoricos)
    );*/
}
