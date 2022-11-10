package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logs.Log;

@WebServlet("/AlquileresMain")
public class AlquileresMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Accion> Acciones;
	
    public AlquileresMainController() {
        super();
        this.Acciones = new HashMap<>();
        
        // SECCION DE MENUS
        this.Acciones.put("AdministrarAlquiler", new Accion() {
			public void ejecutar(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				response.sendRedirect("AdministrarAlquiler");
			}
		});
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
