<%@page import="global.DateFormatter"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Alquileres.Alquiler"%>
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
	<title>Pagar Alquiler</title>
	<%
		Alquiler alquilerSeleccionado = (Alquiler)request.getAttribute("AlquilerSeleccionado");
	%>
</head>
<body>
	<jsp:include page ="WEB-INF/Navegacion.html"/>
	
	<div class="card" style="margin: 12px;">
		<h5 class="card-header" style="backgroud-color: aliceblue;">
			Alquiler Seleccionado
		</h5>
		<div class="card-body">
			<div class="col-md-6">
				<div class="text-danger">
					<%=(String)request.getAttribute("ErrorMessage") != null ? (String)request.getAttribute("ErrorMessage") : ""%>
				</div>
			</div>
			
			<form class="form" action="PagarAlquiler" method="post">
			<!-- SECCION DE ALTA Y MODIFICACION  -->
			<div class="card" style="margin: 12px;">
				<div class="card-header">
					<a data-toggle="collapse" data-target="#" style="text-decoration: none;">Detalle</a>
					<a data-toggle="collapse" data-target="#" class="pull-right" style="text-decoration: none;">
						<i class="fa fa-arrow-down pr-2"></i>
					</a>
				</div>
				<div class="collapse-in" id="Detalle">
					<div class="card-body">
						
						<div class="form-group">
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextFechaInicio">Fecha de Inicio</span>
									</div>
									<label class="form-control" id="labelFechaInicio"><%=DateFormatter.getFormattedDate(alquilerSeleccionado.getFechaInicio())%></label>
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextHoraInicio">Hora de Inicio</span>
									</div>
									<label class="form-control" id="labelHoraInicio"><%=DateFormatter.getFormattedHour(alquilerSeleccionado.getHoraInicio())%></label>
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextFechaFin">Fecha de Fin</span>
									</div>
									<label class="form-control" id="labelFechaFin"><%=DateFormatter.getFormattedDate(alquilerSeleccionado.getFechaFin())%></label>
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextHoraFin">Hora de Fin</span>
									</div>
									<label class="form-control" id="labelHoraFin"><%=DateFormatter.getFormattedHour(alquilerSeleccionado.getHoraFin())%></label>
								</div>
							</div>
						
						<div class="form-group">
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextEmpleado">Empleado</span>
									</div>
									<label class="form-control" id="labelEmpleado"><%=alquilerSeleccionado.getEmpleado().toString()%></label>
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextTipoAlquiler">Tipo Alquiler</span>
									</div>
									<label class="form-control" id="labelTipoAlquiler"><%=alquilerSeleccionado.getTipoAlquiler().getDescripcion()%></label>
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextCochera">Cochera</span>
									</div>
									<label class="form-control" id="labelCochera"><%=alquilerSeleccionado.getCochera().toString()%></label>
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextVehiculo">Vehiculo</span>
									</div>
									<label class="form-control" id="labelVehiculo"><%=alquilerSeleccionado.getVehiculo().toString()%></label>
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextTiempoEstadoa">Tiempo Estadia</span>
									</div>
									<label class="form-control" id="labelTiempoEstadia"><%=alquilerSeleccionado.getTiempoEstadia()%></label>
									<div class="input-group-prepend">
										<span class="input-group-text" id="SelectFormaPago">Forma de Pago</span>
									</div>
									<label class="form-control" id="labelFormaPago"><%=alquilerSeleccionado.getFormaPago().getDescripcion()%></label>
									<label class="form-control" id="labelFormaPagoImputacion"><%=alquilerSeleccionado.getFormaPago().getTotalImputar() * 100%> %</label>
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextPrecio">Precio</span>
									</div>
									<label class="form-control" id="labelPrecio"><%=alquilerSeleccionado.getPrecio()%></label>
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextPrecioFinal">Precio Final</span>
									</div>
									<label class="form-control" id="labelPrecioFinal"><%=alquilerSeleccionado.getTotalPrecio()%></label>
								</div>
							</div>
							
							<div class="form-row">
								<p class="card-title text-success" <%=alquilerSeleccionado.isPagado() ? "" : "hidden" %>>Pago registrado con exito!</p>
							</div>
							
							<div class="form-row">
								<div class="col-lg-11">
									<div class="pull-right">
										<button class="btn btn-primary" type="submit" name="Pagar" <%= alquilerSeleccionado.isPagado() ? "disabled" : "" %>>
											<i class="fa fa-check pr-2"></i>Confirmar pago
										</button>
										<button class="btn btn-secondary" type="submit" name="Volver" >
											<i class="fa fa-remove pr-2"></i>Volver
										</button>
									</div>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<!--  FIN ALTA Y MODIFICACION  -->
			</div>
			</form>
	
		</div>
	</div>
</body>
</html>