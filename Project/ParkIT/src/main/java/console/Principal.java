package console;

import java.time.LocalDate;

import data.PrecioAlquilerRepository;
import data.TipoAlquilerRepository;
import data.TipoCocheraRepository;
import entities.Alquileres.PrecioAlquiler;
import entities.Alquileres.TipoAlquiler;
import entities.Cocheras.TipoCochera;
import entities.Personas.Empleado;
import exceptions.ValidationException;
import logic.Personas.EmpleadoLogic;

public class Principal {

	public static void main(String[] args) throws ValidationException {
		
		Empleado empleado = new Empleado();
		empleado.setNombre("Esteban");
		empleado.setApellido("Carignani");
		empleado.setDNI("41567829");
		empleado.setEmail("estebancarignani99@gmail.com");
		empleado.setTelefono("3364562256");
		empleado.setDireccion("Alem 330");
		empleado.setFechaNacimiento(LocalDate.of(1999, 01, 13));
		empleado.setCuit("20415678291");
		
		EmpleadoLogic.getInstancia().add(empleado);
	}

}
