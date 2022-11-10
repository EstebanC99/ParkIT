<%@page import="entities.Alquileres.FormaPago"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Administrar Formas de Pago</title>

<link rel="icon" href="http://getboostrap.com/favicon.ico">

<%
LinkedList<FormaPago> formasPago = request.getAttribute("ListaFormasPago") != null ? (LinkedList<FormaPago>) request.getAttribute("ListaFormasPago") : new LinkedList<FormaPago>();
FormaPago formaPagoSeleccionada = (FormaPago) request.getAttribute("FormaPagoSeleccionada");
%>
</head>
<body>
	<jsp:include page="WEB-INF/Navegacion.html" />

	<div class="card" style="margin: 12px;">
		<h5 class="card-header" style="backgroud-color: aliceblue;">
			Formas de Pago</h5>
			
		<div class="card-body">
			<div class="col-md-6">
				<div class="text-danger">
					<%=(String)request.getAttribute("ErrorMessage") != null ? (String)request.getAttribute("ErrorMessage") : ""%>
				</div>
			</div>
		</div>
			
		<div class="card-body">
			<form class="form" action="AdministrarFormaPago" method="post">

				<!-- SECCION DE ALTA Y MODIFICACION  -->
				<div class="card" style="margin: 12px;">
					<div class="card-header">
						<a data-toggle="collapse" data-target="#Detalle"
							style="text-decoration: none;">Detalle</a> <a
							data-toggle="collapse" data-target="#Detalle" class="pull-right"
							style="text-decoration: none;"> <i class="fa fa-plus pr-2"></i>
						</a>
					</div>
					<div class="<%=formaPagoSeleccionada != null ? "" : "collapse"%>"
						id="Detalle">
						<div class="card-body">
							<p class="card-title">Forma de Pago</p>

							<div class="form-group">
								<div class="form-row">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="TextoDescripcion">Descripcion</span>
										</div>
										<input type="hidden" class="form-control" id="inputID"
											name="ID"
											value="<%=formaPagoSeleccionada != null ? formaPagoSeleccionada.getID() : 0%>">
										<input type="text" class="form-control" id="inputDescripcion"
											name="Descripcion" placeholder="Ingrese Descripcion"
											required="" autofocus=""
											value="<%=formaPagoSeleccionada != null ? formaPagoSeleccionada.getDescripcion() : ""%>">
									</div>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="TextoDescuento">Descuento</span>
										</div>
										<input type="text" class="form-control" id="inputDescuento"
											name="Descuento" placeholder="Ingrese Descuento" required=""
											autofocus=""
											value="<%=formaPagoSeleccionada != null ? formaPagoSeleccionada.getDescuento() : ""%>">
									</div>
									
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="TextoInremento">Incremento</span>
										</div>
										<input type="text" class="form-control" id="inputIncremento"
											name="Incremento" placeholder="Ingrese Incremento" required=""
											autofocus=""
											value="<%=formaPagoSeleccionada != null ? formaPagoSeleccionada.getIncremento() : ""%>">
									</div>
								</div>

								<div class="form-row">
									<div class="col-lg-11">
										<div class="pull-right">
											<button class="btn btn-primary" type="submit"
												name="<%=formaPagoSeleccionada != null ? "Modificar" : "Guardar"%>">
												<i class="fa fa-save pr-2"></i>Guardar
											</button>
											<button class="btn btn-secondary"
												onclick="<%formaPagoSeleccionada = null;%>" type="submit"
												data-toggle="collapse" data-target="#Detalle">
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
			<form action="AdministrarFormaPago" method="post">
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
												<th>Tipo</th>
												<th>Descuento</th>
												<th>Incremento</th>
											</tr>
										</thead>
										<tbody>
											<%
											for (FormaPago formaPago : formasPago) {
											%>
											<tr>
												<td><%=formaPago.getID()%></td>
												<td><%=formaPago.getDescripcion()%></td>
												<td><%=formaPago.getDescuento()%></td>
												<td><%=formaPago.getIncremento()%></td>
												<td>
													<button class="btn btn-link-custom" type="submit"
														name="BuscarID" value="<%=formaPago.getID()%>">
														<i class="fa fa-pencil pr-2"></i>
													</button>
													<button class="btn btn-link" style="color: #dd4b39;"
														type="submit" name="Eliminar"
														value="<%=formaPago.getID()%>">
														<i class="fa fa-trash pr-2"></i>
													</button>
												</td>
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
			</form>



		</div>
	</div>
</body>
</html>