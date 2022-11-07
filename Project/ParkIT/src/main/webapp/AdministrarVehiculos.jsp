<%@page import="java.util.LinkedList"%>
<%@page import="entities.Vehiculos.Vehiculo"%>
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
	<title>Administrar Vehiculos</title>
		
	<%
		LinkedList<Vehiculo> vehiculos = request.getAttribute("ListaVehiculos") != null ? (LinkedList<Vehiculo>)request.getAttribute("ListaVehiculos") : new LinkedList<Vehiculo>();
		Vehiculo vehiculoSeleccionado = (Vehiculo)request.getAttribute("VehiculoSeleccionado");
	%>
</head>
<body>
	<jsp:include page ="WEB-INF/Navegacion.html"/>
	
	<div class="card" style="margin: 12px;">
		<h5 class="card-header" style="backgroud-color: aliceblue;">
			Vehiculos registrados
		</h5>
		<div class="card-body">
			<form class="form" action="Vehiculo" method="post">
			
			<!-- SECCION DE ALTA Y MODIFICACION  -->
			<div class="card" style="margin: 12px;">
				<div class="card-header">
					<a data-toggle="collapse" data-target="#Detalle" style="text-decoration: none;">Detalle</a>
					<a data-toggle="collapse" data-target="#Detalle" class="pull-right" style="text-decoration: none;">
						<i class="fa fa-plus pr-2"></i>
					</a>
				</div>
				<div class="<%=vehiculoSeleccionado != null ? "" : "collapse" %>" id="Detalle">
					<div class="card-body">
						<p class="card-title">Vehiculo</p>
						
						<div class="form-group">
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextPatente">Patente</span>
									</div>
									<input type="hidden" class="form-control" id="inputID" name="ID" value="<%=vehiculoSeleccionado != null ? vehiculoSeleccionado.getID() : 0%>">
									<input type="text" class="form-control" id="inputPatente" name="Patente" placeholder="Ex: ABC123" required="true" autofocus="" value="<%=vehiculoSeleccionado != null ? vehiculoSeleccionado.getPatente() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextMarca">Marca</span>
									</div>
									<input type="text" class="form-control" id="inputMarca" name="Marca" placeholder="Ex: Ford" required="true" autofocus="" value="<%=vehiculoSeleccionado != null ? vehiculoSeleccionado.getMarca() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextModelo">Modelo</span>
									</div>
									<input type="text" class="form-control" id="inputModelo" name="Modelo" placeholder="Ex: Focus" required="true" autofocus="" value="<%=vehiculoSeleccionado != null ? vehiculoSeleccionado.getModelo() : ""%>">
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="SelectTipoVehiculo">Tipo Vehiculo</span>
									</div>
									<jsp:include page="WEB-INF/ComboTiposVehiculos.jsp"></jsp:include>
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="SelectCliente">Cliente</span>
									</div>
									<jsp:include page="WEB-INF/ComboClientes.jsp"></jsp:include>
								</div>
							</div>
							
							<div class="form-row">
								<div class="col-lg-11">
									<div class="pull-right">
										<button class="btn btn-primary" type="submit" name="<%=vehiculoSeleccionado != null ? "Modificar" : "Guardar"%>">
											<i class="fa fa-save pr-2"></i>Guardar
										</button>
										<button class="btn btn-secondary" onclick="<% vehiculoSeleccionado = null;%>" type="reset" data-toggle="collapse" data-target="#Detalle" >
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
			<form action="Vehiculo" method="post">
			<!--  SECCION DE GRILLA  -->
			<div class="card" style="margin: 12px;">
				<div class="card-body">
					<h5 class="card-title">Registradas</h5>
					
					<div class="row">
			            	<div class="col-12 col-sm-12 col-lg-12">
			                	<div class="table-responsive">
		                    		<table class="table">
			                    		<thead>
			                    			<tr>
			                    				<th>ID</th>
			                    		    	<th>Patente</th>
			                    		    	<th>Marca</th>
			                    		    	<th>Modelo</th>
			                    		    	<th>Tipo Vehiculo</th>
			                    		    	<th>Cliente</th>
			                    		    	<th></th>
			                      			</tr>
			                      		</thead>
			                    		<tbody>
			                    		<% for (Vehiculo vehiculo: vehiculos) { %>
			                    			<tr>
			                    				<td><%=vehiculo.getID()%></td>
			                    				<td><%=vehiculo.getPatente()%></td>
			                    				<td><%=vehiculo.getMarca()%></td>
			                    				<td><%=vehiculo.getModelo()%></td>
			                    				<td><%=vehiculo.getTipoVehiculo().getDescripcion()%></td>
			                    				<td><%=vehiculo.getCliente().toString()%></td>
			                    				<td>
			                    					<button class="btn btn-link-custom" type="submit"  name="BuscarID" value="<%=vehiculo.getID()%>">
			                    						<i class="fa fa-pencil pr-2"></i>
			                    					</button>
			                    					<button class="btn btn-link" style="color: #dd4b39;" type="submit" name="Eliminar" value="<%=vehiculo.getID() %>">
			                    						<i class="fa fa-trash pr-2"></i>
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