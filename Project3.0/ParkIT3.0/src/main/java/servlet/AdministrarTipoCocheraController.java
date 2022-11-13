package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cocheras.TipoCochera;
import exceptions.ValidationException;
import logic.Cocheras.AdministrarTipoCocheraLogic;
import logs.Log;


@WebServlet("/AdministrarTipoCochera")
public class AdministrarTipoCocheraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdministrarTipoCocheraLogic Logic;
    
	private TipoCochera TipoCochera;

    public AdministrarTipoCocheraController() {
        super();
        this.Logic = AdministrarTipoCocheraLogic.getIntancia();
        this.TipoCochera = new TipoCochera();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LinkedList<TipoCochera> tiposCochera = new LinkedList<>();
    	tiposCochera = this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID"); 
		
		if (buscarID != null) {
			this.TipoCochera.setID(Integer.parseInt(buscarID));
			request.setAttribute("CocheraSeleccionada", this.Logic.getByID(this.TipoCochera));
		}
		
		request.setAttribute("ListaTiposCochera", tiposCochera);
		request.getRequestDispatcher("AdministrarTipoCochera.jsp").include(request, response);
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
		this.TipoCochera.setDescripcion(request.getParameter("Descripcion"));
		this.Logic.add(this.TipoCochera);
		this.TipoCochera = new TipoCochera();
	}
	
	private void modificar(HttpServletRequest request ) throws ValidationException {
		this.TipoCochera.setID(Integer.parseInt(request.getParameter("ID")));
		this.TipoCochera.setDescripcion(request.getParameter("Descripcion"));
		this.Logic.save(this.TipoCochera);
	}
	
	private void eliminar(HttpServletRequest request) throws ValidationException {
		this.TipoCochera.setID(Integer.parseInt(request.getParameter("Eliminar")));
		this.Logic.getByID(this.TipoCochera);
		this.Logic.remove(this.TipoCochera);
	}

}
