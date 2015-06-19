<%@ page contentType='text/html' pageEncoding='UTF-8'%>
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
	<c:if test='${not empty voorstellingengenre}'> <!-- tabel alleen laten zien als er iets in voorstellingengenre zit, dus niet bij de beginpagina -->
		<table>
			<thead>
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs</th>
					<th>Vrije plaatsen</th>
					<th>Reserveren</th>
			</thead>
			<c:forEach var='voorstelling' items='${voorstellingengenre}'>
				<tbody>
					<tr>
						<td><fmt:formatDate value="${voorstelling.datum}" type="both"
								dateStyle='short' timeStyle='short' /></td>
						<td>${voorstelling.titel }</td>
						<td>${voorstelling.uitvoerders }</td>
						<td>€ ${voorstelling.prijs }</td>
						<td>${voorstelling.vrijeplaatsen }</td>
						<c:if test='${voorstelling.vrijeplaatsen > 0}'>
						<td>
						<c:url value='/reserveren.htm' var='reserverenURL'>
						<c:param name='voorstellingid' value="${voorstelling.id}"/>
						</c:url>
						<a href="<c:out value='${reserverenURL}'/>">Reserveren</a></td>
						</c:if>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>