package entities.Usuarios;

import entities.BaseEntity;

public class Usuario extends BaseEntity {

	private String NombreUsuario;
	private String Password;
	private boolean Activo;
	private TipoUsuario Tipo;
	
	public String getNombreUsuario() {
		return NombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		NombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public boolean isActivo() {
		return Activo;
	}
	public void setActivo(boolean activo) {
		Activo = activo;
	}
	
	public TipoUsuario getTipo() {
		return this.Tipo;
	}
	
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.Tipo = tipoUsuario;
	}
	
	public void setTipoUsuario(int tipoUsuario) {
		if (tipoUsuario == TipoUsuario.Empleado.value) {
			this.Tipo = TipoUsuario.Empleado;
		}
		if (tipoUsuario == TipoUsuario.Cliente.value) {
			this.Tipo = TipoUsuario.Cliente;
		}
	}
	
}
