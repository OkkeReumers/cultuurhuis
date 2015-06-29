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
			<label>Gebruikersnaam:<input
				name="gebruikersnaam" autofocus required /></label>
		</div>
		<div>
			<label>Paswoord:<input name="paswoord"
				required /></label>
		</div>
		<div>
			<input name="zoekMeOpKnop" type="submit" value="Zoek me op" />
		</div>
	</form>

		
	<form action="nieuweklant.htm">
		<input type="submit" value="Ik ben nieuw">
	</form>
	
	
	<div>${klant.voornaam} ${klant.familienaam } ${klant.straat }
		${klant.huisnr } ${klant.postcode } ${klant.gemeente }
		<c:if test='${empty klant }'>
			Verkeerde gebruikersnaam of paswoord
		</c:if>
	</div>	
	
	
	<h2>Stap 2: Bevestigen</h2>
	<form action="http://google.com">
		<input type="submit" value="Bevestigen">
	</form>
</body>
</html>


