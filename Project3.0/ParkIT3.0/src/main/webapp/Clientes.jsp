<%@page import="java.util.LinkedList"%>
<%@page import="entities.Personas.Cliente"%>
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
    
	<title>Administrar Clientes</title>
		
	<%
		LinkedList<Cliente> clientes = request.getAttribute("ListaClientes") != null ? (LinkedList<Cliente>)request.getAttribute("ListaClientes") : new LinkedList<Cliente>();
		Cliente clienteSeleccionado = (Cliente)request.getAttribute("ClienteSeleccionado");
	%>
	<script>
		$(document).ready(function(){
		  $("#inputBuscar").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#tableClientes tr").filter(function() {
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
			Cliente
		</h5>
		<div class="card-body">
			<div class="col-md-6">
				<div class="text-danger">
					<%=(String)request.getAttribute("ErrorMessage") != null ? (String)request.getAttribute("ErrorMessage") : ""%>
				</div>
			</div>
			<form class="form" action="Cliente" method="post">
			
			<!-- SECCION DE ALTA Y MODIFICACION  -->
			<div class="card" style="margin: 12px;">
				<div class="card-header">
					<a data-toggle="collapse" data-target="#Detalle" style="text-decoration: none;">Detalle</a>
					<a data-toggle="collapse" data-target="#Detalle" class="pull-right" style="text-decoration: none;">
						<i class="fa fa-plus pr-2"></i>
					</a>
				</div>
				<div class="<%=clienteSeleccionado != null ? "" : "collapse" %>" id="Detalle">
					<div class="card-body">
						<p class="card-title">Cliente</p>
						
						<div class="form-group">
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextNombre">Nombre</span>
									</div>
									<input type="hidden" class="form-control" id="inputID" name="ID" value="<%=clienteSeleccionado != null ? clienteSeleccionado.getID() : 0%>">
									<input type="text" class="form-control" id="inputNombre" name="Nombre" placeholder="Mi Nombre" required="true" value="<%=clienteSeleccionado != null ? clienteSeleccionado.getNombre() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextApellido">Apellido</span>
									</div>
									<input type="text" class="form-control" id="inputApellido" name="Apellido" placeholder="Mi Apellido" required="true" value="<%=clienteSeleccionado != null ? clienteSeleccionado.getApellido() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextDNI">DNI</span>
									</div>
									<input type="text" class="form-control" id="inputDNI" name="DNI" placeholder="41567829" required="true" value="<%=clienteSeleccionado != null ? clienteSeleccionado.getDNI() : ""%>">
								</div>
							</div>
								
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoEmail">Email</span>
									</div>
									<input type="text" class="form-control" id="inputEmail" name="Email" placeholder="Ex: miMail@dominio.com." value="<%=clienteSeleccionado != null ? clienteSeleccionado.getEmail() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoTelefono">Telefono</span>
									</div>
									<input type="text" class="form-control" id="inputTelefono" name="Telefono" placeholder="Ex: 5493364562256" value="<%=clienteSeleccionado != null ? clienteSeleccionado.getTelefono() : ""%>">
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoDireccion">Direccion</span>
									</div>
									<input type="text" class="form-control" id="inputDireccion" name="Direccion" placeholder="Ex: Mi calle 300. Piso 1, Dpto A." value="<%=clienteSeleccionado != null ? clienteSeleccionado.getDireccion() : ""%>">
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoNombreUsuario">Nombre de Usuario</span>
									</div>
									<input type="text" class="form-control" id="inputEmail" name="NombreUsuario" placeholder="Ex: miusuario" value="<%=clienteSeleccionado != null ? clienteSeleccionado.getUser().getNombreUsuario() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextPassword">Password</span>
									</div>
									<input type="password" class="form-control" id="inputPassword" name="Password" placeholder="Ex: ******" value="<%=clienteSeleccionado != null ? clienteSeleccionado.getUser().getPassword() : ""%>">
								</div>
							</div>
							
							<div class="form-row">
								<div class="col-lg-11">
									<div class="pull-right">
										<button class="btn btn-primary" type="submit" name="<%=clienteSeleccionado != null ? "Modificar" : "Guardar"%>">
											<i class="fa fa-save pr-2"></i>Guardar
										</button>
										<button class="btn btn-secondary" onclick="<% clienteSeleccionado = null;%>" type="submit" data-toggle="collapse" data-target="#Detalle" >
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
							<form action="Cliente" method="post">
			                	<div class="table-responsive">
		                    		<table class="table" id="tableClientes">
			                    		<thead>
			                    			<tr>
			                    				<th>ID</th>
			                    		    	<th>Nombre</th>
			                    		    	<th>Apellido</th>
			                    		    	<th>DNI</th>
			                    		    	<th>Email</th>
			                    				<th>Telefono</th>
			                    		    	<th>Direccion</th>
			                    		    	<th>Usuario</th>
			                    		    	<th></th>
			                      			</tr>
			                      		</thead>
			                    		<tbody>
				                    		<% for (Cliente cliente: clientes) { %>
				                    			<tr>
				                    				<td><%=cliente.getID()%></td>
				                    				<td><%=cliente.getNombre()%></td>
				                    				<td><%=cliente.getApellido()%></td>
				                    				<td><%=cliente.getDNI()%></td>
				                    				<td><%=cliente.getEmail()%></td>
				                    				<td><%=cliente.getTelefono()%></td>
				                    				<td><%=cliente.getDireccion()%></td>
				                    				<td><%=cliente.getUser().getNombreUsuario()%></td>
				                    				<td>
				                    					<button class="btn btn-link-custom" type="submit"  name="BuscarID" value="<%=cliente.getID()%>">
				                    						<i class="fa fa-pencil pr-2"></i>
				                    					</button>
				                    					<button class="btn btn-link" style="color: #dd4b39;" type="submit" name="Eliminar" value="<%=cliente.getID() %>">
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