<%@ page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html>
<head>
<vdab:head title='Overzicht' />
<!-- tag head inladen -->
</head>
<body>
	<header>
		<h1>
			Het Cultuurhuis:overzicht<img
				src="<c:url value='/images/bevestig.png'/>" alt="bevestig logo" />
		</h1>
	</header>
	<vdab:menu currentpage="overzicht" />
	<h2>Gelukte reserveringen</h2>
	<table>
		<thead>
			<tr>
				<th>Datum</th>
				<th>Titel</th>
				<th>Uitvoerders</th>
				<th>Prijs</th>
				<th>Plaatsen</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

	<h2>Mislukte reserveringen</h2>
	<table>
		<thead>
			<tr>
				<th>Datum</th>
				<th>Titel</th>
				<th>Uitvoerders</th>
				<th>Prijs</th>
				<th>Plaatsen</th>
				<th>Vrije plaatsen</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>