package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnError;

import com.mysql.cj.Session;

import entities.Alquileres.PrecioAlquiler;
import entities.Alquileres.TipoAlquiler;
import entities.Cocheras.TipoCochera;
import exceptions.ValidationException;
import logic.Alquileres.AdministrarTipoAlquilerLogic;
import logic.Alquileres.PrecioAlquilerLogic;
import logic.Cocheras.AdministrarTipoCocheraLogic;

@WebServlet("/AdministrarPrecioAlquiler")
public class AdministrarPrecioAlquilerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrecioAlquilerLogic Logic;
	private AdministrarTipoAlquilerLogic TipoAlquilerLogic;
	private AdministrarTipoCocheraLogic TipoCocheraLogic;
	private PrecioAlquiler PrecioAlquiler;
	
    public AdministrarPrecioAlquilerController() {
        super();
        this.Logic = PrecioAlquilerLogic.getInstancia();
        this.TipoAlquilerLogic = new AdministrarTipoAlquilerLogic();
        this.TipoCocheraLogic = new AdministrarTipoCocheraLogic();
        this.PrecioAlquiler = new PrecioAlquiler();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<PrecioAlquiler> precios = new LinkedList<>();
		precios = this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID");
		
		if (buscarID != null) {
			this.PrecioAlquiler.setID(Integer.parseInt(buscarID));
			request.setAttribute("PrecioSeleccionado", this.Logic.getByID(this.PrecioAlquiler));
		}
		
		request.setAttribute("ListaPrecios", precios);
		request.setAttribute("ListaTiposAlquileres", this.TipoAlquilerLogic.getAll());
		request.setAttribute("ListaTiposCocheras", this.TipoCocheraLogic.getAll());
		request.getRequestDispatcher("AdministrarPrecioAlquiler.jsp").include(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Boolean esEliminar = request.getParameter("Eliminar") != null;
		Boolean esAgregar = request.getParameter("Guardar") != null;
		Boolean esModificar = request.getParameter("Modificar") != null;

		if (esEliminar)
			this.eliminar(request);
		if (esAgregar)
			this.agregar(request);
		if (esModificar)
			this.modificar(request);
		
		this.doGet(request, response);
	}
    
    @OnError
    protected void onError(Session session, Throwable thr) {
    	thr.printStackTrace();
    }

    private void agregar(HttpServletRequest request) throws ServletException {
    	this.PrecioAlquiler.setPrecio(Double.parseDouble(request.getParameter("Precio")));
    	this.PrecioAlquiler.setFechaVigencia(LocalDate.parse(request.getParameter("FechaVigencia")));
    	
    	TipoAlquiler tipoAlquiler = new TipoAlquiler();
    	tipoAlquiler.setID(request.getParameter("TipoAlquilerID"));
    	this.PrecioAlquiler.setTipoAlquiler(this.TipoAlquilerLogic.getByID(tipoAlquiler));
    	
    	TipoCochera tipoCochera = new TipoCochera();
    	tipoCochera.setID(request.getParameter("TipoCocheraID"));
    	this.PrecioAlquiler.setTipoCochera(this.TipoCocheraLogic.getByID(tipoCochera));
    	
    	try {
    		this.Logic.add(this.PrecioAlquiler);
    	}
    	catch(ValidationException ex) {
    		throw new ServletException(ex.getMessage());
    	}
	}
	
	private void modificar(HttpServletRequest request ) {
		this.PrecioAlquiler.setID(request.getParameter("ID"));
		this.PrecioAlquiler.setPrecio(Double.parseDouble(request.getParameter("Precio")));
    	this.PrecioAlquiler.setFechaVigencia(LocalDate.parse(request.getParameter("FechaVigencia")));
    	
    	TipoAlquiler tipoAlquiler = new TipoAlquiler();
    	tipoAlquiler.setID(request.getParameter("TipoAlquilerID"));
    	this.PrecioAlquiler.setTipoAlquiler(this.TipoAlquilerLogic.getByID(tipoAlquiler));
    	
    	TipoCochera tipoCochera = new TipoCochera();
    	tipoCochera.setID(request.getParameter("TipoCocheraID"));
    	this.PrecioAlquiler.setTipoCochera(this.TipoCocheraLogic.getByID(tipoCochera));
    	
		this.Logic.update(this.PrecioAlquiler);
	}
	
	private void eliminar(HttpServletRequest request) {
		this.PrecioAlquiler.setID(request.getParameter("Eliminar"));
		this.Logic.getByID(this.PrecioAlquiler);
		this.Logic.remove(this.PrecioAlquiler);
	}
}
