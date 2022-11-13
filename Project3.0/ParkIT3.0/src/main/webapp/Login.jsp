<%@page import="servlet.Login"%>
<%@page import="entities.Servicios.Servicio"%>
<%@page import="java.util.LinkedList"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Login</title>

<link rel="icon" href="http://getboostrap.com/favicon.ico">

<%
String resp = "";
if(request.getParameter("logout") != null){
	request.getSession().removeAttribute("usuario");
	response.sendRedirect("Login.jsp");
}else{
	
 if (request.getSession().getAttribute("usuario") != null) {
	response.sendRedirect("Index.jsp");
} 

}
if (request.getParameter("respuesta") != null) {
resp = "Usuario o contraseña invalidos";
}
%>

</head>
<body><jsp:include page="WEB-INF/NavegacionAnonima.jsp" />


	<form class="form" action="Login" method="post">

		<div class="card mx-auto mt-5" style="width: 500px">
			<div class="" id="Detalle">
				<h5 class="card-header" style="backgroud-color: aliceblue;">
					Iniciar sesion</h5>
				<div class="card-body mx-auto">
					<div class="form-group">
						<div class="">
							<div class="form-outline mb-4">
								<label class="form-label" for="email">Nombre de usuario
								</label> <input type="text" name="nombreUsuario" class="form-control" />
							</div>
							<div class="form-outline mb-4">
								<label class="form-label" for="password">Contraseña</label> <input
									type="password" id="password" name="password"
									class="form-control" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="text-danger">
								<%=resp%>
							</div>
						</div>
						<div class="form-row">
							<div class="col-lg-11">
								<div class="pull-right">
									<button class="btn btn-primary">Ingresar</button>
									<button class="btn btn-secondary">Registrarse</button>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</form>
</body>
</html>
