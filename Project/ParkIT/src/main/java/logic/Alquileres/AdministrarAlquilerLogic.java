package logic.Alquileres;

import java.util.LinkedList;

import data.AlquilerRepository;
import dto.Filtros.FiltroAlquileres;
import entities.Alquileres.Alquiler;
import exceptions.ValidationException;
import logic.Logic;

public class AdministrarAlquilerLogic extends Logic<Alquiler, AlquilerRepository> {

	public AdministrarAlquilerLogic() {
		this.Repository = AlquilerRepository.getInstancia();
	}
	
	private static AdministrarAlquilerLogic Instancia;
	
	public static AdministrarAlquilerLogic getInstancia() {
		if (Instancia == null)
			Instancia = new AdministrarAlquilerLogic();
		
		return Instancia;
	}
	
	@Override
	public void add(Alquiler alquiler) throws ValidationException {
		alquiler.setPrecio(PrecioAlquilerLogic.getInstancia().obtenerPrecioVigente(alquiler.getTipoAlquiler(), alquiler.getCochera().getTipoCochera()));
		
		super.add(alquiler);
	}
	
	@Override
	protected void validateAdd(Alquiler alquiler) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(Alquiler alquiler) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(Alquiler alquiler) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	public LinkedList<Alquiler> searchByFilter(FiltroAlquileres filtro){
		return this.Repository.searchByFilters(filtro);
	}
}
