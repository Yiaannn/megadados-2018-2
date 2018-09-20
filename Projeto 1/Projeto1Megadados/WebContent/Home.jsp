<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style> <%@include file="/mystyle.css"%> </style>
<title>Insert title here</title>
</head>
<body>

<form id="monsters" action='/Projeto1Megadados/Monsters' method='get'></form>
<form id="signin" action='/Projeto1Megadados/Admin' method='get'></form>

<p>${message}</p>

<div class="boxwrapper">
	<div class="centerbox">
		<div class="centerboxchild">
			<h1>Projeto Megadados</h1>
		</div>
		
		<div class="centerboxchild">
			<button type="submit" form='monsters'>Lista de Monstros</button>
			<button type="submit" form="signin">Acessar como Administrador</button>
		</div>
	</div>
</div>

</body>
</html>