package console;

import java.time.LocalDate;

import data.PrecioAlquilerRepository;
import data.TipoAlquilerRepository;
import data.TipoCocheraRepository;
import entities.Alquileres.PrecioAlquiler;
import entities.Alquileres.TipoAlquiler;
import entities.Cocheras.TipoCochera;

public class Principal {

	public static void main(String[] args) {
		PrecioAlquilerRepository repository = new PrecioAlquilerRepository();
		
		TipoCocheraRepository tipoCocheraRepository = new TipoCocheraRepository();
		TipoCochera cochera = new TipoCochera();
		cochera.setID(1);
		cochera = tipoCocheraRepository.getByID(cochera);
		
		TipoAlquilerRepository tipoAlquilerRepository = new TipoAlquilerRepository();
		TipoAlquiler tipoAlquiler = new TipoAlquiler();
		tipoAlquiler.setID(5);
		tipoAlquiler = tipoAlquilerRepository.getByID(tipoAlquiler);
		
		PrecioAlquiler precio = new PrecioAlquiler();
		precio.setID(2);
		precio.setPrecio(Double.parseDouble("200.5"));
		precio.setFechaVigencia(LocalDate.of(2022, 11, 3));
		
		repository.update(precio);
		
		System.out.println(precio.getID());
	}

}
