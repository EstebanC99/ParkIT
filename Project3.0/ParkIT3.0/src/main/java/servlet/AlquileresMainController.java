package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logs.Log;
import logic.Alquileres.AdministrarAlquilerLogic;
import logic.Cocheras.CocheraLogic;
import logic.Servicios.ServicioVehiculoLogic;

@WebServlet("/AlquileresMain")
public class AlquileresMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Accion> Acciones;
	private AdministrarAlquilerLogic Logic;
	private CocheraLogic CocheraLogicService;
	private ServicioVehiculoLogic ServicioVehiculoLogicService;
	
    public AlquileresMainController() {
        super();
        this.Acciones = new HashMap<>();
        this.Logic = AdministrarAlquilerLogic.getInstancia();
        this.CocheraLogicService = CocheraLogic.getInstancia();
        this.ServicioVehiculoLogicService = ServicioVehiculoLogic.getInstancia();
        
        // SECCION DE MENUS
        this.Acciones.put("AdministrarAlquiler", new Accion() {
			public void ejecutar(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				response.sendRedirect("AdministrarAlquiler");
			}
        });
        this.Acciones.put("Cocheras", new Accion() {
			public void ejecutar(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				response.sendRedirect("Cochera");
			}
        });
        
        this.Acciones.put("AdministrarServiciosVehiculos", new Accion() {
			public void ejecutar(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				response.sendRedirect("AdministrarServiciosVehiculos");
			}
        });
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("CocherasLibres", this.CocheraLogicService.getCantidadCocherasLibres());
    	request.setAttribute("Cocheras", this.CocheraLogicService.getCantidadCocheras());
    	request.setAttribute("AlquileresVigentes", this.Logic.getCantidadAlquileresVigentes());
    	request.setAttribute("AlquileresEnDeuda", this.Logic.getCantidadAlquileresImpagos());
    	request.setAttribute("ServiciosDeHoy", this.ServicioVehiculoLogicService.getCantidadDeServiciosParaLaFecha(LocalDate.now()));
    	request.setAttribute("ServicioDeManiana", this.ServicioVehiculoLogicService.getCantidadDeServiciosParaLaFecha(LocalDate.now().plusDays(1)));
    	request.getRequestDispatcher("AlquileresMain.jsp").include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("Accion");

		try {
			this.Acciones.get(accion).ejecutar(request, response);
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					"La pagina solicitada no puede accederse en este momento. Por favor, regrese al menu principal!");
			Log.registrarFineLog(ex);
		}
	}

}
