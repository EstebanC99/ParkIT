package console;

import java.util.LinkedList;

import data.PrecioAlquilerRepository;
import entities.Alquileres.PrecioAlquiler;

public class Principal {

	public static void main(String[] args) {
		PrecioAlquilerRepository repository = new PrecioAlquilerRepository();
		
		PrecioAlquiler precio = new PrecioAlquiler();
		precio.setID(1);
		repository.getByID(precio);
		
		LinkedList<PrecioAlquiler> lista = repository.getAll();
		
		System.out.print(String.join(" ", String.valueOf(precio.getID()), 
														String.valueOf(precio.getPrecio()),
													    precio.getFechaVigencia().toString(), 
													    precio.getTipoAlquiler().getDescripcion(), 
													    precio.getTipoCochera().getDescripcion()));
	}

}
