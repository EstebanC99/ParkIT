package logic.Usuarios;

import data.UsuarioRepository;
import entities.Usuarios.Usuario;
import exceptions.ValidationException;
import logic.Logic;

public class UsuarioLogic extends Logic<Usuario, UsuarioRepository> {

	private static UsuarioLogic Instancia;
	public static UsuarioLogic getInstancia() {
		if (Instancia == null)
			Instancia = new UsuarioLogic();
		
		return Instancia;
	}
	
	public UsuarioLogic() {
		this.Repository = UsuarioRepository.getInstancia();
	}
	
	@Override
	protected void validateAdd(Usuario usuario) throws ValidationException {
		this.canAddOrUpdate(usuario);
		
	}

	@Override
	protected void validateDelete(Usuario myEntity) throws ValidationException {
		
	}

	@Override
	protected void validateUpdate(Usuario usuario) throws ValidationException {
		this.canAddOrUpdate(usuario);
		
	}

	public void canAddOrUpdate(Usuario usuario) throws ValidationException {
		this.validateNotExistingUser(usuario);
	}
	
	private void validateNotExistingUser(Usuario usuario) throws ValidationException {
		Usuario usuarioExistente = this.Repository.getByUser(usuario);
		
		if (usuarioExistente != null && (usuarioExistente.getID() != usuario.getID() || usuario.getID() != 0)) {
			throw new ValidationException("El nombre de Usuario ya existe");
		}
	}
}
