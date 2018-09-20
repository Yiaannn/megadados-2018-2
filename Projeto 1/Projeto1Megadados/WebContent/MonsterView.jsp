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

<div class="boxwrapper">
	<div class="centerbox">
		<div class="centerboxchild">
			<h1>${monster.name} - Dados Gerais</h1>
		</div>
		
		<div class="centerboxchild">
			${monster.subspeciesOf}
		</div>
		
		<div class="centerboxchild">
			<h2>${monster.poisonVulnerability}</h2>
		</div>
		<div class="centerboxchild">
			<h2>${monster.sleepVulnerability}</h2>
		</div>
		<div class="centerboxchild">
			<h2>${monster.paralysisVulnerability}</h2>
		</div>
		<div class="centerboxchild">
			<h2>${monster.blastVulnerability}</h2>
		</div>
		<div class="centerboxchild">
			<h2>${monster.stunVulnerability}</h2>
		</div>
		
		<div class="centerboxchild">
			<h2></h2>
		</div>
		
		<div class="centerboxchild">
			<h2>${monster.fireVulnerability}</h2>
		</div>
		<div class="centerboxchild">
			<h2>${monster.waterVulnerability}</h2>
		</div>
		<div class="centerboxchild">
			<h2>${monster.thunderVulnerability}</h2>
		</div>
		<div class="centerboxchild">
			<h2>${monster.iceVulnerability}</h2>
		</div>
		<div class="centerboxchild">
			<h2>${monster.dragonVulnerability}</h2>
		</div>
	</div>


	<c:forEach items="${bodypartlist}" var="bodypart">
		<div class="centerbox">
			<div class="centerboxchild">
				<h1>${bodypart.name}</h1>
			</div>
			<div class="centerboxchild">
				<h2>${bodypart.isBreakable}</h2>
			</div>
			<div class="centerboxchild">
				<h2>${bodypart.isSeverable}</h2>
			</div>
			<div class="centerboxchild">
				<h2>${bodypart.bluntVulnerability}</h2>
			</div>
			<div class="centerboxchild">
				<h2>${bodypart.severingVulnerability}</h2>
			</div>
			<div class="centerboxchild">
				<h2>${bodypart.shotVulnerability}</h2>
			</div>
		</div>
	</c:forEach>
</div>

</body>
</html>