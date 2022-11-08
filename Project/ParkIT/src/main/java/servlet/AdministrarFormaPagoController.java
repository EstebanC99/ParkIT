package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alquileres.FormaPago;
import exceptions.ValidationException;
import logic.Alquileres.FormaPagoLogic;

/**
 * Servlet implementation class AdministrarFormaPago
 */
@WebServlet("/AdministrarFormaPago")
public class AdministrarFormaPagoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FormaPagoLogic Logic;
	
	private FormaPago FormaPago;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrarFormaPagoController() {
        super();
        this.Logic = FormaPagoLogic.getInstancia();
        this.FormaPago = new FormaPago();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LinkedList<FormaPago> formasPago = new LinkedList<FormaPago>();
		
		formasPago=this.Logic.getAll();
		
		String buscarID = request.getParameter("BuscarID"); 
		
		if (buscarID != null) {
			this.FormaPago.setID(Integer.parseInt(buscarID));
			request.setAttribute("FormaPagoSeleccionada", this.Logic.getByID(this.FormaPago));
		}
		
		request.setAttribute("ListaFormasPago", formasPago);
		request.getRequestDispatcher("AdministrarFormaPago.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	
	private void agregar(HttpServletRequest request) throws ServletException{
		this.FormaPago.setDescripcion(request.getParameter("Descripcion"));
		this.FormaPago.setDescuento(Float.parseFloat(request.getParameter("Descuento")));
		this.FormaPago.setIncremento(Float.parseFloat(request.getParameter("Incremento")));
		
		try {
    		this.Logic.add(this.FormaPago);
    	}
    	catch(ValidationException ex) {
    		throw new ServletException(ex.getMessage());
    	}
	}
	
	private void modificar(HttpServletRequest request ) {
		this.FormaPago.setID(Integer.parseInt(request.getParameter("ID")));
		this.FormaPago.setDescripcion(request.getParameter("Descripcion"));
		this.FormaPago.setDescuento(Float.parseFloat(request.getParameter("Descuento")));
		this.FormaPago.setIncremento(Float.parseFloat(request.getParameter("Incremento")));
		this.Logic.update(FormaPago);
	}
	
	private void eliminar(HttpServletRequest request) {
		this.FormaPago.setID(Integer.parseInt(request.getParameter("Eliminar")));
		this.Logic.getByID(this.FormaPago);
		this.Logic.remove(this.FormaPago);
	}

}