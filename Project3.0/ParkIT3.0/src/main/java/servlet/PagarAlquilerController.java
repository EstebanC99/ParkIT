package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alquileres.Alquiler;
import exceptions.ValidationException;
import logic.Alquileres.AdministrarAlquilerLogic;

@WebServlet("/PagarAlquiler")
public class PagarAlquilerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdministrarAlquilerLogic Logic;
	private Alquiler Alquiler;
	
    public PagarAlquilerController() {
        super();
        this.Logic = AdministrarAlquilerLogic.getInstancia();
        this.Alquiler = new Alquiler();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (this.Alquiler.getID() == 0)
			this.Alquiler.setID(request.getParameter("PagarID"));
		
		this.Logic.getByID(this.Alquiler);
		
		request.setAttribute("AlquilerSeleccionado", this.Alquiler);
		request.getRequestDispatcher("PagarAlquiler.jsp").include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean esPagar = request.getParameter("Pagar") != null;
		Boolean esVolver = request.getParameter("Volver") != null;

		try {
			if (esPagar)
				this.pagar(request);
			if (esVolver)
				response.sendRedirect("AdministrarAlquiler");
			
			request.setAttribute("ErrorMessage", "");
		}
		catch (ValidationException ex) {
			request.setAttribute("ErrorMessage", ex.getMessage());
		}
		
		if (!esVolver)
			doGet(request, response);
	}

	private void pagar(HttpServletRequest request) throws ValidationException {
		this.Logic.pagar(this.Alquiler);
	}

}
