package es.cic.curso.grupo5.ejercicio027.backend.dto;

public class HistoricoDTO {
	String usuario;
	String operacion;
	String hora;
	
	public HistoricoDTO() {
		super();
	}
	
	public String getOperacion() {
		return operacion;
	}
	
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
