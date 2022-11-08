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
	<title>Alquileres Menu</title>
		
	<%
		//LinkedList<TipoCochera> tiposCochera = request.getAttribute("ListaTiposCochera") != null ? (LinkedList<TipoCochera>)request.getAttribute("ListaTiposCochera") : new LinkedList<TipoCochera>();
		//TipoCochera cocheraSeleccionada = (TipoCochera)request.getAttribute("CocheraSeleccionada");
	%>
</head>

<body>
	<jsp:include page ="WEB-INF/Navegacion.html"/>

<form action="AlquileresMain" method="post">

	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionOpciones"
				style="text-decoration: none;">Opciones de Menu</a>
		</div>

		<!-- INICIO OPCIONES -->
		<div class="collapse" id="SeccionOpciones">
			<div class="card-body">
				<div class="row">
				
					<!--  INICIO COCHERAS  -->
					<div class="col-auto">
						<div class="card" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">Ver cocheras</h5>
								<h6 class="card-subtitle mb-2 text-muted">Listado de Cocheras en sus respectivos estados
								</h6>
								<p class="card-text">Accede a un listado de las cocheras
								y permite registrar el alquiler de ellas.</p>
								<div class="pull-right">
									<button class="btn btn-link" type="submit" name="Accion"
										value="AdministrarTipoVehiculo">Ir a</button>
								</div>
							</div>
						</div>
					</div>
					<!--  FIN COCHERAS -->
					
					<!--  INICIO ALQUILERES  -->
					<div class="col-auto">
						<div class="card" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">Alquileres</h5>
								<h6 class="card-subtitle mb-2 text-muted">Listado de los alquileres existentes</h6>
								<p class="card-text">Accede a un listado de los alquileres existentes
								y permite registrar el pago de ellos.</p>
								<div class="pull-right">
									<button class="btn btn-link" type="submit" name="Accion"
										value="AdministrarAlquiler">Ir a</button>
								</div>
							</div>
						</div>
					</div>
					<!--  FIN ALQUILERES -->
					
				</div>
			</div>
		</div>
		<!--  FIN OPCIONES -->
	</div>
	
	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionInformacionGeneral"
				style="text-decoration: none;">Informacion General</a>
		</div>
		
		<!-- INICIO DETALLE GENERAL -->
		<div class="collapse-in" id="SeccionInformacionGeneral">
			<div class="card-body">
				<div class="row">
					
				</div>
			</div>
		</div>
		
		<!-- FIN DETALLE GENERAL -->
	</div>

</form>
</body>
</html>