package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuarios.TipoUsuario;
import entities.Usuarios.Usuario;
import logic.Usuarios.LoginLogic;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		LoginLogic ctrl = new LoginLogic();

		String nombreUsuario = request.getParameter("nombreUsuario");
		String password = request.getParameter("password");
		usuario.setNombreUsuario(nombreUsuario);
		usuario.setPassword(password);
		usuario = ctrl.valdiar(usuario);
		request.getSession().getAttribute("usuario");
		if (usuario == null) {
			response.sendRedirect("Login.jsp?respuesta=0");
		} else {
			request.getSession().setAttribute("usuario", usuario);
			request.getSession().getAttribute("usuario");
			if (usuario.getTipo() == TipoUsuario.Empleado) {
				response.sendRedirect("Index.jsp");
			} else {
				response.sendRedirect("InformacionCliente.jsp");
			}
		}
	}
}
