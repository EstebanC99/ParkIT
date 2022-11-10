package entities.Personas;

import entities.BaseEntity;
import entities.Usuarios.Usuario;

public abstract class Persona extends BaseEntity {
	
	public Persona() {
		this.User = new Usuario();
	}
	
	private String Nombre;
	
	private String Apellido;
	
	private String DNI;
	
	private String Email;
	
	private String Telefono;
	
	private String Direccion;
	
	private Usuario User;

	public String getNombre() {
		return this.Nombre;
	}
	public void setNombre(String nombre) {
		this.Nombre = nombre.toUpperCase();
	}
	public String getApellido() {
		return this.Apellido;
	}
	public void setApellido(String apellido) {
		this.Apellido = apellido.toUpperCase();
	}
	public String getDNI() {
		return this.DNI;
	}
	public void setDNI(String dni) {
		this.DNI = dni;
	}
	public String getEmail() {
		return this.Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	public String getTelefono() {
		return this.Telefono;
	}
	public void setTelefono(String telefono) {
		this.Telefono = telefono;
	}
	public String getDireccion() {
		return this.Direccion;
	}
	public void setDireccion(String direccion) {
		this.Direccion = direccion;
	}
	public void setUser(Usuario usuario) {
		this.User = usuario;
	}
	public Usuario getUser() {
		return this.User;
	}
	
	@Override
	public String toString() {
		return String.join(", ", this.Apellido, this.Nombre);
	}
	
}
