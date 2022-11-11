package logic.Usuarios;

import java.util.LinkedList;

import data.UsuarioRepository;
import entities.Usuarios.Usuario;

public class LoginLogic {
	private UsuarioRepository usuarioRep;
	
	public LoginLogic() {
		usuarioRep = new UsuarioRepository();
	}
	
	public Usuario valdiar(Usuario us) {
		
		return usuarioRep.getByUserAndPassword(us);
	}
	public LinkedList<Usuario> getAll(){
		return usuarioRep.getAll();
	}
}
