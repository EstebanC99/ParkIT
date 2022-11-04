<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<jsp:include page="WEB-INF/Navegacion.html" />
</body>

<form action="MenuPrincipal" method="post">
	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionEstacionamiento"
				style="text-decoration: none;">Estacionamiento</a>
		</div>

		<div class="collapse-in" id="SeccionEstacionamiento">
			<div class="card-body">
				<div class="row">
				
					<!--  INICIO TIPOS DE VEHICULOS  -->
					<div class="col-auto">
						<div class="card" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">Tipos de Vehiculos</h5>
								<h6 class="card-subtitle mb-2 text-muted">Alta, Baja o
									Modificación</h6>
								<p class="card-text">Accede al menú para visualizar,
									modificar o eliminar los tipos de vehiculos admitidos por el
									estacionamiento.</p>
								<div class="pull-right">
									<button class="btn btn-link" type="submit" name="Accion"
										value="AdministrarTipoVehiculo">Ir a</button>
								</div>
							</div>
						</div>
					</div>
					<!--  FIN TIPOS DE VEHICULOS -->
					
					<!--  INICIO SERVICIOS  -->
					<div class="col-auto">
						<div class="card" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">Tipos de Servicios</h5>
								<h6 class="card-subtitle mb-2 text-muted">Alta, Baja o
									Modificación</h6>
								<p class="card-text">Accede al menú para visualizar,
									modificar o eliminar los tipos de servicios ofrecidos por el
									estacionamiento.</p>
								<div class="pull-right">
									<button class="btn btn-link" type="submit" name="Accion"
										value="AdministrarServicio">Ir a</button>
								</div>
							</div>
						</div>
					</div>
					<!--  FIN SERVICIOS -->
					
					<!--  INICIO TIPOS DE COCHERAS  -->
					<div class="col-auto">
						<div class="card" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">Tipos de Cocheras</h5>
								<h6 class="card-subtitle mb-2 text-muted">Alta, Baja o
									Modificación</h6>
								<p class="card-text">Accede al menú para visualizar,
									modificar o eliminar los tipos de cocheras admitidos por el
									estacionamiento.</p>
								<div class="pull-right">
									<button class="btn btn-link" type="submit" name="Accion"
										value="AdministrarTipoCochera">Ir a</button>
								</div>
							</div>
						</div>
					</div>
					<!--  FIN TIPOS DE COCHERAS  -->
					
					<!--  INICIO TIPOS DE ALQUILERES  -->
					<div class="col-auto">
						<div class="card" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">Tipos de Alquileres</h5>
								<h6 class="card-subtitle mb-2 text-muted">Alta, Baja o
									Modificación</h6>
								<p class="card-text">Accede al menú para visualizar,
									modificar o eliminar los tipos de alquileres admitidos por el
									estacionamiento.</p>
								<div class="pull-right">
									<button class="btn btn-link" type="submit" name="Accion"
										value="AdministrarTipoAlquiler">Ir a</button>
								</div>
							</div>
						</div>
					</div>
					<!--  FIN TIPOS DE ALQUILERES  -->

				</div>

			</div>
		</div>
		<!--  FIN SECCION ESTACIONAMIENTO -->

	</div>
</form>

</html>