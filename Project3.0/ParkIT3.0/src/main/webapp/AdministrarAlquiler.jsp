<%@page import="java.time.LocalDate"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Alquileres.Alquiler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/Error.jsp"%>
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
<title>Administrar Alquileres</title>

<%
LinkedList<Alquiler> alquileres = request.getAttribute("ListaAlquileres") != null
		? (LinkedList<Alquiler>) request.getAttribute("ListaAlquileres")
		: new LinkedList<Alquiler>();
Alquiler alquilerSeleccionado = (Alquiler) request.getAttribute("AlquilerSeleccionado");
%>
</head>
<body>
	<jsp:include page="WEB-INF/Navegacion.html" />

	<div class="card" style="margin: 12px;">
		<h5 class="card-header" style="backgroud-color: aliceblue;">
			Alquileres registrados</h5>
		<div class="card-body">
			<div class="col-md-6">
				<div class="text-danger">
					<%=(String) request.getAttribute("ErrorMessage") != null ? (String) request.getAttribute("ErrorMessage") : ""%>
				</div>
			</div>
			<form class="form" action="AdministrarAlquiler" method="post">

				<!-- SECCION DE ALTA Y MODIFICACION  -->
				<div class="card" style="margin: 12px;">
					<div class="card-header">
						<a data-toggle="collapse" data-target="#Filtros"
							style="text-decoration: none;">Filtros</a> <a
							data-toggle="collapse" data-target="#Filtros" class="pull-right"
							style="text-decoration: none;"> <i
							class="fa fa-arrow-down pr-2"></i>
						</a>
					</div>
					<div class="collapse-in" id="Filtros">
						<div class="card-body">
							<p class="card-title">Filtrar alquileres</p>

							<div class="form-group">
								<div class="form-row">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="TextFechaInicio">Fecha
												Inicio</span>
										</div>
										<input type="date" class="form-control" id="inputFechaInicio"
											name="FechaInicio" value="<%=LocalDate.now()%>">
										<div class="input-group-prepend">
											<span class="input-group-text" id="TextFechaFin">Fecha
												Fin</span>
										</div>
										<input type="date" class="form-control" id="inputFechaFin"
											name="FechaFin" value="<%=LocalDate.now()%>">
										<div class="input-group-prepend">
											<span class="input-group-text" id="TextEstadoAlquiler">Estado</span>
										</div>
										<jsp:include page="WEB-INF/ComboEstadosAlquiler.jsp" />
									</div>
								</div>

								<div class="form-row">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="SelectFormaPago">Forma
												Pago</span>
										</div>
										<jsp:include page="WEB-INF/ComboFormasPago.jsp"></jsp:include>
										<div class="input-group-prepend">
											<span class="input-group-text" id="SelectTipoAlquiler">Tipo
												Alquiler</span>
										</div>
										<jsp:include page="WEB-INF/ComboTiposAlquileres.jsp"></jsp:include>
									</div>
								</div>

								<div class="form-row">
									<div class="col-lg-11">
										<div class="pull-right">
											<button class="btn btn-primary" type="submit" name="Buscar">
												<i class="fa fa-search pr-2"></i>Buscar
											</button>
											<button class="btn btn-secondary" type="submit"
												name="Cancelar">
												<i class="fa fa-remove pr-2"></i>Cancelar
											</button>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!--  FIN ALTA Y MODIFICACION  -->
			</form>
			<form action="AdministrarAlquiler" method="post">
				<!--  SECCION DE GRILLA  -->
				<div class="card" style="margin: 12px;">
					<div class="card-body">
						<h5 class="card-title">Registrados</h5>

						<button class="btn btn-primary pull-right" type="submit"
							name="Agregar">
							<i class="fa fa-plus pr-2"></i>Agregar
						</button>


						<div class="row">
							<div class="col-12 col-sm-12 col-lg-12">
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th>ID</th>
												<th>Fecha Hora Inicio</th>
												<th>Fecha Hora Fin</th>
												<th>Pagado</th>
												<th>Forma Pago</th>
												<th>Tipo Alquiler</th>
												<th>Patente</th>
												<th>Precio Pactado</th>
												<th>Precio Final</th>
												<th>Nro. Cochera</th>
												<th>Empleado</th>
												<th></th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<%
											for (Alquiler alquiler : alquileres) {
											%>
											<tr>
												<td><%=alquiler.getID()%></td>
												<td><%=alquiler.getFechaHoraInicio().toString()%></td>
												<td><%=alquiler.getFechaHoraFin().toString()%></td>
												<td><input type="checkbox"
													<%=alquiler.isPagado() ? "checked='checked'" : ""%>
													disabled></td>
												<td><%=alquiler.getFormaPago().getDescripcion()%></td>
												<td><%=alquiler.getTipoAlquiler().getDescripcion()%></td>
												<td><%=alquiler.getVehiculo().getPatente()%></td>
												<td><%=alquiler.getPrecio()%></td>
												<td><%=alquiler.getTotalPrecio()%></td>
												<td><%=alquiler.getCochera().getNroCochera()%></td>
												<td><%=alquiler.getEmpleado().toString()%></td>
												<td>
													<button class="btn btn-link-custom" type="submit"
														name="ModificarID" value="<%=alquiler.getID()%>">
														<i class="fa fa-pencil pr-2"></i>
													</button>
													<button class="btn btn-link" style="color: #dd4b39;"
														type="submit" name="EliminarID"
														value="<%=alquiler.getID()%>"
														<%=alquiler.isPagado() ? "disabled" : "enabled"%>>
														<i class="fa fa-trash pr-2"></i>
													</button>
												</td>
												<td>
													<button class="btn btn-link-custom" type="submit"
														name="PagarID" value="<%=alquiler.getID()%>">
														<i class="fa fa-credit-card pr-2"></i>Pagar
													</button>
												</td>
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
				<!--  FIN DE GRILLA  -->
			</form>



		</div>
	</div>

</body>
</html>