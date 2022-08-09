package logic.Alquileres;

import data.TipoAlquilerRepository;
import entities.Alquileres.TipoAlquiler;
import logic.LogicBase;

public class AdministrarTipoAlquilerLogic extends LogicBase<TipoAlquiler, TipoAlquilerRepository>{

	private static AdministrarTipoAlquilerLogic instancia;
	
	public static AdministrarTipoAlquilerLogic getIntancia() {
		if (instancia == null) {
			instancia = new AdministrarTipoAlquilerLogic();
		}
		
		return instancia;
	}
	
	public AdministrarTipoAlquilerLogic() {
		
		this.Repository = TipoAlquilerRepository.getInstancia();
	}
	
}
