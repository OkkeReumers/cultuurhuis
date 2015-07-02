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
	<vdab:menu currentpage="mandje" />
	<c:if test='${not empty mandje}'>
		<form method="post" id="verwijder">
			<table>
				<thead>
					<tr>
						<th>Datum</th>
						<th>Titel</th>
						<th>Uitvoerders</th>
						<th>Prijs</th>
						<th>Plaatsen</th>
						<th><input name="verwijderknop" type="submit"
							value="Verwijderen" /></th>
				</thead>
				<c:forEach var='reservatie' items='${reservaties}'>
					<tbody>
						<tr>
							<td><fmt:formatDate value="${reservatie.voorstelling.datum}"
									type="both" dateStyle='short' timeStyle='short' /></td>
							<td>${reservatie.voorstelling.titel }</td>
							<td>${reservatie.voorstelling.uitvoerders }</td>
							<td>€ ${reservatie.voorstelling.prijs }</td>
							<td>${reservatie.aantalPlaatsen }</td>
							<td><label><input type='checkbox' name='verwijder'
									value='${reservatie.voorstelling.id}'></label></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</form>
		Te betalen: &euro; <fmt:formatNumber type="number"
			minFractionDigits="2" value="${totaal}" />
	</c:if>
</body>
</html>