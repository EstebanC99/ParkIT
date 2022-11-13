package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Alquileres.AdministrarAlquilerLogic;
import logic.Personas.ClienteLogic;
import logic.Personas.EmpleadoLogic;
import logic.Servicios.AdministrarServicioLogic;
import logic.Servicios.ServicioVehiculoLogic;
import logic.Vehiculos.VehiculoLogic;
import entities.Alquileres.Alquiler;
import entities.Servicios.ServicioVehiculo;
import entities.Usuarios.Usuario;


@WebServlet("/InformacionCliente")
public class InformacionClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServicioVehiculoLogic ServicioVehiculoLogic;
    private AdministrarAlquilerLogic AdministrarAlquilerLogic;
    private EmpleadoLogic EmpleadoLogicService;
	private AdministrarServicioLogic ServicioLogic;
	private VehiculoLogic VehiculoLogicService;
	private ClienteLogic ClienteLogicService;
    private ServicioVehiculo ServicioVehiculo;
	
    public InformacionClienteController() {
        super();
        this.ServicioVehiculoLogic = ServicioVehiculoLogic.getInstancia();
        this.EmpleadoLogicService = EmpleadoLogic.getInstancia();
        this.AdministrarAlquilerLogic =  AdministrarAlquilerLogic.getInstancia();
        this.ServicioLogic = AdministrarServicioLogic.getIntancia();
        this.VehiculoLogicService = VehiculoLogic.getInstancia();
        this.ClienteLogicService = ClienteLogic.getInstancia();
        this.ServicioVehiculo = new ServicioVehiculo();
    }
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<ServicioVehiculo> serviciosVehiculos = new LinkedList<>();
		LinkedList<Alquiler> alquileres= new LinkedList<>();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		alquileres = this.AdministrarAlquilerLogic.getAlquileresUsuario(usuario.getID());
		request.setAttribute("ListaAlquileres", alquileres);
		serviciosVehiculos = this.ServicioVehiculoLogic.getServiciosUsuario(usuario.getID());
	
		request.setAttribute("ListaServiciosVehiculos", serviciosVehiculos);
		request.setAttribute("ListaVehiculos", this.VehiculoLogicService.getAll());
		request.setAttribute("ListaServicios", this.ServicioLogic.getAll());
		request.setAttribute("ListaEmpleados", this.EmpleadoLogicService.getAll());
		request.setAttribute("ListaClientes", this.ClienteLogicService.getAll());
		request.getRequestDispatcher("InformacionCliente.jsp").include(request, response);
	}
	
} 	