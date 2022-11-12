package console;

import java.time.LocalDate;
import java.util.LinkedList;

import data.PrecioAlquilerRepository;
import data.TipoAlquilerRepository;
import data.TipoCocheraRepository;
import entities.Alquileres.Alquiler;
import entities.Alquileres.PrecioAlquiler;
import entities.Alquileres.TipoAlquiler;
import entities.Cocheras.TipoCochera;
import entities.Personas.Empleado;
import exceptions.ValidationException;
import global.TiposAlquileres;
import logic.Alquileres.AdministrarAlquilerLogic;
import logic.Personas.EmpleadoLogic;

public class Principal {

	public static void main(String[] args) throws ValidationException {
		
		System.out.print(System.getProperty("user.dir"));
		AdministrarAlquilerLogic a = new AdministrarAlquilerLogic();
		LinkedList<Alquiler> alquileres = a.getAlquileresUsuario(8);
		//System.out.println(alquileres);
		for(Alquiler alquiler : alquileres) {
			System.out.println(alquiler.getID());

		}
	}

}
