package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Personas.Empleado;
import exceptions.ValidationException;
import logic.Personas.EmpleadoLogic;
import logs.Log;

@WebServlet("/Empleado")
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmpleadoLogic Logic;
    private Empleado Empleado;
	
    public EmpleadoController() {
        super();
        this.Logic = EmpleadoLogic.getInstancia();
        this.Empleado = new Empleado();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Empleado> empleados = new LinkedList<>();
		empleados = this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID");
		
		if (buscarID != null) {
			this.Empleado.setID(Integer.parseInt(buscarID));
			request.setAttribute("EmpleadoSeleccionado", this.Logic.getByID(this.Empleado));
		}
		
		request.setAttribute("ListaEmpleados", empleados);
		request.getRequestDispatcher("Empleados.jsp").include(request, response);
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
		this.Empleado.setNombre(request.getParameter("Nombre"));
		this.Empleado.setApellido(request.getParameter("Apellido"));
		this.Empleado.setDNI(request.getParameter("DNI"));
		this.Empleado.setEmail(request.getParameter("Email"));
		this.Empleado.setTelefono(request.getParameter("Telefono"));
		this.Empleado.setDireccion(request.getParameter("Direccion"));
		this.Empleado.setFechaNacimiento(LocalDate.parse(request.getParameter("FechaNacimiento")));
		this.Empleado.setCuit(request.getParameter("Cuit"));
    	
		this.Logic.add(this.Empleado);
	}
	
	private void modificar(HttpServletRequest request ) throws ValidationException {
		this.Empleado.setID(request.getParameter("ID"));
		this.Empleado.setNombre(request.getParameter("Nombre"));
		this.Empleado.setApellido(request.getParameter("Apellido"));
		this.Empleado.setDNI(request.getParameter("DNI"));
		this.Empleado.setEmail(request.getParameter("Email"));
		this.Empleado.setTelefono(request.getParameter("Telefono"));
		this.Empleado.setDireccion(request.getParameter("Direccion"));
		this.Empleado.setFechaNacimiento(LocalDate.parse(request.getParameter("FechaNacimiento")));
		this.Empleado.setCuit(request.getParameter("Cuit"));
    	
		this.Logic.update(this.Empleado);
	}
	
	private void eliminar(HttpServletRequest request) throws ValidationException {
		this.Empleado.setID(request.getParameter("Eliminar"));
		this.Logic.getByID(this.Empleado);
		this.Logic.remove(this.Empleado);
	}
}
