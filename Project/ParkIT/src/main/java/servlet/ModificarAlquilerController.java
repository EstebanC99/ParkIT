package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alquileres.Alquiler;
import entities.Alquileres.FormaPago;
import entities.Alquileres.TipoAlquiler;
import entities.Cocheras.Cochera;
import entities.Personas.Empleado;
import entities.Vehiculos.Vehiculo;
import exceptions.ValidationException;
import logic.Alquileres.AdministrarAlquilerLogic;
import logic.Alquileres.AdministrarTipoAlquilerLogic;
import logic.Alquileres.FormaPagoLogic;
import logic.Cocheras.CocheraLogic;
import logic.Personas.EmpleadoLogic;
import logic.Vehiculos.VehiculoLogic;

@WebServlet("/ModificarAlquiler")
public class ModificarAlquilerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdministrarAlquilerLogic Logic;   
	private CocheraLogic CocheraLogicService;
	private AdministrarTipoAlquilerLogic TipoAlquilerLogic;
	private VehiculoLogic VehiculoLogicService;
	private EmpleadoLogic EmpleadoLogicService;
	private FormaPagoLogic FormaPagoLogicService;
	private Alquiler Alquiler;
	
    public ModificarAlquilerController() {
        super();
        this.Logic = AdministrarAlquilerLogic.getInstancia();
        this.CocheraLogicService = CocheraLogic.getInstancia();
        this.TipoAlquilerLogic = AdministrarTipoAlquilerLogic.getIntancia();
        this.VehiculoLogicService = VehiculoLogic.getInstancia();
        this.EmpleadoLogicService = EmpleadoLogic.getInstancia();
        this.FormaPagoLogicService = FormaPagoLogic.getInstancia();
        this.Alquiler = new Alquiler();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.Alquiler.setID(request.getParameter("ModificarID"));
		this.Logic.getByID(this.Alquiler);
		
		request.setAttribute("AlquilerSeleccionado", this.Alquiler);
		request.setAttribute("ListaTiposAlquileres", this.TipoAlquilerLogic.getAll());
    	request.setAttribute("ListaEmpleados", this.EmpleadoLogicService.getAll());
		request.setAttribute("ListaVehiculos", this.VehiculoLogicService.getAll());
		request.setAttribute("ListaCocheras", this.CocheraLogicService.getAll());
		request.setAttribute("ListaFormasPago", this.FormaPagoLogicService.getAll());
		
		request.setAttribute("TipoAlquilerSeleccionadoID", this.Alquiler.getTipoAlquiler().getID());
		request.setAttribute("EmpleadoSeleccionadoID", this.Alquiler.getEmpleado().getID());
		request.setAttribute("VehiculoSeleccionadoID", this.Alquiler.getVehiculo().getID());
		request.setAttribute("CocheraSeleccionadaID", this.Alquiler.getCochera().getID());
		request.setAttribute("FormaPagoSeleccionadaID", this.Alquiler.getFormaPago().getID());
		
		request.getRequestDispatcher("ModificarAlquiler.jsp").include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean esGuardar = request.getParameter("Guardar") != null;
		Boolean esCancelar = request.getParameter("Cancelar") != null;

		try {
			if (esGuardar) {
				this.guardar(request);
				response.sendRedirect("AdministrarAlquiler");
			}
			if (esCancelar)
				response.sendRedirect("AdministrarAlquiler");
				
			request.setAttribute("ErrorMessage", "");
		}
		catch (ValidationException ex) {
			request.setAttribute("ErrorMessage", ex.getMessage());
			doGet(request, response);
		}
		
		if (!esCancelar)
			doGet(request, response);
	}

	private void guardar(HttpServletRequest request) throws ValidationException {
		Empleado empleado = new Empleado();
		empleado.setID(request.getParameter("EmpleadoID"));
    	this.Alquiler.setEmpleado(this.EmpleadoLogicService.getByID(empleado));
    	
    	TipoAlquiler tipoAlquiler = new TipoAlquiler();
    	tipoAlquiler.setID(request.getParameter("TipoAlquilerID"));
    	this.Alquiler.setTipoAlquiler(this.TipoAlquilerLogic.getByID(tipoAlquiler));
    	
    	Vehiculo vehiculo = new Vehiculo();
    	vehiculo.setID(request.getParameter("VehiculoID"));
    	this.Alquiler.setVehiculo(this.VehiculoLogicService.getByID(vehiculo));
    	
    	Cochera cochera = new Cochera();
    	cochera.setID(request.getParameter("CocheraID"));
    	this.Alquiler.setCochera(this.CocheraLogicService.getByID(cochera));
    	
    	FormaPago formaPago = new FormaPago();
    	formaPago.setID(request.getParameter("FormaPagoID"));
    	this.Alquiler.setFormaPago(this.FormaPagoLogicService.getByID(formaPago));
    	
    	this.Alquiler.setFechaInicio(LocalDate.parse(request.getParameter("FechaInicio")));
    	this.Alquiler.setHoraInicio(LocalTime.parse(request.getParameter("HoraInicio")));
    	this.Alquiler.setTiempoEstadia(request.getParameter("TiempoEstadia"));

		this.Logic.update(this.Alquiler);
	}

}
