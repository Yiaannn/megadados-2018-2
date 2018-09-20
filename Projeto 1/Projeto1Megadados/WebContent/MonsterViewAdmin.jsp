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

<datalist id="vulnerability">
  <option value="0" label="🚫">
  <option value="1" label="AAA">
  <option value="2" label="⭐⭐">
  <option value="3" label="⭐⭐⭐">
</datalist>

<div class="boxwrapper">
	<div class="centerbox">
		<div class="centerboxchild">
			<h1>${monster.name} - Dados Gerais</h1>
		</div>
		<div class="centerboxchild">
			<details>
				<summary>Modificar Nome</summary>
				<div class="centerboxchild">
				<form id="patchname" action='/Projeto1Megadados/Monsters/${monster.name}' method='post' >
					<input type='text' name='newname' placeholder='ex.: Rathalos'><br>
					<input type="hidden" name="_method" value="PATCH">
					<input type="hidden" name="query" value="modifyname">
					<input type="hidden" name="target" value="${monster.name}">
				</form>
				</div>
				<div class="centerboxchild">
					<button type="submit" form="patchname">Confirmar</button>
				</div>
			</details>
		</div>
		
		<div class="centerboxchild">
			${monster.subspeciesOf}
		</div>
		<div class="centerboxchild">
			<details>
				<summary>Reassociar Subspécie</summary>
				<div class="centerboxchild">
				<form id="patchsubspecies" action='/Projeto1Megadados/Monsters/${monster.name}' method='post' >
					<input type='text' name='subspecies' placeholder='Deixe em branco para não associar'><br>
					<input type="hidden" name="_method" value="PATCH">
					<input type="hidden" name="query" value="modifysubspecies">
					<input type="hidden" name="target" value="${monster.name}">
				</form>
				</div>
				<div class="centerboxchild">
					<button type="submit" form="patchsubspecies">Confirmar</button>
				</div>
			</details>
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
			<details>
				<summary>Modificar Vulnerabilidade a Ailments</summary>
				<div class="centerboxchild">
				<form id="patchailments" action='/Projeto1Megadados/Monsters/${monster.name}' method='post' >
					<h2>Vulnerabilidade à Veneno</h2>
					<input type="range" list="vulnerability" max="3" name="poison">
					<h2>Vulnerabilidade à Sono</h2>
					<input type="range" list="vulnerability" max="3" name="sleep">
					<h2>Vulnerabilidade à Parálise</h2>
					<input type="range" list="vulnerability" max="3" name="paralysis">
					<h2>Vulnerabilidade à Implosão</h2>
					<input type="range" list="vulnerability" max="3" name="blast">
					<h2>Vulnerabilidade à Atordoamento</h2>
					<input type="range" list="vulnerability" max="3" name="stun">
					<input type="hidden" name="_method" value="PATCH">
					<input type="hidden" name="query" value="modifyailments">
					<input type="hidden" name="target" value="${monster.name}">
				</form>
				</div>
				<div class="centerboxchild">
					<button type="submit" form="patchailments">Confirmar</button>
				</div>
			</details>
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
		
		<div class="centerboxchild">
			<details>
				<summary>Modificar Vulnerabilidade Elemental</summary>
				<div class="centerboxchild">
				<form id="patchelemental" action='/Projeto1Megadados/Monsters/${monster.name}' method='post' >
					<h2>Vulnerabilidade à Fogo</h2>
					<input type="range" list="vulnerability" max="3" name="fire">
					<h2>Vulnerabilidade à Água</h2>
					<input type="range" list="vulnerability" max="3" name="water">
					<h2>Vulnerabilidade à Raio</h2>
					<input type="range" list="vulnerability" max="3" name="thunder">
					<h2>Vulnerabilidade à Gelo</h2>
					<input type="range" list="vulnerability" max="3" name="ice">
					<h2>Vulnerabilidade à Dragão</h2>
					<input type="range" list="vulnerability" max="3" name="dragon">
					<input type="hidden" name="_method" value="PATCH">
					<input type="hidden" name="query" value="modifyelemental">
					<input type="hidden" name="target" value="${monster.name}">
				</form>
				</div>
				<div class="centerboxchild">
					<button type="submit" form="patchelemental">Confirmar</button>
				</div>
			</details>
		</div>
	</div>


	<div class="centerbox">
		<div class="centerboxchild">
			<details>
				<summary>Adicionar nova Parte do Corpo</summary>
				<div class="centerboxchild">
				<form id="newbodypart" action='/Projeto1Megadados/Monsters/${monster.name}' method='post' >
					<h2>Nome</h2>
					<input type='text' name='newname' placeholder='ex.: Cabeça, Asas, Garras, etc'><br>
					<h2>É quebrável?</h2>
					<input type="radio" name="isBreakable" value="true" checked>Sim<br>
					<input type="radio" name="isBreakable" value="false">Não<br>
					<h2>É cortável?</h2>
					<input type="radio" name="isSeverable" value="true" checked>Sim<br>
					<input type="radio" name="isSeverable" value="false">Não<br>
					<h2>Vulnerabilidade à Impacto</h2>
					<input type="range" list="vulnerability" max="3" name="blunt">
					<h2>Vulnerabilidade à Corte</h2>
					<input type="range" list="vulnerability" max="3" name="severing">
					<h2>Vulnerabilidade à Projétil</h2>
					<input type="range" list="vulnerability" max="3" name="shot">
					
					<input type="hidden" name="target" value="${monster.name}">
				</form>
				</div>
				<div class="centerboxchild">
					<button type="submit" form="newbodypart">Confirmar</button>
				</div>
			</details>
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
			
			<div class="centerboxchild">
				<form id="bodypartdelete" action='/Projeto1Megadados/Monsters/${monster.name}' method='post'>
					<input type="hidden" name="_method" value="DELETE">
				</form>
				<button type="submit" form="bodypartdelete" name="bodypartid" value="${bodypart.id}">Apagar</button>
			</div>
		</div>
	</c:forEach>
</div>

</body>
</html>