package console;

import java.util.LinkedList;

import entities.Alquileres.Alquiler;
import exceptions.ValidationException;
import logic.Alquileres.AdministrarAlquilerLogic;

public class Principal {

	public static void main(String[] args) throws ValidationException {
		
		System.out.print(System.getProperty("user.dir"));
		AdministrarAlquilerLogic a = new AdministrarAlquilerLogic();
		LinkedList<Alquiler> alquileres = a.getAlquileresUsuario(8);
		
		for(Alquiler alquiler : alquileres) {
			System.out.println(alquiler.getID());

		}
	}

}
