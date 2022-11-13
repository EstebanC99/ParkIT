package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alquileres.ListaEspera;
import entities.Cocheras.TipoCochera;
import entities.Personas.Cliente;
import exceptions.ValidationException;
import logic.Alquileres.ListaEsperaLogic;
import logic.Cocheras.AdministrarTipoCocheraLogic;
import logic.Personas.ClienteLogic;
import logs.Log;

@WebServlet("/ListaEspera")
public class ListaEsperaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ListaEsperaLogic Logic;
    private ClienteLogic ClienteLogicService;
    private AdministrarTipoCocheraLogic TipoCocheraLogic;
    private ListaEspera ListaEspera;
	
    public ListaEsperaController() {
        super();
        this.Logic = ListaEsperaLogic.getInstancia();
        this.ClienteLogicService = ClienteLogic.getInstancia();
        this.TipoCocheraLogic = AdministrarTipoCocheraLogic.getIntancia();
        this.ListaEspera = new ListaEspera();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<ListaEspera> listasEspera = new LinkedList<ListaEspera>();
		listasEspera = this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID");
		
		if (buscarID != null) {
			this.ListaEspera.setID(Integer.parseInt(buscarID));
			request.setAttribute("ListaEsperaSeleccionada", this.Logic.getByID(this.ListaEspera));
		}
		
		request.setAttribute("ListasEspera", listasEspera);
		request.setAttribute("ListaClientes", this.ClienteLogicService.getAll());
		request.setAttribute("ListaTiposCocheras", this.TipoCocheraLogic.getAll());
		request.getRequestDispatcher("AdministrarListaEspera.jsp").include(request, response);
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

    	Cliente cliente= new Cliente();
    	cliente.setID(request.getParameter("ClienteID"));
    	this.ListaEspera.setCliente(this.ClienteLogicService.getByID(cliente));

    	TipoCochera  tipoCochera= new TipoCochera();
    	tipoCochera.setID(request.getParameter("TipoCocheraID"));
    	this.ListaEspera.setTipoCochera(this.TipoCocheraLogic.getByID(tipoCochera));
    	
		this.Logic.add(this.ListaEspera);
	}
	
	private void modificar(HttpServletRequest request ) throws ValidationException {
		this.ListaEspera.setID(request.getParameter("ID"));
    	
		Cliente cliente= new Cliente();
    	cliente.setID(request.getParameter("ClienteID"));
    	this.ListaEspera.setCliente(this.ClienteLogicService.getByID(cliente));

    	TipoCochera  tipoCochera= new TipoCochera();
    	tipoCochera.setID(request.getParameter("TipoCocheraID"));
    	this.ListaEspera.setTipoCochera(this.TipoCocheraLogic.getByID(tipoCochera));
    	
		this.Logic.update(this.ListaEspera);
	}
	
	private void eliminar(HttpServletRequest request) throws ValidationException {
		this.ListaEspera.setID(request.getParameter("Eliminar"));
		this.Logic.getByID(this.ListaEspera);
		this.Logic.remove(this.ListaEspera);
	}
}
