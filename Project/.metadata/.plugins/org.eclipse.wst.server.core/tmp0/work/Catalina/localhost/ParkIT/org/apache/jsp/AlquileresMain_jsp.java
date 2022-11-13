/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.36
 * Generated at: 2022-11-13 16:33:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class AlquileresMain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n");
      out.write("    <meta name=\"description\" content=\"\">\n");
      out.write("    <meta name=\"author\" content=\"\">\n");
      out.write("\t<title>Alquileres Menu</title>\n");
      out.write("\t\t\n");
      out.write("\t");

		int cocherasLibres = request.getAttribute("CocherasLibres") != null ? (Integer)request.getAttribute("CocherasLibres") : 0;
		int cocheras = request.getAttribute("Cocheras") != null ? (Integer)request.getAttribute("Cocheras") : 0;
		int alquileresVigentes = request.getAttribute("AlquileresVigentes") != null ? (Integer)request.getAttribute("AlquileresVigentes") : 0;
		int alquileresEnDeuda = request.getAttribute("AlquileresEnDeuda") != null ? (Integer)request.getAttribute("AlquileresEnDeuda") : 0;
		int serviciosDeHoy = request.getAttribute("ServiciosDeHoy") != null ? (Integer)request.getAttribute("ServiciosDeHoy") : 0;
		int serviciosManiana = request.getAttribute("ServicioDeManiana") != null ? (Integer)request.getAttribute("ServicioDeManiana") : 0;
	
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "WEB-INF/Navegacion.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<form action=\"AlquileresMain\" method=\"post\">\n");
      out.write("\n");
      out.write("\t<div class=\"card\" style=\"margin: 12px;\">\n");
      out.write("\t\t<div class=\"card-header\">\n");
      out.write("\t\t\t<a data-toggle=\"collapse\" data-target=\"#SeccionInformacionGeneral\"\n");
      out.write("\t\t\t\tstyle=\"text-decoration: none;\">Informacion General</a>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t<!-- INICIO DETALLE GENERAL -->\n");
      out.write("\t\t<div class=\"collapse-in\" id=\"SeccionInformacionGeneral\">\n");
      out.write("\t\t\t<div class=\"card-body\">\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\t<div class=\"col-md-12\">\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t\t<div class=\"row col-md-12\">\n");
      out.write("\t\t\t\t\t\t<div class=\"input-group mb-3\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"input-group-prepend\">\n");
      out.write("\t\t\t\t\t\t\t\t<span class=\"input-group-text col-md-12\" id=\"TextCocheras\">Cocheras</span>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<label class=\"form-control\" id=\"labelCocheras\">");
      out.print(cocheras);
      out.write("</label>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-1 col-md-1 col-lg-1\"></div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"input-group-prepend\">\n");
      out.write("\t\t\t\t\t\t\t\t<span class=\"input-group-text\" id=\"TextAlquileresVigentes\">Alquileres Vigentes</span>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<label class=\"form-control\"id=\"labelAlquileresVigentes\">");
      out.print(alquileresVigentes);
      out.write("</label>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-1 col-md-1 col-lg-1\"></div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"input-group-prepend\">\n");
      out.write("\t\t\t\t\t\t\t\t<span class=\"input-group-text\" id=\"TextServiciosDeHoy\">Servicios de Hoy</span>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<label class=\"form-control\"id=\"labelServiciosDeHoy\">");
      out.print(serviciosDeHoy);
      out.write("</label>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<div class=\"row col-md-12\">\n");
      out.write("\t\t\t\t\t\t<div class=\"input-group mb-3\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"input-group-prepend\">\n");
      out.write("\t\t\t\t\t\t\t\t<span class=\"input-group-text\" id=\"TextCocherasLibres\">Cocheras Libres</span>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<label class=\"form-control ");
      out.print(cocherasLibres < 5 ? "text-danger" : "" );
      out.write("\" id=\"labelCocherasLibres\">");
      out.print(cocherasLibres);
      out.write("</label>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-1 col-md-1 col-lg-1\"></div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"input-group-prepend\">\n");
      out.write("\t\t\t\t\t\t\t\t<span class=\"input-group-text\" id=\"TextAlquileresEnDeuda\">Alquileres en Deuda</span>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<label class=\"form-control ");
      out.print(alquileresEnDeuda != 0 ? "text-danger" : "" );
      out.write("\" id=\"labelAlquileresEnDeuda\">");
      out.print(alquileresEnDeuda);
      out.write("</label>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-1 col-md-1 col-lg-1\"></div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"input-group-prepend\">\n");
      out.write("\t\t\t\t\t\t\t\t<span class=\"input-group-text\" id=\"TextServiciosProximos\">Servicios del proximo dia</span>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<label class=\"form-control\" id=\"labelServiciosProximos\">");
      out.print(serviciosManiana);
      out.write("</label>\n");
      out.write("\t\t\t\t\t\t</div>\t\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t<!-- FIN DETALLE GENERAL -->\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<div class=\"card\" style=\"margin: 12px;\">\n");
      out.write("\t\t<div class=\"card-header\">\n");
      out.write("\t\t\t<a data-toggle=\"collapse\" data-target=\"#SeccionOpciones\"\n");
      out.write("\t\t\t\tstyle=\"text-decoration: none;\">Opciones de Menu</a>\n");
      out.write("\t\t</div>\n");
      out.write("\n");
      out.write("\t\t<!-- INICIO OPCIONES -->\n");
      out.write("\t\t<div class=\"collapse-in\" id=\"SeccionOpciones\">\n");
      out.write("\t\t\t<div class=\"card-body\">\n");
      out.write("\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t\t<div class=\"col-md-1\"></div>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t\t<!--  INICIO COCHERAS  -->\n");
      out.write("\t\t\t\t\t<div class=\"col-auto\">\n");
      out.write("\t\t\t\t\t\t<div class=\"card\" style=\"width: 18rem;\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"card-body\">\n");
      out.write("\t\t\t\t\t\t\t\t<h5 class=\"card-title\">Ver cocheras</h5>\n");
      out.write("\t\t\t\t\t\t\t\t<h6 class=\"card-subtitle mb-2 text-muted\">Listado de Cocheras\n");
      out.write("\t\t\t\t\t\t\t\t</h6>\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"card-text\">Accede al listado de Cocheras para visualizarlas, junto con su disponibilidad.</p>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"pull-right\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<button class=\"btn btn-link\" type=\"submit\" name=\"Accion\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\tvalue=\"Cocheras\">Ir a</button>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<!--  FIN COCHERAS -->\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<div class=\"col-md-2\"></div>\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<!--  INICIO ALQUILERES  -->\n");
      out.write("\t\t\t\t\t<div class=\"col-auto\">\n");
      out.write("\t\t\t\t\t\t<div class=\"card\" style=\"width: 18rem;\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"card-body\">\n");
      out.write("\t\t\t\t\t\t\t\t<h5 class=\"card-title\">Alquileres</h5>\n");
      out.write("\t\t\t\t\t\t\t\t<h6 class=\"card-subtitle mb-2 text-muted\">Listado de alquileres</h6>\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"card-text\">Accede a un listado de los alquileres existentes\n");
      out.write("\t\t\t\t\t\t\t\ty permite registrar el pago de ellos.</p>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"pull-right\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<button class=\"btn btn-link\" type=\"submit\" name=\"Accion\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\tvalue=\"AdministrarAlquiler\">Ir a</button>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<!--  FIN ALQUILERES -->\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<div class=\"col-md-2\"></div>\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<!--  INICIO SERVICIOS  -->\n");
      out.write("\t\t\t\t\t<div class=\"col-auto\">\n");
      out.write("\t\t\t\t\t\t<div class=\"card\" style=\"width: 18rem;\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"card-body\">\n");
      out.write("\t\t\t\t\t\t\t\t<h5 class=\"card-title\">Servicios</h5>\n");
      out.write("\t\t\t\t\t\t\t\t<h6 class=\"card-subtitle mb-2 text-muted\">Listado de Servicios</h6>\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"card-text\">Accede al listado de los servicios existentes\n");
      out.write("\t\t\t\t\t\t\t\ty permite registrar el pago de ellos.</p>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"pull-right\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<button class=\"btn btn-link\" type=\"submit\" name=\"Accion\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\tvalue=\"AdministrarServiciosVehiculos\">Ir a</button>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<!--  FIN SERVICIOS -->\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!--  FIN OPCIONES -->\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("</form>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
