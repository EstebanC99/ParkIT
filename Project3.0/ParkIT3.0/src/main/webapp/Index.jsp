<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Park-IT</title>
</head>

<body>
	<jsp:include page="WEB-INF/Navegacion.html" />
</body>

<form action="MenuPrincipal" method="post">

	<!-- CARD DE ESTACIONAMIENTO  -->
	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionEstacionamiento"
				style="text-decoration: none;">Estacionamiento</a>
		</div>

		<!-- INICIO SECCION ESTACIONAMIENTO -->
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
					
					<!--  INICIO PRECIOS DE ALQUILERES  -->
					<div class="col-auto">
						<div class="card" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">Precios</h5>
								<h6 class="card-subtitle mb-2 text-muted">Alta, Baja o
									Modificación</h6>
								<p class="card-text">Accede al menú para visualizar,
									modificar o eliminar los precios de alquileres vigentes en el
									estacionamiento.</p>
								<div class="pull-right">
									<button class="btn btn-link" type="submit" name="Accion"
										value="AdministrarPrecioAlquiler">Ir a</button>
								</div>
							</div>
						</div>
					</div>
					<!--  FIN PRECIOS DE ALQUILERES  -->

					<!--  INICIO TIPOS DE FORMAS DE PAGO  --> 
					<div class="col-auto"> 
						<div class="card" style="width: 18rem;"> 
							<div class="card-body"> 
								<h5 class="card-title">Tipos de Formas de Pago</h5> 
								<h6 class="card-subtitle mb-2 text-muted">Alta, Baja o 
									Modificación</h6> 
								<p class="card-text">Accede al menú para visualizar, 
									modificar o eliminar los tipos de formas de pago admitidos por el 
									estacionamiento.</p> 
								<div class="pull-right"> 
									<button class="btn btn-link" type="submit" name="Accion" 
										value="AdministrarFormaPago">Ir a</button> 
								</div> 
							</div> 
						</div> 
					</div> 
					<!--  FIN TIPOS DE TIPOS DE FORMAS DE PAGO --> 
				</div>
			</div>
		</div>
		<!--  FIN SECCION ESTACIONAMIENTO -->
	</div>
	
	<!-- CARD DE USUARIOS -->
	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionUsuarios"
				style="text-decoration: none;">Usuarios</a>
		</div>

	<!--  INICIO SECCION USUARIOS -->
		<div class="collapse" id="SeccionUsuarios">
			<div class="card-body">
				<div class="row">
				
					<!--  INICIO EMPLEADOS  -->
					<div class="col-auto">
						<div class="card" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">Empleados</h5>
								<h6 class="card-subtitle mb-2 text-muted">Alta, Baja o
									Modificación</h6>
								<p class="card-text">Accede al menú para visualizar,
									modificar o eliminar los empleados del lugar. A la vez, 
									asignar o modificar usuarios correspondientes.</p>
								<div class="pull-right">
									<button class="btn btn-link" type="submit" name="Accion"
										value="Empleado">Ir a</button>
								</div>
							</div>
						</div>
					</div>
					<!--  FIN EMPLEADOS -->
					
					<!--  INICIO CLIENTES  -->
					<div class="col-auto">
						<div class="card" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">Clientes</h5>
								<h6 class="card-subtitle mb-2 text-muted">Alta, Baja o
									Modificación</h6>
								<p class="card-text">Accede al menú para visualizar,
									modificar o eliminar los clientes del lugar. A la vez, 
									asignar o modificar usuarios correspondientes.</p>
								<div class="pull-right">
									<button class="btn btn-link" type="submit" name="Accion"
										value="Cliente">Ir a</button>
								</div>
							</div>
						</div>
					</div>
					<!--  FIN CLIENTES -->
					
				</div>
	
			</div>
		</div>
	</div>

</form>
</html>