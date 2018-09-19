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
	<c:forEach items="${monsternames}" var="name">
		<div class="centerbox">
			<div class="centerboxchild">
				<h1><a href="/Projeto1Megadados/Monsters/${name}">${name}</a></h1>
			</div>
		</div>
	</c:forEach>
</div>

</body>
</html>