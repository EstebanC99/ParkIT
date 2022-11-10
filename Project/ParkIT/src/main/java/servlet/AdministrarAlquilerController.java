package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Filtros.FiltroAlquileres;
import entities.Alquileres.Alquiler;
import exceptions.ValidationException;
import logic.Alquileres.AdministrarAlquilerLogic;
import logic.Alquileres.AdministrarTipoAlquilerLogic;
import logic.Alquileres.FormaPagoLogic;

@WebServlet("/AdministrarAlquiler")
public class AdministrarAlquilerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdministrarAlquilerLogic Logic;
	private FormaPagoLogic FormaPagoLogicService;
	private AdministrarTipoAlquilerLogic TipoAlquilerLogic;
	private FiltroAlquileres Filtro;
       
    public AdministrarAlquilerController() {
        super();
        this.Logic = AdministrarAlquilerLogic.getInstancia();
        this.FormaPagoLogicService = FormaPagoLogic.getInstancia();
        this.TipoAlquilerLogic = AdministrarTipoAlquilerLogic.getIntancia();
        this.Filtro = new FiltroAlquileres();
        
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Alquiler> alquileres = new LinkedList<>();
		alquileres = this.Logic.searchByFilter(this.Filtro);
		
		request.setAttribute("ListaAlquileres", alquileres);
		request.setAttribute("ListaFormasPago", this.FormaPagoLogicService.getAll());
		request.setAttribute("ListaTiposAlquileres", this.TipoAlquilerLogic.getAll());
		request.getRequestDispatcher("AdministrarAlquiler.jsp").include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		Boolean esBuscar = request.getParameter("Buscar") != null;
		Boolean esAgregar = request.getParameter("Agregar") != null;
		Boolean esModificar = request.getParameter("ModificarID") != null;
		Boolean esEliminar = request.getParameter("EliminarID") != null;
		Boolean esCancelar = request.getParameter("Cancelar") != null;

		try {
			if (esBuscar)
				this.buscar(request);
			if (esAgregar)
				this.agregar(response);
			if (esModificar)
				this.modificar(request);
			if (esEliminar)
				this.eliminar(request);
			if (esCancelar)
				this.Filtro.limpiar();
			
			request.setAttribute("ErrorMessage", "");
		}
		catch (ValidationException ex) {
			request.setAttribute("ErrorMessage", ex.getMessage());
		}
				
		doGet(request, response);
	}

	private void agregar(HttpServletResponse response) throws IOException {
		response.sendRedirect("AgregarAlquiler");
	}

	private void eliminar(HttpServletRequest request) throws ValidationException {
		Alquiler alquiler = new Alquiler();
		alquiler.setID(request.getParameter("EliminarID"));
		this.Logic.remove(alquiler);
	}

	private void modificar(HttpServletRequest request) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	private void buscar(HttpServletRequest request) throws ValidationException {
		this.Filtro.setFechaInicio(request.getParameter("FechaInicio"));
		this.Filtro.setFechaFin(request.getParameter("FechaFin"));
		this.Filtro.setEstadoAlquiler(request.getParameter("EstadoAlquiler"));
		this.Filtro.setFormaPagoID(request.getParameter("FormaPagoID"));
		this.Filtro.setTipoAlquilerID(request.getParameter("TipoAlquilerID"));
	}

}
