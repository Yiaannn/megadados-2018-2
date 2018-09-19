<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style> <%@include file="/mystyle.css"%> </style>
<title>Insert title here</title>
</head>
<body>

<%@ page import="java.util.*, std.*" %>  

<p>${message}</p>

<div class="boxwrapper">
	<div class="centerbox">
		<div class="centerboxchild">
			<details>
				<summary>Adicionar novo Monstro</summary>
				<div class="centerboxchild">
				<form id="newmonster" action='/Projeto1Megadados/Monsters' method='post' >
					<input type='text' name='newname' placeholder='ex.: Rathalos'><br>
				</form>
				</div>
				<div class="centerboxchild">
					<button type="submit" form="newmonster">Confirmar</button>
				</div>
			</details>
		</div>
	</div>

	<c:forEach items="${monsternames}" var="name">
		<div class="centerbox">
			<div class="centerboxchild">
				<h1><a href="/Projeto1Megadados/Monsters/${name}">${name}</a></h1>
			</div>
			<div class="centerboxchild">
				<form id="monsterdelete" action='/Projeto1Megadados/Monsters' method='post'>
					<input type="hidden" name="_method" value="DELETE">
				</form>
				<button type="submit" form="monsterdelete" name="deletetarget" value="${name}">Apagar</button>
			</div>
		</div>
	</c:forEach>
</div>

</body>
</html>