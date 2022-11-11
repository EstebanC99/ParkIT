package servlet;

import entities.Alquileres.TipoAlquiler;
import exceptions.ValidationException;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Alquileres.AdministrarTipoAlquilerLogic;
import logs.Log;

@WebServlet("/AdministrarTipoAlquiler")
public class AdministrarTipoAlquilerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AdministrarTipoAlquilerLogic Logic;
	
	private TipoAlquiler TipoAlquiler;
	
    public AdministrarTipoAlquilerController() {
        super();
        this.Logic = AdministrarTipoAlquilerLogic.getIntancia();
        this.TipoAlquiler = new TipoAlquiler();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LinkedList<TipoAlquiler> tiposAlquiler = new LinkedList<>();
    	tiposAlquiler = this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID"); 
		
		if (buscarID != null) {
			this.TipoAlquiler.setID(Integer.parseInt(buscarID));
			request.setAttribute("TipoAlquilerSeleccionado", this.Logic.getByID(this.TipoAlquiler));
		}
		
		request.setAttribute("ListaTiposAlquileres", tiposAlquiler);
		request.getRequestDispatcher("AdministrarTipoAlquiler.jsp").include(request, response);
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
		this.TipoAlquiler.setDescripcion(request.getParameter("Descripcion"));
		this.Logic.add(this.TipoAlquiler);
	}
	
	private void modificar(HttpServletRequest request ) throws ValidationException {
		this.TipoAlquiler.setID(Integer.parseInt(request.getParameter("ID")));
		this.TipoAlquiler.setDescripcion(request.getParameter("Descripcion"));
		this.Logic.save(this.TipoAlquiler);
	}
	
	private void eliminar(HttpServletRequest request) throws ValidationException {
		this.TipoAlquiler.setID(Integer.parseInt(request.getParameter("Eliminar")));
		this.Logic.getByID(this.TipoAlquiler);
		this.Logic.remove(this.TipoAlquiler);
	}
}
