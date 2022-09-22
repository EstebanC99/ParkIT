package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuPrincipal
 */
@WebServlet("/MenuPrincipal")
public class MenuPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Accion> Acciones;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuPrincipal() {
		super();
		this.Acciones = new HashMap<String, Accion>();
		this.Acciones.put("AdministrarTipoVehiculo", new Accion() {
			public void ejecutar(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				response.sendRedirect("AdministrarTipoVehiculo");
			}
		});
		this.Acciones.put("AdministrarServicio", new Accion() {
			public void ejecutar(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				response.sendRedirect("AdministrarServicio");
			}
		});
		this.Acciones.put("AdministrarTipoCochera", new Accion() {
			public void ejecutar(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				response.sendRedirect("AdministrarTipoCochera");
			}
		});
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("Accion");

		try {
			this.Acciones.get(accion).ejecutar(request, response);
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					"La pagina solicitada no puede accederse en este momento. Por favor, regrese al menu principal!");
		}
	}

}
