<%@page import="java.util.LinkedList"%>
<%@page import="entities.Cocheras.Cochera"%>
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
	<title>Administrar Cocheras</title>
		
	<%
		LinkedList<Cochera> cocheras = request.getAttribute("ListaCocheras") != null ? (LinkedList<Cochera>)request.getAttribute("ListaCocheras") : new LinkedList<Cochera>();
		Cochera cocheraSeleccionada = (Cochera)request.getAttribute("CocheraSeleccionada");
	%>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
		  $("#inputBuscar").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#tableCocheras tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
	</script>
</head>
<body>
	<jsp:include page ="WEB-INF/Navegacion.html"/>
	
	<div class="card" style="margin: 12px;">
		<h5 class="card-header" style="backgroud-color: aliceblue;">
			Cocheras del lugar
		</h5>
		<div class="card-body">
			<div class="col-md-6">
				<div class="text-danger">
					<%=(String)request.getAttribute("ErrorMessage") != null ? (String)request.getAttribute("ErrorMessage") : ""%>
				</div>
			</div>
			<form class="form" action="Cochera" method="post">
			
			<!-- SECCION DE ALTA Y MODIFICACION  -->
			<div class="card" style="margin: 12px;">
				<div class="card-header">
					<a data-toggle="collapse" data-target="#Detalle" style="text-decoration: none;">Detalle</a>
					<a data-toggle="collapse" data-target="#Detalle" class="pull-right" style="text-decoration: none;">
						<i class="fa fa-plus pr-2"></i>
					</a>
				</div>
				<div class="<%=cocheraSeleccionada != null ? "" : "collapse" %>" id="Detalle">
					<div class="card-body">
						<p class="card-title">Cochera</p>
						
						<div class="form-group">
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextNroCochera">Numero de Cochera</span>
									</div>
									<input type="hidden" class="form-control" id="inputID" name="ID" value="<%=cocheraSeleccionada != null ? cocheraSeleccionada.getID() : 0%>">
									<input type="text" class="form-control" id="inputNroCochera" name="NroCochera" placeholder="Ex: 1, 2, ..., n" required="true" autofocus="" value="<%=cocheraSeleccionada != null ? cocheraSeleccionada.getNroCochera() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextDisponible">Disponible</span>
									</div>
									<input type="checkbox" class="form-control col-sm-2" id="inputDisponible" name="Disponible" <%=cocheraSeleccionada != null && cocheraSeleccionada.isDisponible() ? "checked='checked'" : ""%> value="True">
								</div>
							</div>
								
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextoFecha">Ubicacion</span>
									</div>
									<input type="text" class="form-control" id="inputUbicacion" name="Ubicacion" placeholder="Ex: 1er Piso, Centro." required="true" autofocus="" value="<%=cocheraSeleccionada != null ? cocheraSeleccionada.getUbicacion() : ""%>">
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="SelectTipoAlquiler">Tipo Cochera</span>
									</div>
									<jsp:include page="WEB-INF/ComboTiposCocheras.jsp"></jsp:include>
								</div>
							</div>
							
							<div class="form-row">
								<div class="col-lg-11">
									<div class="pull-right">
										<button class="btn btn-primary" type="submit" name="<%=cocheraSeleccionada != null ? "Modificar" : "Guardar"%>">
											<i class="fa fa-save pr-2"></i>Guardar
										</button>
										<button class="btn btn-secondary" onclick="<% cocheraSeleccionada = null;%>" type="submit" data-toggle="collapse" data-target="#Detalle" >
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
					<h5 class="card-title">Registradas</h5>
					
					<div class="row">
			            <div class="col-12 col-sm-12 col-lg-12">
			            	<input style="margin-bottom:12px;" type="text" class="form-control" id="inputBuscar" name="Buscar" autocomplete="off" placeholder="Buscar Cochera" autofocus="">
							<form action="Cochera" method="post">
			                	<div class="table-responsive">
		                    		<table id="tableCocheras" class="table">
			                    		<thead>
			                    			<tr>
			                    				<th>ID</th>
			                    		    	<th>Numero Cochera</th>
			                    		    	<th>Ubicacion</th>
			                    		    	<th>Disponible</th>
			                    		    	<th>Tipo Cochera</th>
			                    		    	<th></th>
			                      			</tr>
			                      		</thead>
			                    		<tbody>
			                    		<% for (Cochera cochera: cocheras) { %>
			                    			<tr>
			                    				<td><%=cochera.getID()%></td>
			                    				<td><%=cochera.getNroCochera()%></td>
			                    				<td><%=cochera.getUbicacion()%></td>
			                    				<td><input type="checkbox" <%=cochera.isDisponible() ? "checked='checked'" : ""%> disabled></td>
			                    				<td><%=cochera.getTipoCochera().getDescripcion()%></td>
			                    				<td>
			                    					<button class="btn btn-link-custom" type="submit"  name="BuscarID" value="<%=cochera.getID()%>">
			                    						<i class="fa fa-pencil pr-2"></i>
			                    					</button>
			                    					<button class="btn btn-link" style="color: #dd4b39;" type="submit" name="Eliminar" value="<%=cochera.getID() %>">
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