package es.cic.curso.grupo5.ejercicio027.backend.dto;

public class UsuarioDTO {

    Long id;
    String nombre;
    String password;
    String rol;
    String email;

	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(String nombre, String password, String rol, String email) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
		this.email = email;
	}
	
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id=id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}