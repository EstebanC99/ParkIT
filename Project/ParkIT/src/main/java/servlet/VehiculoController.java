package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Personas.ClienteLogic;
import logic.Vehiculos.AdministrarTipoVehiculoLogic;
import logic.Vehiculos.VehiculoLogic;

import entities.Personas.Cliente;
import entities.Vehiculos.TipoVehiculo;
import entities.Vehiculos.Vehiculo;
import exceptions.ValidationException;

@WebServlet("/Vehiculo")
public class VehiculoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VehiculoLogic Logic;
    private ClienteLogic ClienteLogicService;
    private AdministrarTipoVehiculoLogic TipoVehiculoLogic;
    private Vehiculo Vehiculo;
	
    public VehiculoController() {
        super();
        this.Logic = VehiculoLogic.getInstancia();
        this.ClienteLogicService = ClienteLogic.getInstancia();
        this.TipoVehiculoLogic = AdministrarTipoVehiculoLogic.getIntancia();
        this.Vehiculo = new Vehiculo();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LinkedList<Vehiculo> vehiculos = new LinkedList<>();
    	vehiculos = this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID");
		
		if (buscarID != null) {
			this.Vehiculo.setID(Integer.parseInt(buscarID));
			request.setAttribute("VehiculoSeleccionado", this.Logic.getByID(this.Vehiculo));
		}

		response.flushBuffer();
		request.setAttribute("ListaVehiculos", vehiculos);
		request.setAttribute("ListaTiposVehiculos", this.TipoVehiculoLogic.getAll());
		request.setAttribute("ListaClientes", this.ClienteLogicService.getAll());
		request.getRequestDispatcher("AdministrarVehiculos.jsp").include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("ErrorMessage", "");
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
		}
		
		this.doGet(request, response);
	}
	
	private void agregar(HttpServletRequest request) throws ValidationException {
		this.Vehiculo.setPatente(request.getParameter("Patente"));
		this.Vehiculo.setMarca(request.getParameter("Marca"));
		this.Vehiculo.setModelo(request.getParameter("Modelo"));
		
		Cliente cliente = new Cliente();
		cliente.setID(request.getParameter("ClienteID"));
    	this.Vehiculo.setCliente(this.ClienteLogicService.getByID(cliente));
		
    	TipoVehiculo tipoVehiculo = new TipoVehiculo();
    	tipoVehiculo.setID(request.getParameter("TipoVehiculoID"));
    	this.Vehiculo.setTipoVehiculo(this.TipoVehiculoLogic.getByID(tipoVehiculo));

		this.Logic.add(this.Vehiculo);
	}
	
	private void modificar(HttpServletRequest request ) throws ValidationException {
		this.Vehiculo.setID(request.getParameter("ID"));
		this.Vehiculo.setPatente(request.getParameter("Patente"));
		this.Vehiculo.setMarca(request.getParameter("Marca"));
		this.Vehiculo.setModelo(request.getParameter("Modelo"));
		
		Cliente cliente = new Cliente();
		cliente.setID(request.getParameter("ClienteID"));
    	this.Vehiculo.setCliente(this.ClienteLogicService.getByID(cliente));
		
    	TipoVehiculo tipoVehiculo = new TipoVehiculo();
    	tipoVehiculo.setID(request.getParameter("TipoVehiculoID"));
    	this.Vehiculo.setTipoVehiculo(this.TipoVehiculoLogic.getByID(tipoVehiculo));
    	
		this.Logic.update(this.Vehiculo);
	}
	
	private void eliminar(HttpServletRequest request) throws ValidationException {
		this.Vehiculo.setID(request.getParameter("Eliminar"));
		this.Logic.getByID(this.Vehiculo);
		this.Logic.remove(this.Vehiculo);
	}
}
