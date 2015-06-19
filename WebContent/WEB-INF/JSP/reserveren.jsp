<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Reserveren' />		<!-- tag head inladen -->
</head>
	<body>
		<header>
		<h1>
			Het Cultuurhuis:reserveren<img
				src="<c:url value='/images/reserveren.png'/>"
				alt="reserveren logo" />
		</h1>
	</header>
				<dl>
					<dt>Voorstelling :</dt><dd>${voorstelling.titel}</dd>
					<dt>Uitvoerders:</dt><dd>${voorstelling.uitvoerders}</dd>
					<dt>Datum:</dt><dd>${voorstelling.datum}</dd>
					<dt>Prijs:</dt><dd>${voorstelling.prijs}</dd>
					<dt>Plaatsen:</dt><dd>${voorstelling.vrijeplaatsen}</dd>
				</dl>
	</body>
</html>