package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Alquileres.Alquiler;
import logic.Alquileres.AdministrarAlquilerLogic;
import logic.Alquileres.AdministrarTipoAlquilerLogic;
import logic.Alquileres.FormaPagoLogic;

@WebServlet("/AdministrarAlquiler")
public class AdministrarAlquilerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdministrarAlquilerLogic Logic;
	private FormaPagoLogic FormaPagoLogicService;
	private AdministrarTipoAlquilerLogic TipoAlquilerLogic;
       
    public AdministrarAlquilerController() {
        super();
        this.Logic = AdministrarAlquilerLogic.getInstancia();
        this.FormaPagoLogicService = FormaPagoLogic.getInstancia();
        this.TipoAlquilerLogic = AdministrarTipoAlquilerLogic.getIntancia();
        
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Alquiler> alquileres = new LinkedList<>();
		alquileres = this.Logic.getAll();
		
		//String buscarID = request.getParameter("BuscarID");
		
//		if (buscarID != null) {
//			this.Cliente.setID(Integer.parseInt(buscarID));
//			request.setAttribute("ClienteSeleccionado", this.Logic.getByID(this.Cliente));
//		}
		
		request.setAttribute("ListaAlquileres", alquileres);
		request.setAttribute("ListaFormasPago", this.FormaPagoLogicService.getAll());
		request.setAttribute("ListaTiposAlquileres", this.TipoAlquilerLogic.getAll());
		request.getRequestDispatcher("AdministrarAlquiler.jsp").include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
