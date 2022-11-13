<%@page import="java.util.LinkedList"%>
<%@page import="entities.Personas.Empleado"%>
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
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    
	<title>Administrar Empleados</title>
		
	<%
		LinkedList<Empleado> empleados = request.getAttribute("ListaEmpleados") != null ? (LinkedList<Empleado>)request.getAttribute("ListaEmpleados") : new LinkedList<Empleado>();
		Empleado empleadoSeleccionado = (Empleado)request.getAttribute("EmpleadoSeleccionado");
	%>
	
	<script>
		$(document).ready(function(){
		  $("#inputBuscar").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#tableEmpleados tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
	</script>
</head>
<body>
	<jsp:include page ="WEB-INF/Navegacion.jsp"/>
	
	<div class="card" style="margin: 12px;">
		<h5 class="card-header" style="backgroud-color: aliceblue;">
			Empleados
		</h5>
		<div class="card-body">
			<div class="col-md-6">
				<div class="text-danger">
					<%=(String)request.getAttribute("ErrorMessage") != null ? (String)request.getAttribute("ErrorMessage") : ""%>
				</div>
			</div>
			<form class="form" action="Empleado" method="post">
			
			<!-- SECCION DE ALTA Y MODIFICACION  -->
			<div class="card" style="margin: 12px;">
				<div class="card-header">
					<a data-toggle="collapse" data-target="#Detalle" style="text-decoration: none;">Detalle</a>
					<a data-toggle="collapse" data-target="#Detalle" class="pull-right" style="text-decoration: none;">
						<i class="fa fa-plus pr-2"></i>
					</a>
				</div>
				<div class="<%=empleadoSeleccionado != null ? "" : "collapse" %>" id="Detalle">
					<div class="card-body">
						<p class="card-title">Empleado</p>
						
						<div class="form-group">
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextNombre">Nombre</span>
									</div>
									<input type="hidden" class="form-control" id="inputID" name="ID" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getID() : 0%>">
									<input type="text" class="form-control" id="inputNombre" name="Nombre" placeholder="Mi Nombre" required="true" autofocus="" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getNombre() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextApellido">Apellido</span>
									</div>
									<input type="text" class="form-control" id="inputApellido" name="Apellido" placeholder="Mi Apellido" required="true" autofocus="" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getApellido() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextDNI">DNI</span>
									</div>
									<input type="text" class="form-control" id="inputDNI" name="DNI" placeholder="41567829" required="true" autofocus="" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getDNI() : ""%>">
								</div>
							</div>
								
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoEmail">Email</span>
									</div>
									<input type="text" class="form-control" id="inputEmail" name="Email" placeholder="Ex: miMail@dominio.com." autofocus="" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getEmail() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoTelefono">Telefono</span>
									</div>
									<input type="text" class="form-control" id="inputTelefono" name="Telefono" placeholder="Ex: 5493364562256" autofocus="" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getTelefono() : ""%>">
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoDireccion">Direccion</span>
									</div>
									<input type="text" class="form-control" id="inputDireccion" name="Direccion" placeholder="Ex: Mi calle 300. Piso 1, Dpto A." autofocus="" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getDireccion() : ""%>">
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoFechaNacimiento">Fecha Nacimiento</span>
									</div>
									<input type="date" class="form-control" id="inputFechaNacimiento" name="FechaNacimiento" required="true" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getFechaNacimiento().toString() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoCuit">CUIT</span>
									</div>
									<input type="text" class="form-control" id="inputCuit" name="Cuit" placeholder="Ex: 20415678291" required="true" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getCuit() : ""%>">
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
											<span class="input-group-text" id="TextoNombreUsuario">Nombre de Usuario</span>
										</div>
										<input type="text" class="form-control" id="inputEmail" name="NombreUsuario" placeholder="Ex: miusuario" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getUser().getNombreUsuario() : ""%>">
										<div class="input-group-prepend">
											<span class="input-group-text" id="TextPassword">Password</span>
										</div>
										<input type="password" class="form-control" id="inputPassword" name="Password" placeholder="Ex: ******" value="<%=empleadoSeleccionado != null ? empleadoSeleccionado.getUser().getPassword() : ""%>">
									</div>
								</div>
							</div>
							
							<div class="form-row">
								<div class="col-lg-11">
									<div class="pull-right">
										<button class="btn btn-primary" type="submit" name="<%=empleadoSeleccionado != null ? "Modificar" : "Guardar"%>">
											<i class="fa fa-save pr-2"></i>Guardar
										</button>
										<button class="btn btn-secondary" onclick="<% empleadoSeleccionado = null;%>" type="submit" data-toggle="collapse" data-target="#Detalle" >
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
			<!--  SECCION DE GRILLA  -->
			<div class="card" style="margin: 12px;">
				<div class="card-body">
					<h5 class="card-title">Registrados</h5>
					
					<div class="row">
			            <div class="col-12 col-sm-12 col-lg-12">
							<input style="margin-bottom:12px;" type="text" class="form-control" id="inputBuscar" name="Buscar" autocomplete="off" placeholder="Buscar Cliente" autofocus="">
							<form action="Empleado" method="post">
			                	<div class="table-responsive">
		                    		<table class="table" id="tableEmpleados">
			                    		<thead>
			                    			<tr>
			                    				<th>ID</th>
			                    		    	<th>Nombre</th>
			                    		    	<th>Apellido</th>
			                    		    	<th>DNI</th>
			                    		    	<th>Email</th>
			                    				<th>Telefono</th>
			                    		    	<th>Direccion</th>
			                    		    	<th>Fecha Nacimiento</th>
			                    		    	<th>Cuit</th>
			                    		    	<th>Usuario</th>
			                    		    	<th></th>
			                      			</tr>
			                      		</thead>
			                    		<tbody>
				                    		<% for (Empleado empleado: empleados) { %>
				                    			<tr>
				                    				<td><%=empleado.getID()%></td>
				                    				<td><%=empleado.getNombre()%></td>
				                    				<td><%=empleado.getApellido()%></td>
				                    				<td><%=empleado.getDNI()%></td>
				                    				<td><%=empleado.getEmail()%></td>
				                    				<td><%=empleado.getTelefono()%></td>
				                    				<td><%=empleado.getDireccion()%></td>
				                    				<td><%=empleado.getFechaNacimiento().toString()%></td>
				                    				<td><%=empleado.getCuit()%></td>
				                    				<td><%=empleado.getUser().getNombreUsuario()%></td>
				                    				<td>
				                    					<button class="btn btn-link-custom" type="submit"  name="BuscarID" value="<%=empleado.getID()%>">
				                    						<i class="fa fa-pencil pr-2"></i>
				                    					</button>
				                    					<button class="btn btn-link" style="color: #dd4b39;" type="submit" name="Eliminar" value="<%=empleado.getID() %>">
				                    						<i class="fa fa-trash pr-2"></i>
				                    					</button>
				                    				</td>
				                    			</tr>
				                    		<% } %>
		                    			</tbody>
		                   			</table>
		               			</div>
							</form>
		          		</div>	
		          	</div>
				</div>
			</div>
			<!--  FIN DE GRILLA  -->


			
		</div>
	</div>

</body>
</html>