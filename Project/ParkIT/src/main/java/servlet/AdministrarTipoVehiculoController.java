package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Vehiculos.TipoVehiculo;
import logic.Vehiculos.AdministrarTipoVehiculoLogic;

/**
 * Servlet implementation class AdministrarTipoVehiculo
 */
@WebServlet("/AdministrarTipoVehiculo")
public class AdministrarTipoVehiculoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AdministrarTipoVehiculoLogic Logic;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrarTipoVehiculoController() {
        super();
        // TODO Auto-generated constructor stub
        
        this.Logic = AdministrarTipoVehiculoLogic.getIntancia();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setDescripcion(request.getParameter("Descripcion"));
		
		this.Logic.add(tipoVehiculo);
		
	}

}
