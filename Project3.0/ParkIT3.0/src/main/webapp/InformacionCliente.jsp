<%@page import="java.util.LinkedList"%>
<%@page import="entities.Servicios.ServicioVehiculo"%>
<%@page import="entities.Alquileres.Alquiler"%>
<%@page import="entities.Usuarios.Usuario"%>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Informacion del Cliente</title>
	<%LinkedList<ServicioVehiculo> serviciosVehiculos = request.getAttribute("ListaServiciosVehiculos") != null ? (LinkedList<ServicioVehiculo>)request.getAttribute("ListaServiciosVehiculos") : new LinkedList<ServicioVehiculo>();%>
	<% LinkedList<Alquiler> alquileres = request.getAttribute("ListaAlquileres") != null ? (LinkedList<Alquiler>)request.getAttribute("ListaAlquileres") : new LinkedList<Alquiler>();%>
</head>
</head>

<body>
	<jsp:include page="WEB-INF/NavegacionCliente.jsp" />


	<!-- CARD DE ALQUILER  -->
	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionAlquileres"
				style="text-decoration: none;"><h5 class="card-title">Alquileres</h5></a>
		</div>

	<!--  SECCION DE GRILLA  -->
		<div class="card" style="margin: 12px;">
			<div class="card-body">
				<div class="row">
		            	<div class="col-12 col-sm-12 col-lg-12">
		                	<div class="table-responsive">
	                    		<table class="table">
		                    		<thead>
		                    			<tr>
		                    				<th>Patente</th>
		                    				<th>Nro. Cochera</th>
		                    		    	<th>Fecha Hora Inicio</th>
		                    		    	<th>Fecha Hora Fin</th>
		                    		    	<th>Tipo Alquiler</th>
		                    		    	<th>Forma Pago</th>
		                    		    	<th>Precio</th>
		                    		    	<th>Pagado</th>
		                    		    	
		                      			</tr>
		                      		</thead>
		                    		<tbody>
		                    		<% for (Alquiler alquiler: alquileres) { %>
		                    			<tr>
		                    				<td><%=alquiler.getVehiculo().getPatente()%></td>
		                    				<td><%=alquiler.getCochera().getNroCochera()%></td>
		                    				<td><%=alquiler.getFechaHoraInicio().toString()%></td>
		                    				<td><%=alquiler.getFechaHoraFin().toString()%></td>
		                    				<td><%=alquiler.getTipoAlquiler().getDescripcion()%></td>
		                    				<td><%=alquiler.getFormaPago().getDescripcion()%></td>
											<td><%=alquiler.getTotalPrecio()%></td>
											<td><%=alquiler.isPagado() ? "Si" : "No"%></td>
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
	</div>
	<!-- FIN DE CARD ALQUILER -->

	
	<!-- CARD DE SERVICIOS -->
	<div class="card" style="margin: 12px;">
		<div class="card-header">
			<a data-toggle="collapse" data-target="#SeccionServicios"
				style="text-decoration: none;"><h5 class="card-title">Servicios</h5></a>
		</div>
		
		

	<!--  SECCION DE GRILLA  -->
			<div class="card" style="margin: 12px;">
				<div class="card-body">
					<div class="row">
			            	<div class="col-12 col-sm-12 col-lg-12">
			                	<div class="table-responsive">
		                    		<table class="table">
			                    		<thead>
			                    			<tr>
			                    		    	<th>Fecha Realizacion</th>
			                    		    	<th>Servicio</th>
			                    		    	<th>Patente</th>
			                    		    	<th>Vehiculo</th>
			                    		    	<th>Precio</th>
			                    		    	<th>Pagado</th>
			                      			</tr>
			                      		</thead>
			                    		<tbody>
			                    		<% for (ServicioVehiculo servicioVehiculo: serviciosVehiculos) { %>
			                    			<tr>
			                    				<td><%=servicioVehiculo.getFechaRealizacion().format(DateTimeFormatter.ofPattern("dd-MM-uuuu"))%></td>
			                    				<td><%=servicioVehiculo.getServicio().getDescripcion()%></td>
			                    				<td><%=servicioVehiculo.getVehiculo().getPatente()%></td>
			                    				<td><%=servicioVehiculo.getVehiculo().getMarca() + " - " + servicioVehiculo.getVehiculo().getModelo() %></td>
			                    				<td><%=servicioVehiculo.getFormattedPrecio() %></td>
			                    				<td><%=servicioVehiculo.isPagado() ? "Si" : "No" %></td>
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
	</div>
	<!-- FIN DE CARD DE SERVICIOS -->
</body>
</html>