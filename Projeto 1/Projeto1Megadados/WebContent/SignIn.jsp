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

<form id="voltar" action='/Projeto1Megadados/' method='get'></form>

<p>${message}</p>

<div class="boxwrapper">
	<div class="centerbox">
		<div class="centerboxchild">
			<h1>Acessar Perfil</h1>
		</div>
		
		<div class="centerboxchild">
			<form id="signup" action='/Projeto1Megadados/Admin' method='post' >
				<input type='text' name='login' placeholder='login'><br>
				<input type='password' name='pass' placeholder='senha'><br>
			</form>
		</div>
		
		<div class="centerboxchild">
			<button type="submit" form='voltar'>Voltar</button>
			<button type="submit" form="signup">Confirmar</button>
		</div>
	</div>
</div>

</body>
</html>