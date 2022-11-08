package logic.Alquileres;

import java.time.LocalDate;

import data.ListaEsperaRepository;
import entities.Alquileres.ListaEspera;
import exceptions.ValidationException;
import logic.Logic;

public class ListaEsperaLogic extends Logic<ListaEspera, ListaEsperaRepository> {

	public ListaEsperaLogic() {
		this.Repository = ListaEsperaRepository.getInstancia();
	}
	
	private static ListaEsperaLogic Instancia;
	
	public static ListaEsperaLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new ListaEsperaLogic();
		}
		
		return Instancia;
	}
	
	@Override
	protected void validateAdd(ListaEspera listaEspera) throws ValidationException {
		this.validateRequiredFields(listaEspera);
		
		if (listaEspera.getFechaIngreso().isBefore(LocalDate.now()))
			throw new ValidationException("La Fecha de Ingreso no puede ser anterior a la fecha actual");
	}

	@Override
	protected void validateDelete(ListaEspera listaEspera) {

	}

	@Override
	protected void validateUpdate(ListaEspera listaEspera) throws ValidationException {
		this.validateRequiredFields(listaEspera);		
	}

	private void validateRequiredFields(ListaEspera listaEspera) throws ValidationException {
		if (listaEspera.getCliente() == null)
			throw new ValidationException("El Cliente es requerido");
		
		if (listaEspera.getTipoCochera() == null)
			throw new ValidationException("El Tipo de Cochera es requerido");
	}
	
}
