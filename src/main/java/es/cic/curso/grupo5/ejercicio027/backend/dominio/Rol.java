package es.cic.curso.grupo5.ejercicio027.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.cic.curso.grupo5.ejercicio027.backend.repository.Identificable;

@Entity
@Table (name="ROL")
public class Rol implements Identificable<Long>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4060663225027873388L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
	
	@Column(name ="rol")
    String rol;
	
	@JoinColumn(name = "idoperacion")
	@ManyToOne(fetch = FetchType.LAZY) //@ManyToOne(fetch = FetchType.EAGER)//modificado para probar sin dtos
	private Operacion operacion;

	
	public Rol() {
		super();
	}
	
	public Rol(String rol, Operacion operacion) {
		super();
		this.rol = rol;
		this.operacion = operacion;
	}
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Operacion getOperacion() {
		return operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

	
}