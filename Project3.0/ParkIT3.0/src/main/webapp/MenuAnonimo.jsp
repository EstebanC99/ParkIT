<%@page import="java.util.LinkedList"%>
<%@page import="entities.Cocheras.Cochera"%>
<%@page import="entities.Servicios.Servicio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/Error.jsp"%>
<!DOCTYPE html>
<html>
<head>

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
</head>

<body><jsp:include page="WEB-INF/NavegacionAnonima.jsp" />

<form action="MenuAnonimo" method="get">

	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionCocheras" 
				style="text-decoration: none;">Cocheras</a>
		</div>

		<div class="collapse-in" id="SeccionCocheras">
			<div class="card-body">
				
				<div class="form-group">
					<div class="form-row">
						<div class="input-group mb-3">
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


	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionServicios" style="text-decoration: none;">Servicios</a>
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



</form>

	<script>
		$(document).ready(function(){
		  $("#inputBuscarCochera").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#tableCocheras").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
	</script>
</html>