<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style> <%@include file="/mystyle.css"%> </style>
<title>Insert title here</title>
</head>
<body>

<%@ page import="java.util.*, std.*" %>  

<p>${message}</p>

<form id="voltar" action='/Projeto1Megadados/Monsters' method='get'></form>

<div class="boxwrapper">
	<div class="centerbox">
		<div class="centerboxchild">
			<button type="submit" form='voltar'>Voltar</button>
		</div>
	</div>
</div>

</body>
</html>