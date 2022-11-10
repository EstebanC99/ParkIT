package entities.Usuarios;

public enum TipoUsuario {
	Empleado(1),
	Cliente(2);
	
	public int value;
	
	private TipoUsuario(int value) {
		this.value = value;
	}
}
