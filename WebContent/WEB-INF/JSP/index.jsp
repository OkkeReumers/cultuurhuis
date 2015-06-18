<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html>
<head>
<vdab:head title='Cultuurhuis' />		<!-- tag head inladen -->
</head>
<body>
	<header>
		<h1>
			Het Cultuurhuis:voorstellingen<img
				src="<c:url value='/images/voorstellingen.png'/>"
				alt="cultuurhuis logo" />
		</h1>
	</header>
	<vdab:menu />
	<table>
	<tbody id="subkop">
		<tr>
			<th>Datum</th>
			<th>Titel</th>
			<th>Uitvoerders</th>
			<th>Prijs</th>
			<th>Vrije plaatsen</th>
			<th>Reserveren</th>
	</tbody>
	<c:forEach var='voorstelling' items='${voorstellingen}'>
	<tbody>
	<tr>
	<th>${voorstelling.datum }</th>
	<th>${voorstelling.titel }</th>
	<th>${voorstelling.uitvoerders }</th>
	<th>${voorstelling.prijs }</th>
	<th>${voorstelling.vrijeplaatsen }</th>
	<th>reserveren</th>
	</tr>
	</tbody>
	</c:forEach> 
	</table>

</body>
</html>