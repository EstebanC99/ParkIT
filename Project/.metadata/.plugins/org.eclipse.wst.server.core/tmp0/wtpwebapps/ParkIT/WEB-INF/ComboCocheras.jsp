<%@page import="java.util.LinkedList"%>
<%@page import="entities.Cocheras.Cochera"%>
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
   	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
	
	<%
		LinkedList<Cochera> cocheras = request.getAttribute("ListaCocheras") != null ? (LinkedList<Cochera>)request.getAttribute("ListaCocheras") : new LinkedList<Cochera>();
		int cocheraSeleccionadaID =request.getAttribute("CocheraSeleccionadaID") != null ? (Integer)request.getAttribute("CocheraSeleccionadaID") : 0;
	%>
	<link rel="stylesheet" href="/dist/css/ajax-bootstrap-select.css"/>
	<script src="/dist/js/ajax-bootstrap-select.js"></script>
</head>
<body>
	<select class="form-control form-control-md selectpicker" name="CocheraID" id="selectVehiculos" data-live-search="true">
	<% for (Cochera cochera : cocheras) { %>
		<option value=<%=cochera.getID()%> <%= cocheraSeleccionadaID == cochera.getID() ? "selected" : "" %>><%=cochera.toString()%></option>
	<% } %>
	</select>
</body>
</html>