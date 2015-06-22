<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Reservatiemandje' />
<!-- tag head inladen -->
</head>
<body>
	<header>
		<h1>
			Het Cultuurhuis:reservatiemandje<img
				src="<c:url value='/images/mandje.png'/>" alt="mandje logo" />
		</h1>
	</header>
<vdab:menu/>
	<c:if test='${not empty voorstellingInMandje}'>
		<table>
			<thead>
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs</th>
					<th>Plaatsen</th>
					<th>Verwijderen</th>
			</thead>
			<c:forEach var='voorstelling' items='${voorstellingInMandje}'>
				<tbody>
					<tr>
						<td><fmt:formatDate value="${voorstelling.datum}" type="both"
								dateStyle='short' timeStyle='short' /></td>
						<td>${voorstelling.titel }</td>
						<td>${voorstelling.uitvoerders }</td>
						<td>€ ${voorstelling.prijs }</td>
						<td>${voorstelling.vrijeplaatsen }</td>
						<td><label><input type='checkbox' name='id'
							value='${voorstelling.id}'> <c:out value='${voorstelling.titel}' /></label></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<p>Te betalen:
	</c:if>
</body>
</html>