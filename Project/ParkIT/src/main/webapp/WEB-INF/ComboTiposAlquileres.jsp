<%@page import="java.util.LinkedList"%>
<%@page import="entities.Alquileres.TipoAlquiler"%>
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
	<%
		LinkedList<TipoAlquiler> tiposAlquileres = request.getAttribute("ListaTiposAlquileres") != null ? (LinkedList<TipoAlquiler>)request.getAttribute("ListaTiposAlquileres") : new LinkedList<TipoAlquiler>();
		int tipoAlquilerSeleccionadoID =request.getAttribute("TipoAlquilerSeleccionadoID") != null ? (Integer)request.getAttribute("TipoAlquilerSeleccionadoID") : 0;
	%>

</head>
<body>
	<select class="form-control form-control-md" name="TipoAlquilerID">
		<option value=0>Todos</option>
	<% for (TipoAlquiler tipoAlquiler : tiposAlquileres) { %>
		<option value=<%=tipoAlquiler.getID()%> <%= tipoAlquilerSeleccionadoID == tipoAlquiler.getID() ? "selected" : "" %>><%=tipoAlquiler.getDescripcion()%></option>
	<% } %>
	</select>
</body>
</html>