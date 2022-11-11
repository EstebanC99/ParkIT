<%@page import="java.util.LinkedList"%>
<%@page import="entities.Cocheras.Cochera"%>
<%@page import="entities.Servicios.Servicio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/Error.jsp"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="ISO-8859-1">
<title>Park-IT</title>
<%
LinkedList<Cochera> cocheras = request.getAttribute("ListaCocheras") != null
		? (LinkedList<Cochera>) request.getAttribute("ListaCocheras")
		: new LinkedList<Cochera>();
LinkedList<Servicio> servicios = request.getAttribute("ListaServicios") != null
		? (LinkedList<Servicio>) request.getAttribute("ListaServicios")
		: new LinkedList<Servicio>();
%>
	<script>
		$(document).ready(function(){
		  $("#inputBuscarCochera").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#tableCocherastr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
	</script>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="">Park-IT</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
			</ul>
			<form class="form-inline my-2 ml-2 my-lg-0">
				<a href="Login.jsp" class="btn btn-primary my-2 my-sm-0">Iniciar sesión</a> 
			</form>

		</div>
	</nav>
</body>

<form action="MenuAnonimo" method="get">
	<div class="row">
		<div class="col-12">
			<div class="card" style="margin: 12px;">
				<div class="card-header">
					<a data-toggle="collapse" role="button" data-target="#SeccionCocheras" aria-expanded="true" aria-controls="SeccionCocheras"
						style="text-decoration: none;">Cocheras</a>
				</div>

				<div class="collapse-show" id="SeccionCocheras">
					<div class="card-body">
						<div class="row">

							<div class="card" style="margin: 12px;width:-webkit-fill-available;">
								<div class="card-body">

									<div class="row">
										<div class="col-12 col-sm-12 col-lg-12">
											<input style="margin-bottom: 12px;" type="text"
												class="form-control" id="inputBuscarCochera" name="Buscar"
												autocomplete="off" placeholder="Buscar cochera">
											<div class="table-responsive">
												<table class="table" id="tableCocheras">
													<thead>
														<tr>
															<th>ID</th>
															<th>Numero Cochera</th>
															<th>Ubicacion</th>
															<th>Disponible</th>
															<th>Tipo Cochera</th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<%
														for (Cochera cochera : cocheras) {
														%>
														<tr>
															<td><%=cochera.getID()%></td>
															<td><%=cochera.getNroCochera()%></td>
															<td><%=cochera.getUbicacion()%></td>
															<td><input type="checkbox"
																<%=cochera.isDisponible() ? "checked='checked'" : ""%>
																disabled></td>
															<td><%=cochera.getTipoCochera().getDescripcion()%></td>
														</tr>
														<%
														}
														%>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-12">
			<div class="card" style="margin: 12px;">
				<div class="card-header">
					<a data-toggle="collapse" data-target="#SeccionServicios" aria-expanded="false"
						style="text-decoration: none;">Servicios</a>
				</div>

				<div class="collapse" id="SeccionServicios">
					<div class="card-body">
						<div class="row">

							<div class="card" style="margin: 12px; width:-webkit-fill-available;">
								<div class="card-body">

									<div class="row">
										<div class="col-12 col-sm-12 col-lg-12">
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th>ID</th>
															<th>Tipo</th>
															<th>Precio</th>
														</tr>
													</thead>
													<tbody>
														<%
														for (Servicio servicio : servicios) {
														%>
														<tr>
															<td><%=servicio.getID()%></td>
															<td><%=servicio.getDescripcion()%></td>
															<td><%=servicio.getPrecio()%></td>
														</tr>
														<%
														}
														%>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</form>
</html>