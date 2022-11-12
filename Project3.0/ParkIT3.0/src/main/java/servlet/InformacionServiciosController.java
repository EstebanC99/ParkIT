package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Servicios.Servicio;
import exceptions.ValidationException;
import logic.Servicios.AdministrarServicioLogic;
import logs.Log;

@WebServlet("/InformacionServicios")
public class InformacionServiciosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdministrarServicioLogic Logic;
	
	private Servicio Servicio;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformacionServiciosController() {
        super();
        this.Logic = AdministrarServicioLogic.getIntancia();
        this.Servicio = new Servicio();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Servicio> servicios= new LinkedList<Servicio>();
		
		servicios= this.Logic.getAll();
		request.setAttribute("ListaServicios", servicios);
		request.getRequestDispatcher("InformacionServicios.jsp").include(request, response);
	}
}