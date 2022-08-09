<%@page import="entities.Vehiculos.TipoVehiculo"%>
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
	<title>Administrar Tipo Vehiculo</title>
	
	<link href="styles/css/bootstrap.css" rel="stylesheet">

</head>
<body>
	
	<form class="form" action="AdministrarTipoVehiculo" method="post">
		<label for="inputDescripcion" class="sr-only">Descripcion</label>
		<input id="inputDescripcion" name="Descripcion" class="form-control" placeholder="Ingrese Descripcion" required="" autofocus="" type="text">
		<button class="btn btn-lg btn-primary btn-block" type="submit">Registrar</button>
	</form>

</body>
</html>