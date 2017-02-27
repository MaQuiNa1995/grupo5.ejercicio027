package es.cic.curso.grupo5.ejercicio027.backend.dto;

import org.springframework.stereotype.Component;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;


@Component
public class HistoricoConverter {
	public HistoricoDTO entityToDto(Historico historico, Usuario u) {
		System.out.println("resultadooooo");
		HistoricoDTO resultado = new HistoricoDTO();
		resultado.setUsuario(u.getNombre());
		resultado.setOperacion(historico.getOperacion());
		resultado.setHora(historico.getHora());
		
		return resultado;
		
	}
/*
	
	public Historico DTO2Entity(HistoricoDTO historicoDTO, UsuarioDTO usuarioDTO) {
		Historico resultado = new Historico();
		
		resultado.setUsuario(usuarioDTO.getNombre());
		resultado.setOperacion(historicoDTO.getOperacion());
		resultado.setHora(historicoDTO.getHora());
		return resultado;		
	}
	
	public List<HistoricoDTO> entity2DTO(List<Historico> historicos) {
		List<HistoricoDTO> resultado = new ArrayList<HistoricoDTO>();
		for(Historico historico: historicos) {
			resultado.add(entityToDto(historico));
		}
		return resultado;
	}
	
	public List<Historico> DTO2Entity(List<HistoricoDTO> historicosDTO) {
		List<Historico> resultado = new ArrayList<Historico>();
		for(HistoricoDTO historico: historicosDTO) {
			resultado.add(DTO2Entity(historico));
		};
		return resultado;		
	}	
*/
}
