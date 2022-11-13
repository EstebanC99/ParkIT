<%@page import="java.util.LinkedList"%>
<%@page import="entities.Personas.Empleado"%>
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
		LinkedList<Empleado> empleados = request.getAttribute("ListaEmpleados") != null ? (LinkedList<Empleado>)request.getAttribute("ListaEmpleados") : new LinkedList<Empleado>();
		int empleadoSeleccionadoID = request.getAttribute("EmpleadoSeleccionadoID") != null ? (Integer)request.getAttribute("EmpleadoSeleccionadoID") : 0;
	%>

</head>
<body>
	<select class="form-control form-control-md" name="EmpleadoID">
	<% for (Empleado empleado : empleados) { %>
		<option value="<%=empleado.getID()%>"<%= empleadoSeleccionadoID == empleado.getID() ? "selected" : "" %>><%=empleado.toString()%></option>
	<% } %>
	</select>
</body>
</html>