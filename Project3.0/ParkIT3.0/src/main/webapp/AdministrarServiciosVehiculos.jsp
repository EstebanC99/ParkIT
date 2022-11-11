<%@page import="java.util.LinkedList"%>
<%@page import="entities.Servicios.ServicioVehiculo"%>
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
	<title>Administrar Servicios de Vehiculos</title>
		
	<%
		LinkedList<ServicioVehiculo> serviciosVehiculos = request.getAttribute("ListaServiciosVehiculos") != null ? (LinkedList<ServicioVehiculo>)request.getAttribute("ListaServiciosVehiculos") : new LinkedList<ServicioVehiculo>();
		ServicioVehiculo serviciosVehiculoSeleccionado = (ServicioVehiculo)request.getAttribute("ServicioVehiculoSeleccionado");
	%>
</head>
<body>
	<jsp:include page ="WEB-INF/Navegacion.html"/>
	
	<div class="card" style="margin: 12px;">
		<h5 class="card-header" style="backgroud-color: aliceblue;">
			Servicios programados para Vehiculos
		</h5>
		<div class="card-body">
			<div class="col-md-6">
				<div class="text-danger">
					<%=(String)request.getAttribute("ErrorMessage") != null ? (String)request.getAttribute("ErrorMessage") : ""%>
				</div>
			</div>
			
			
			<form class="form" action="AdministrarServiciosVehiculos" method="post">
				<div class="card" style="margin: 12px;">
					<div class="card-header">
						<a data-toggle="collapse" data-target="#Filtros" style="text-decoration: none;">Filtros</a> 
							<a data-toggle="collapse" data-target="#Filtros" class="pull-right" style="text-decoration: none;"> <i class="fa fa-plus pr-2"></i>
						</a>
					</div>
				<div class="collapse" id="Filtros">
					<div class="card-body">

						<div class="form-group">
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoDescripcion">Clientes</span>
									</div>
									<jsp:include page="WEB-INF/ComboClientes.jsp"></jsp:include>
								</div>
							</div>
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoDescripcion">Estado Servicio</span>
									</div>
									<jsp:include page="WEB-INF/ComboEstadosAlquiler.jsp"></jsp:include>
								</div>
							</div>
						</div>

							<div class="form-row">
								<div class="col-lg-11">
									<div class="pull-right">
										<button class="btn btn-primary" type="submit" name="Buscar">
											<i class="fa fa-search pr-2"></i>Buscar
										</button>
										<button class="btn btn-secondary" type="submit" name="CancelarBusqueda" data-toggle="collapse" data-target="#Filtros">
											<i class="fa fa-remove pr-2"></i>Cancelar
										</button>
									</div>
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</form>
			
			<form class="form" action="AdministrarServiciosVehiculos" method="post">
			
			<!-- SECCION DE ALTA Y MODIFICACION  -->
			<div class="card" style="margin: 12px;">
				<div class="card-header">
					<a data-toggle="collapse" data-target="#Detalle" style="text-decoration: none;">Detalle</a>
					<a data-toggle="collapse" data-target="#Detalle" class="pull-right" style="text-decoration: none;">
						<i class="fa fa-plus pr-2"></i>
					</a>
				</div>
				<div class="<%=serviciosVehiculoSeleccionado != null ? "" : "collapse" %>" id="Detalle">
					<div class="card-body">
						<p class="card-title">Servicio Vehiculo</p>
						
						<div class="form-group">
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextFechaRealizacion">Fecha de Realizacion</span>
									</div>
									<input type="hidden" class="form-control" id="inputID" name="ID" value="<%=serviciosVehiculoSeleccionado != null ? serviciosVehiculoSeleccionado.getID() : 0%>">
									<input type="date" class="form-control" id="inputFechaRealizacion" name="FechaRealizacion" required="true" value="<%=serviciosVehiculoSeleccionado != null ? serviciosVehiculoSeleccionado.getFechaRealizacion().toString() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="SelectServicio">Servicio</span>
									</div>
									<jsp:include page="WEB-INF/ComboServicios.jsp"></jsp:include>
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="SelectVehiculo">Vehiculo</span>
									</div>
									<jsp:include page="WEB-INF/ComboVehiculos.jsp"></jsp:include>
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="SelectEmpleado">Empleado</span>
									</div>
									<jsp:include page="WEB-INF/ComboEmpleados.jsp"></jsp:include>
								</div>
							</div>
							
							<div class="form-row">
								<div class="col-lg-11">
									<div class="pull-right">
										<button class="btn btn-primary" type="submit" name="<%=serviciosVehiculoSeleccionado != null ? "Modificar" : "Guardar"%>">
											<i class="fa fa-save pr-2"></i>Guardar
										</button>
										<button class="btn btn-secondary" onclick="<% serviciosVehiculoSeleccionado = null;%>" type="submit" data-toggle="collapse" data-target="#Detalle" >
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
			<form action="AdministrarServiciosVehiculos" method="post">
			<!--  SECCION DE GRILLA  -->
			<div class="card" style="margin: 12px;">
				<div class="card-body">
					<h5 class="card-title">Registrados</h5>
					
					<div class="row">
			            	<div class="col-12 col-sm-12 col-lg-12">
			                	<div class="table-responsive">
		                    		<table class="table">
			                    		<thead>
			                    			<tr>
			                    				<th>ID</th>
			                    		    	<th>Fecha Realizacion</th>
			                    		    	<th>Servicio</th>
			                    		    	<th>Empleado</th>
			                    		    	<th>Vehiculo</th>
			                    		    	<th>Precio</th>
			                    		    	<th>Pagado</th>
			                    		    	<th></th>
			                    		    	<th>Pagar</th>
			                      			</tr>
			                      		</thead>
			                    		<tbody>
			                    		<% for (ServicioVehiculo servicioVehiculo: serviciosVehiculos) { %>
			                    			<tr>
			                    				<td><%=servicioVehiculo.getID()%></td>
			                    				<td><%=servicioVehiculo.getFechaRealizacion().toString()%></td>
			                    				<td><%=servicioVehiculo.getServicio().getDescripcion()%></td>
			                    				<td><%=servicioVehiculo.getEmpleado().toString()%></td>
			                    				<td><%=servicioVehiculo.getVehiculo().toString()%></td>
			                    				<td><%=servicioVehiculo.getFormattedPrecio() %></td>
			                    				<td><input type="checkbox" <%=servicioVehiculo.isPagado() ? "checked='checked'" : ""%> disabled></td>
			                    				<td>
			                    					<button class="btn btn-link-custom" type="submit"  name="BuscarID" value="<%=servicioVehiculo.getID()%>" <%=servicioVehiculo.isPagado() ? "disabled" : "" %>>
			                    						<i class="fa fa-pencil pr-2"></i>
			                    					</button>
			                    					<button class="btn btn-link" style="color: #dd4b39;" type="submit" name="Eliminar" value="<%=servicioVehiculo.getID() %>">
			                    						<i class="fa fa-trash pr-2"></i>
			                    					</button>
			                    				</td>
			                    				<td>
			                    					<button class="btn btn-link-custom" type="submit"  name="PagarID" value="<%=servicioVehiculo.getID()%>">
			                    						<i class="fa fa-credit-card pr-2"></i>Pagar
			                    					</button>
			                    				</td>
			                    			</tr>
			                    		<% } %>
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