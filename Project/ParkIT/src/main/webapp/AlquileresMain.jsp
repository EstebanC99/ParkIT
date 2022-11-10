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
		int cocherasLibres = request.getAttribute("CocherasLibres") != null ? (Integer)request.getAttribute("CocherasLibres") : 0;
		int cocheras = request.getAttribute("Cocheras") != null ? (Integer)request.getAttribute("Cocheras") : 0;
		int alquileresVigentes = request.getAttribute("AlquileresVigentes") != null ? (Integer)request.getAttribute("AlquileresVigentes") : 0;
		int alquileresEnDeuda = request.getAttribute("AlquileresEnDeuda") != null ? (Integer)request.getAttribute("AlquileresEnDeuda") : 0;
		int serviciosDeHoy = request.getAttribute("ServiciosDeHoy") != null ? (Integer)request.getAttribute("ServiciosDeHoy") : 0;
		int serviciosManiana = request.getAttribute("ServicioDeManiana") != null ? (Integer)request.getAttribute("ServicioDeManiana") : 0;
	%>
</head>

<body>
	<jsp:include page ="WEB-INF/Navegacion.html"/>

<form action="AlquileresMain" method="post">

	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionInformacionGeneral"
				style="text-decoration: none;">Informacion General</a>
		</div>
		
		<!-- INICIO DETALLE GENERAL -->
		<div class="collapse-in" id="SeccionInformacionGeneral">
			<div class="card-body">
			
				<div class="col-md-12">
				
					<div class="row col-md-12">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text col-md-12" id="TextCocheras">Cocheras</span>
							</div>
							<label class="form-control" id="labelCocheras"><%=cocheras%></label>
							<div class="col-sm-1 col-md-1 col-lg-1"></div>
							<div class="input-group-prepend">
								<span class="input-group-text" id="TextAlquileresVigentes">Alquileres Vigentes</span>
							</div>
							<label class="form-control"id="labelAlquileresVigentes"><%=alquileresVigentes%></label>
							<div class="col-sm-1 col-md-1 col-lg-1"></div>
							<div class="input-group-prepend">
								<span class="input-group-text" id="TextServiciosDeHoy">Servicios de Hoy</span>
							</div>
							<label class="form-control"id="labelServiciosDeHoy"><%=serviciosDeHoy%></label>
						</div>
					</div>
					
					<div class="row col-md-12">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="TextCocherasLibres">Cocheras Libres</span>
							</div>
							<label class="form-control <%=cocherasLibres < 5 ? "text-danger" : "" %>" id="labelCocherasLibres"><%=cocherasLibres%></label>
							<div class="col-sm-1 col-md-1 col-lg-1"></div>
							<div class="input-group-prepend">
								<span class="input-group-text" id="TextAlquileresEnDeuda">Alquileres en Deuda</span>
							</div>
							<label class="form-control <%=alquileresEnDeuda != 0 ? "text-danger" : "" %>" id="labelAlquileresEnDeuda"><%=alquileresEnDeuda%></label>
							<div class="col-sm-1 col-md-1 col-lg-1"></div>
							<div class="input-group-prepend">
								<span class="input-group-text" id="TextServiciosProximos">Servicios del proximo dia</span>
							</div>
							<label class="form-control" id="labelServiciosProximos"><%=serviciosManiana%></label>
						</div>	
					</div>
					
				</div>
				
			</div>
		</div>
		
		<!-- FIN DETALLE GENERAL -->
	</div>


	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionOpciones"
				style="text-decoration: none;">Opciones de Menu</a>
		</div>

		<!-- INICIO OPCIONES -->
		<div class="collapse-in" id="SeccionOpciones">
			<div class="card-body">
				<div class="row">
				
					<!--  INICIO COCHERAS  -->
					<div class="col-auto">
						<div class="card" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">Ver cocheras</h5>
								<h6 class="card-subtitle mb-2 text-muted">Listado de Cocheras
								</h6>
								<p class="card-text">Accede al listado de Cocheras para visualizarlas, junto con su disponibilidad.</p>
								<div class="pull-right">
									<button class="btn btn-link" type="submit" name="Accion"
										value="Cocheras">Ir a</button>
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
								<h6 class="card-subtitle mb-2 text-muted">Listado de alquileres</h6>
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

</form>
</body>
</html>