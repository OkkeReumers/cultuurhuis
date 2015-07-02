<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Bevestiging' />
</head>
<body>
	<header>
		<h1>
			Het Cultuurhuis:bevestiging reservaties<img
				src="<c:url value='/images/bevestig.png'/>" alt="bevestig logo" />
		</h1>
	</header>
	<vdab:menu currentpage="bevestiging" />
	<h2>Stap 1: wie ben je ?</h2>
	<form name="gebruikerform" method="post" id="gebruikerform">
		<div>
			<label>Gebruikersnaam:<input type="text"
				name="gebruikersnaam" value='${gebruikersnaam }' autofocus required
				<c:if test="${not empty klant}">  disabled </c:if> /></label>
		</div>
		<div>
			<label>Paswoord:<input name="paswoord" type="password"
				required <c:if test="${not empty klant}">  disabled </c:if> /></label>
		</div>
		<div>
			<input name="zoekMeOpKnop" type="submit" value="Zoek me op"
				<c:if test="${not empty klant}">  disabled </c:if> />
		</div>
	</form>


	<form action="nieuweklant.htm">
		<input name="nieuwKnop" type="submit" value="Ik ben nieuw"
			<c:if test="${not empty klant}">  disabled </c:if>>
	</form>

	<c:if test="${not empty klant}">${klant.toString()}</c:if>
	<c:if test="${not empty fout}">${fout}</c:if>



	<h2>Stap 2: Bevestigen</h2>
	<form name="bevestiging" method="post">
		<input type="submit" value="Bevestigen" name="bevestigKnop"
			<c:if test="${empty klant}">  disabled </c:if> />
	</form>

	<script>
		document.getElementById('gebruikerform').onsubmit = function() {
			document.getElementByName('zoekMeOpKnop').disabled = true;
			document.getElementByName('nieuwKnop').disabled = true;
		};
		document.getElementById('bevestiging').onsubmit = function() {
			document.getElementByName('bevestigKnop').disabled = true;
		};
	</script>
</body>
</html>


