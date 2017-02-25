package es.cic.curso.grupo5.ejercicio027.backend.dto;

import org.springframework.stereotype.Component;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;
import es.cic.curso.grupo5.ejercicio027.backend.dominio.Usuario;

@Component
public class HistoricoConverter {
	public HistoricoDTO entityToDto(Historico historico,Usuario usuario) {
		
		HistoricoDTO resultado = new HistoricoDTO();
		
		resultado.setNombre(usuario.getNombre());
		resultado.setRol(usuario.getRol());
		resultado.setOperacion(historico.getOperacion());
		resultado.setHora(historico.getHora());
		
		return resultado;
		
	}
	
}
