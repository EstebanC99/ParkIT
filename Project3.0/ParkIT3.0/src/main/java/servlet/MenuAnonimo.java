package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cocheras.Cochera;
import logic.Cocheras.AdministrarTipoCocheraLogic;
import logic.Cocheras.CocheraLogic;
import logic.Servicios.AdministrarServicioLogic;
import entities.Servicios.Servicio;

import logs.Log;

/**
 * Servlet implementation class MenuPrincipal
 */
@WebServlet("/MenuAnonimo")
public class MenuAnonimo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CocheraLogic LogicCochera;
    private AdministrarTipoCocheraLogic TipoCocheraLogic;
    private Cochera Cochera;
    
    private AdministrarServicioLogic LogicServicio;
    private Servicio Servicio;
	
	private HashMap<String, Accion> Acciones;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuAnonimo() {
		super();
		this.Acciones = new HashMap<String, Accion>();
        this.LogicCochera = CocheraLogic.getInstancia();
        this.TipoCocheraLogic = AdministrarTipoCocheraLogic.getIntancia();
        this.Cochera = new Cochera();
        this.LogicServicio = AdministrarServicioLogic.getIntancia();
        this.Servicio = new Servicio();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LinkedList<Cochera> cocheras = new LinkedList<>();
    	cocheras = this.LogicCochera.getAll();
		request.setAttribute("ListaCocheras", cocheras);
		request.setAttribute("ListaTiposCocheras", this.TipoCocheraLogic.getAll());
		
		LinkedList<Servicio> servicios= new LinkedList<Servicio>();
		
		servicios= this.LogicServicio.getAll();
		request.setAttribute("ListaServicios", servicios);
		request.getRequestDispatcher("MenuAnonimo.jsp").include(request, response);
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
			Log.registrarFineLog(ex);
		}
	}

}