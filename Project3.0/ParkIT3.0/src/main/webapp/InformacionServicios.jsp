<%@page import="java.util.LinkedList"%>
<%@page import="entities.Servicios.Servicio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		
	<%LinkedList<Servicio> servicios = request.getAttribute("ListaServicios") != null ? (LinkedList<Servicio>) request.getAttribute("ListaServicios") : new LinkedList<Servicio>();%>
	
</head>
<body>
	<jsp:include page="WEB-INF/NavegacionCliente.jsp" />

	<!-- CARD DE ALQUILER  -->
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
												<th>Tipo</th>
												<th>Precio</th>
											</tr>
										</thead>
										<tbody>
											<%
											for (Servicio servicio : servicios) {
											%>
											<tr>
												<td><%=servicio.getDescripcion()%></td>
												<td><%=servicio.getPrecio()%></td>
											</tr>
											<%
											}
											%>
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
