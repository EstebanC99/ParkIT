package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Vehiculos.TipoVehiculo;
import exceptions.ValidationException;
import logic.Vehiculos.AdministrarTipoVehiculoLogic;
import logs.Log;

@WebServlet("/AdministrarTipoVehiculo")
public class AdministrarTipoVehiculoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AdministrarTipoVehiculoLogic Logic;
	
	private TipoVehiculo TipoVehiculo;
	
    public AdministrarTipoVehiculoController() {
        super();
        this.Logic = AdministrarTipoVehiculoLogic.getIntancia();
        this.TipoVehiculo = new TipoVehiculo();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LinkedList<TipoVehiculo> tiposVehiculo = new LinkedList<TipoVehiculo>();
		tiposVehiculo = this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID"); 
		
		if (buscarID != null) {
			this.TipoVehiculo.setID(Integer.parseInt(buscarID));
			request.setAttribute("VehiculoSeleccionado", this.Logic.getByID(this.TipoVehiculo));
		}
		
		request.setAttribute("ListaTiposVehiculo", tiposVehiculo);
		request.getRequestDispatcher("AdministrarTipoVehiculo.jsp").include(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Boolean esEliminar = request.getParameter("Eliminar") != null;
		Boolean esAgregar = request.getParameter("Guardar") != null;
		Boolean esModificar = request.getParameter("Modificar") != null;

		try {
			if (esEliminar)
				this.eliminar(request);
			if (esAgregar)
				this.agregar(request);
			if (esModificar)
				this.modificar(request);
		}
		catch (ValidationException ex) {
			request.setAttribute("ErrorMessage", ex.getMessage());
			Log.registrarFineLog(ex);
		}
		
		this.doGet(request, response);
	}
	
	private void agregar(HttpServletRequest request) throws ValidationException {
		this.TipoVehiculo.setDescripcion(request.getParameter("Descripcion"));
		this.Logic.add(this.TipoVehiculo);
		this.TipoVehiculo = new TipoVehiculo();
	}
	
	private void modificar(HttpServletRequest request ) throws ValidationException {
		this.TipoVehiculo.setID(Integer.parseInt(request.getParameter("ID")));
		this.TipoVehiculo.setDescripcion(request.getParameter("Descripcion"));
		this.Logic.save(this.TipoVehiculo);
	}
	
	private void eliminar(HttpServletRequest request) throws ValidationException {
		this.TipoVehiculo.setID(Integer.parseInt(request.getParameter("Eliminar")));
		this.Logic.getByID(this.TipoVehiculo);
		this.Logic.remove(this.TipoVehiculo);
	}
	
}
