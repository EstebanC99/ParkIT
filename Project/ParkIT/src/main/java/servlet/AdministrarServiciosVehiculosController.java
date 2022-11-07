package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Personas.EmpleadoLogic;
import logic.Servicios.AdministrarServicioLogic;
import logic.Servicios.ServicioVehiculoLogic;
import logic.Vehiculos.VehiculoLogic;
import entities.Personas.Empleado;
import entities.Servicios.Servicio;
import entities.Servicios.ServicioVehiculo;
import entities.Vehiculos.Vehiculo;
import exceptions.ValidationException;

@WebServlet("/AdministrarServiciosVehiculos")
public class AdministrarServiciosVehiculosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServicioVehiculoLogic Logic;
    private EmpleadoLogic EmpleadoLogicService;
	private AdministrarServicioLogic ServicioLogic;
	private VehiculoLogic VehiculoLogicService;
    private ServicioVehiculo ServicioVehiculo;
	
    public AdministrarServiciosVehiculosController() {
        super();
        this.Logic = ServicioVehiculoLogic.getInstancia();
        this.EmpleadoLogicService = EmpleadoLogic.getInstancia();
        this.ServicioLogic = AdministrarServicioLogic.getIntancia();
        this.VehiculoLogicService = VehiculoLogic.getInstancia();
        this.ServicioVehiculo = new ServicioVehiculo();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<ServicioVehiculo> serviciosVehiculos = new LinkedList<>();
		serviciosVehiculos = this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID");
		
		if (buscarID != null) {
			this.ServicioVehiculo.setID(Integer.parseInt(buscarID));
			request.setAttribute("ServicioVehiculoSeleccionado", this.Logic.getByID(this.ServicioVehiculo));
		}
		
		request.setAttribute("ListaServiciosVehiculos", serviciosVehiculos);
		request.setAttribute("ListaVehiculos", this.VehiculoLogicService.getAll());
		request.setAttribute("ListaServicios", this.ServicioLogic.getAll());
		request.setAttribute("ListaEmpleados", this.EmpleadoLogicService.getAll());
		request.getRequestDispatcher("AdministrarServiciosVehiculos.jsp").include(request, response);
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

	private void agregar(HttpServletRequest request) throws ServletException {
		this.ServicioVehiculo.setFechaRealizacion(LocalDate.parse(request.getParameter("FechaRealizacion")));
    	
    	Empleado empleado = new Empleado();
    	empleado.setID(request.getParameter("EmpleadoID"));
    	this.ServicioVehiculo.setEmpleado(this.EmpleadoLogicService.getByID(empleado));

    	Servicio  servicio = new Servicio();
    	servicio.setID(request.getParameter("ServicioID"));
    	this.ServicioVehiculo.setServicio(this.ServicioLogic.getByID(servicio));

    	Vehiculo  vehiculo= new Vehiculo();
    	vehiculo.setID(request.getParameter("VehiculoID"));
    	this.ServicioVehiculo.setVehiculo(this.VehiculoLogicService.getByID(vehiculo));
    	
    	try {
    		this.Logic.add(this.ServicioVehiculo);
    	}
    	catch(ValidationException ex) {
    		throw new ServletException(ex.getMessage());
    	}
	}
	
	private void modificar(HttpServletRequest request ) {
		this.ServicioVehiculo.setID(request.getParameter("ID"));
		this.ServicioVehiculo.setFechaRealizacion(LocalDate.parse(request.getParameter("FechaRealizacion")));
    	
    	Empleado empleado = new Empleado();
    	empleado.setID(request.getParameter("EmpleadoID"));
    	this.ServicioVehiculo.setEmpleado(this.EmpleadoLogicService.getByID(empleado));

    	Servicio  servicio = new Servicio();
    	servicio.setID(request.getParameter("ServicioID"));
    	this.ServicioVehiculo.setServicio(this.ServicioLogic.getByID(servicio));

    	Vehiculo  vehiculo= new Vehiculo();
    	vehiculo.setID(request.getParameter("VehiculoID"));
    	this.ServicioVehiculo.setVehiculo(this.VehiculoLogicService.getByID(vehiculo));
    	
		this.Logic.update(this.ServicioVehiculo);
	}
	
	private void eliminar(HttpServletRequest request) {
		this.ServicioVehiculo.setID(request.getParameter("Eliminar"));
		this.Logic.getByID(this.ServicioVehiculo);
		this.Logic.remove(this.ServicioVehiculo);
	}
}
