package entities.Personas;

import java.time.LocalDate;

public class Empleado extends Persona {
	
	private LocalDate FechaNacimiento;
	
	private String Cuit;

	public LocalDate getFechaNacimiento() {
		return this.FechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.FechaNacimiento = fechaNacimiento;
	}

	public String getCuit() {
		return this.Cuit;
	}

	public void setCuit(String cuit) {
		this.Cuit = cuit;
	}
	
}
