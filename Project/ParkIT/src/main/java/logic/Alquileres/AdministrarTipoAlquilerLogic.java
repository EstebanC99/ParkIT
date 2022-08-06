package logic.Alquileres;

import java.util.LinkedList;

import data.TipoAlquilerRepository;
import entities.Alquileres.TipoAlquiler;
import logic.LogicBase;

public class AdministrarTipoAlquilerLogic extends LogicBase<TipoAlquiler, TipoAlquilerRepository>{

	public AdministrarTipoAlquilerLogic() {
		
		this.Repository = TipoAlquilerRepository.getInstancia();
	}
	
	public LinkedList<TipoAlquiler> getAll(){
		
		return this.Repository.getAll();
	}
	
	public TipoAlquiler getByID(TipoAlquiler tipoAlquiler) {
		
		return this.Repository.getByID(tipoAlquiler);
	}
}
