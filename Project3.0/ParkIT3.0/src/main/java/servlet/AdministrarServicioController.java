package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Servicios.Servicio;
import exceptions.ValidationException;
import logic.Servicios.AdministrarServicioLogic;
import logs.Log;

@WebServlet("/AdministrarServicio")
public class AdministrarServicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdministrarServicioLogic Logic;
	
	private Servicio Servicio;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrarServicioController() {
        super();
        this.Logic = AdministrarServicioLogic.getIntancia();
        this.Servicio = new Servicio();

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LinkedList<Servicio> servicios= new LinkedList<Servicio>();
		
		servicios= this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID"); 
		
		if (buscarID != null) {
			this.Servicio.setID(Integer.parseInt(buscarID));
			request.setAttribute("ServicioSeleccionado", this.Logic.getByID(this.Servicio));
		}
		
		request.setAttribute("ListaServicios", servicios);
		request.getRequestDispatcher("AdministrarServicio.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		this.Servicio.setDescripcion(request.getParameter("Descripcion"));
		this.Servicio.setPrecio(Float.parseFloat(request.getParameter("Precio")));
		this.Logic.add(this.Servicio);
	}
	
	private void modificar(HttpServletRequest request ) throws ValidationException {
		this.Servicio.setID(Integer.parseInt(request.getParameter("ID")));
		this.Servicio.setDescripcion(request.getParameter("Descripcion"));
		this.Servicio.setPrecio(Float.parseFloat(request.getParameter("Precio")));
		this.Logic.save(this.Servicio);
	}
	
	private void eliminar(HttpServletRequest request) throws ValidationException {
		this.Servicio.setID(Integer.parseInt(request.getParameter("Eliminar")));
		this.Logic.getByID(this.Servicio);
		this.Logic.remove(this.Servicio);
	}
	
}
