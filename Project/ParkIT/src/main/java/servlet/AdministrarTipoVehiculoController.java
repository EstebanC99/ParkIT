package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Vehiculos.TipoVehiculo;
import logic.Vehiculos.AdministrarTipoVehiculoLogic;

@WebServlet("/AdministrarTipoVehiculo")
public class AdministrarTipoVehiculoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AdministrarTipoVehiculoLogic Logic;
	
    public AdministrarTipoVehiculoController() {
        super();
        
        this.Logic = AdministrarTipoVehiculoLogic.getIntancia();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LinkedList<TipoVehiculo> tiposVehiculo = new LinkedList<>();
		tiposVehiculo = this.Logic.getAll();
		
		request.setAttribute("ListaTiposVehiculo", tiposVehiculo);
		request.getRequestDispatcher("AdministrarTipoVehiculo.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setDescripcion(request.getParameter("Descripcion"));
		
		this.Logic.add(tipoVehiculo);
		
		this.doGet(request, response);
	}

}
