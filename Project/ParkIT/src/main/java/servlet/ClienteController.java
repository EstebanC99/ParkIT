package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Personas.Cliente;
import exceptions.ValidationException;
import logic.Personas.ClienteLogic;

@WebServlet("/Cliente")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClienteLogic Logic;
    private Cliente Cliente;
	
    public ClienteController() {
        super();
        this.Logic = ClienteLogic.getInstancia();
        this.Cliente = new Cliente();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Cliente> clientes = new LinkedList<>();
		clientes = this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID");
		
		if (buscarID != null) {
			this.Cliente.setID(Integer.parseInt(buscarID));
			request.setAttribute("ClienteSeleccionado", this.Logic.getByID(this.Cliente));
		}
		
		request.setAttribute("ListaClientes", clientes);
		request.getRequestDispatcher("Clientes.jsp").include(request, response);
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
		}
		
		this.doGet(request, response);
	}

	private void agregar(HttpServletRequest request) throws ValidationException {
		this.Cliente.setNombre(request.getParameter("Nombre"));
		this.Cliente.setApellido(request.getParameter("Apellido"));
		this.Cliente.setDNI(request.getParameter("DNI"));
		this.Cliente.setEmail(request.getParameter("Email"));
		this.Cliente.setTelefono(request.getParameter("Telefono"));
		this.Cliente.setDireccion(request.getParameter("Direccion"));

		this.Logic.add(this.Cliente);
	}
	
	private void modificar(HttpServletRequest request ) throws ValidationException {
		this.Cliente.setID(request.getParameter("ID"));
		this.Cliente.setNombre(request.getParameter("Nombre"));
		this.Cliente.setApellido(request.getParameter("Apellido"));
		this.Cliente.setDNI(request.getParameter("DNI"));
		this.Cliente.setEmail(request.getParameter("Email"));
		this.Cliente.setTelefono(request.getParameter("Telefono"));
		this.Cliente.setDireccion(request.getParameter("Direccion"));
    	
		this.Logic.update(this.Cliente);
	}
	
	private void eliminar(HttpServletRequest request) throws ValidationException {
		this.Cliente.setID(request.getParameter("Eliminar"));
		this.Logic.getByID(this.Cliente);
		this.Logic.remove(this.Cliente);
	}
}
