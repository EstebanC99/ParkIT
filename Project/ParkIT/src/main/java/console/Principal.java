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
import global.TiposAlquileres;
import logic.Personas.EmpleadoLogic;

public class Principal {

	public static void main(String[] args) throws ValidationException {
		
		System.out.print(System.getProperty("user.dir"));
	}

}
