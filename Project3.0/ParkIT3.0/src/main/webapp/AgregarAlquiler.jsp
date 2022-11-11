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
	<title>Agregar Alquiler</title>
</head>
<body>
	<jsp:include page ="WEB-INF/Navegacion.html"/>
	
	<div class="card" style="margin: 12px;">
		<h5 class="card-header" style="backgroud-color: aliceblue;">
			Nuevo Alquiler
		</h5>
		<div class="card-body">
			<div class="col-md-6">
				<div class="text-danger">
					<%=(String)request.getAttribute("ErrorMessage") != null ? (String)request.getAttribute("ErrorMessage") : ""%>
				</div>
			</div>
			<form class="form" action="AgregarAlquiler" method="post">
			
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
										<span class="input-group-text" id="TextEmpleado">Empleado</span>
									</div>
									<jsp:include page="WEB-INF/ComboEmpleados.jsp"></jsp:include>
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextTipoAlquiler">Tipo Alquiler</span>
									</div>
									<jsp:include page="WEB-INF/ComboTiposAlquileres.jsp"></jsp:include>
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextTiempoEstadoa">Tiempo Estadia</span>
									</div>
									<input type="number" class="form-control" id="inputTiempoEstadia" name="TiempoEstadia" placeholder="Ex: 1, 2, ..., n" required="true" value="0">
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextVehiculo">Vehiculo</span>
									</div>
									<jsp:include page="WEB-INF/ComboVehiculos.jsp"></jsp:include>
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="SelectCochera">Cochera</span>
									</div>
									<jsp:include page="WEB-INF/ComboCocheras.jsp"></jsp:include>
									<div class="input-group-prepend">
										<span class="input-group-text" id="SelectFormaPago">Forma de Pago</span>
									</div>
									<jsp:include page="WEB-INF/ComboFormasPago.jsp"></jsp:include>
								</div>
							</div>
							
							<div class="form-row">
								<div class="col-lg-11">
									<div class="pull-right">
										<button class="btn btn-primary" type="submit" name="Agregar">
											<i class="fa fa-plus pr-2"></i>Agregar
										</button>
										<button class="btn btn-secondary" type="submit" name="Cancelar" >
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
	
		</div>
	</div>
	
</body>
</html>