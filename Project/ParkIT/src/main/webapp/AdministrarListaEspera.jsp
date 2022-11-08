<%@page import="java.util.LinkedList"%>
<%@page import="entities.Alquileres.ListaEspera"%>
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
	<title>Lista Espera</title>
		
	<%
		LinkedList<ListaEspera> listasEspera = request.getAttribute("ListasEspera") != null ? (LinkedList<ListaEspera>)request.getAttribute("ListasEspera") : new LinkedList<ListaEspera>();
		ListaEspera listaEsperaSeleccionada = (ListaEspera)request.getAttribute("ListaEsperaSeleccionada");
	%>
</head>
<body>
	<jsp:include page ="WEB-INF/Navegacion.html"/>
	
	<div class="card" style="margin: 12px;">
		<h5 class="card-header" style="backgroud-color: aliceblue;">
			Lista de Espera
		</h5>
		<div class="card-body">
			<div class="col-md-6">
				<div class="text-danger">
					<%=(String)request.getAttribute("ErrorMessage") != null ? (String)request.getAttribute("ErrorMessage") : ""%>
				</div>
			</div>
			<form class="form" action="ListaEspera" method="post">
			
			<!-- SECCION DE ALTA Y MODIFICACION  -->
			<div class="card" style="margin: 12px;">
				<div class="card-header">
					<a data-toggle="collapse" data-target="#Detalle" style="text-decoration: none;">Detalle</a>
					<a data-toggle="collapse" data-target="#Detalle" class="pull-right" style="text-decoration: none;">
						<i class="fa fa-plus pr-2"></i>
					</a>
				</div>
				<div class="<%=listaEsperaSeleccionada != null ? "" : "collapse" %>" id="Detalle">
					<div class="card-body">
						<p class="card-title">Nuevo registro</p>
						
						<div class="form-group">
							<div class="form-row <%=listaEsperaSeleccionada != null ? "d-block" : "d-none" %>">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextFechaIngreso">Fecha Ingreso</span>
									</div>
									<input type="hidden" class="form-control" id="inputID" name="ID" value="<%=listaEsperaSeleccionada != null ? listaEsperaSeleccionada.getID() : 0%>">
									<input type="text" class="form-control" id="inputFechaIngresa" name="FechaIngreso" disabled value="<%=listaEsperaSeleccionada != null ? listaEsperaSeleccionada.getFechaIngreso() : ""%>">
									<div class="input-group-prepend">
										<span class="input-group-text" id="TextHoraIngreso">Hora Ingreso</span>
									</div>
									<input type="text" class="form-control" id="inputHoraIngreso" name="HoraIngreso" disabled value="<%=listaEsperaSeleccionada != null ? listaEsperaSeleccionada.getHoraIngreso() : ""%>">
								</div>
							</div>
							
							<div class="form-row">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="SelectCliente">Clientes</span>
									</div>
									<jsp:include page="WEB-INF/ComboClientes.jsp"></jsp:include>
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
										<button class="btn btn-primary" type="submit" name="<%=listaEsperaSeleccionada != null ? "Modificar" : "Guardar"%>">
											<i class="fa fa-save pr-2"></i>Guardar
										</button>
										<button class="btn btn-secondary" onclick="<% listaEsperaSeleccionada = null;%>" type="reset" data-toggle="collapse" data-target="#Detalle" >
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
			<form action="ListaEspera" method="post">
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
			                    		    	<th>Fecha</th>
			                    		    	<th>Hora</th>
			                    		    	<th>Cliente</th>
			                    		    	<th>Tipo Cochera</th>
			                    		    	<th></th>
			                      			</tr>
			                      		</thead>
			                    		<tbody>
			                    		<% for (ListaEspera registro: listasEspera) { %>
			                    			<tr>
			                    				<td><%=registro.getID()%></td>
			                    				<td><%=registro.getFechaIngreso().toString()%></td>
			                    				<td><%=registro.getHoraIngreso()%></td>
			                    				<td><%=registro.getCliente().toString()%></td>
			                    				<td><%=registro.getTipoCochera().getDescripcion()%></td>
			                    				<td>
			                    					<button class="btn btn-link-custom" type="submit"  name="BuscarID" value="<%=registro.getID()%>">
			                    						<i class="fa fa-pencil pr-2"></i>
			                    					</button>
			                    					<button class="btn btn-link" style="color: #dd4b39;" type="submit" name="Eliminar" value="<%=registro.getID() %>">
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