package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnError;

import com.mysql.cj.Session;

import logic.Cocheras.AdministrarTipoCocheraLogic;
import logic.Cocheras.CocheraLogic;
import entities.Cocheras.Cochera;
import entities.Cocheras.TipoCochera;
import exceptions.ValidationException;

@WebServlet("/Cochera")
public class CocheraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CocheraLogic Logic;
    private AdministrarTipoCocheraLogic TipoCocheraLogic;
    private Cochera Cochera;
	
    public CocheraController() {
        super();
        this.Logic = CocheraLogic.getInstancia();
        this.TipoCocheraLogic = AdministrarTipoCocheraLogic.getIntancia();
        this.Cochera = new Cochera();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LinkedList<Cochera> cocheras = new LinkedList<>();
    	cocheras = this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID");
		
		if (buscarID != null) {
			this.Cochera.setID(Integer.parseInt(buscarID));
			request.setAttribute("CocheraSeleccionada", this.Logic.getByID(this.Cochera));
		}
		
		request.setAttribute("ListaCocheras", cocheras);
		request.setAttribute("ListaTiposCocheras", this.TipoCocheraLogic.getAll());
		request.getRequestDispatcher("AdministrarCocheras.jsp").include(request, response);
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
    	this.Cochera.setNroCochera(Integer.parseInt(request.getParameter("NroCochera")));
    	this.Cochera.setDisponible(Boolean.parseBoolean(request.getParameter("Disponible")));
    	this.Cochera.setUbicacion(request.getParameter("Ubicacion"));
    	
    	TipoCochera tipoCochera = new TipoCochera();
    	tipoCochera.setID(request.getParameter("TipoCocheraID"));
    	this.Cochera.setTipoCochera(this.TipoCocheraLogic.getByID(tipoCochera));
    	
    	try {
    		this.Logic.add(this.Cochera);
    	}
    	catch(ValidationException ex) {
    		throw new ServletException(ex.getMessage());
    	}
	}
	
	private void modificar(HttpServletRequest request ) {
		this.Cochera.setID(request.getParameter("ID"));
		this.Cochera.setNroCochera(Integer.parseInt(request.getParameter("NroCochera")));
    	this.Cochera.setDisponible(Boolean.parseBoolean(request.getParameter("Disponible")));
    	this.Cochera.setUbicacion(request.getParameter("Ubicacion"));
    	
    	TipoCochera tipoCochera = new TipoCochera();
    	tipoCochera.setID(request.getParameter("TipoCocheraID"));
    	this.Cochera.setTipoCochera(this.TipoCocheraLogic.getByID(tipoCochera));
    	
		this.Logic.update(this.Cochera);
	}
	
	private void eliminar(HttpServletRequest request) {
		this.Cochera.setID(request.getParameter("Eliminar"));
		this.Logic.getByID(this.Cochera);
		this.Logic.remove(this.Cochera);
	}
}