<%@page import="javax.servlet.http.HttpServletResponse"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<%
String textoBotonSesion = "Iniciar sesion";
String tipoBoton = "primary";
if (request.getSession().getAttribute("usuario") != null) {
	textoBotonSesion = "Cerrar sesion";
	tipoBoton = "danger";
}
%>
</head>
<link href="styles/css/bootstrap.css" rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	crossorigin="anonymous"></script>
<script src="js/jquery-3.6.0.min.js"></script>
<script src="styles/js/bootstrap.min.js"></script>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="Index.jsp">Park-IT</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div
			class="collapse navbar-collapse d-flex-lg justify-content-between"
			id="navbarSupportedContent">
			<div>
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="Index.jsp">Inicio<span class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link"
						href="./AdministrarServiciosVehiculos">Servicios</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Estacionamiento </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="./Cochera">Cocheras</a> <a
								class="dropdown-item" href="./Vehiculo">Vehiculos</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="./ListaEspera">Lista espera</a>
						</div></li>
					<li class="nav-item"><a class="nav-link"
						href="./AlquileresMain">Alquileres</a></li>
				</ul>
			</div>
			<form class="form" action="Login" method="put">
				<a class="btn btn-<%=tipoBoton%>" href="Login.jsp?logout=true"><%=textoBotonSesion%></a>
			</form>
		</div>
	</nav>

</body>

</html>