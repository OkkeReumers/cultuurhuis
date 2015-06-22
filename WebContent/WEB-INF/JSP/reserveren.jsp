<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Reserveren' />
<!-- tag head inladen -->
</head>
<body>
	<header>
		<h1>
			Het Cultuurhuis:reserveren<img
				src="<c:url value='/images/reserveren.png'/>" alt="reserveren logo" />
		</h1>
	</header>
	<menu>
		<ul>
			<li><c:url value='/index.htm' var='indexURL'></c:url> <a
				href="<c:out value='${indexURL}'/>">Voorstellingen</a></li>
		</ul>
	</menu>
	<dl>
		<dt>Voorstelling :</dt><dd>${voorstelling.titel}</dd>
		<dt>Uitvoerders:</dt><dd>${voorstelling.uitvoerders}</dd>
		<dt>Datum:</dt><dd>
			<fmt:formatDate value="${voorstelling.datum}" type="both"
				dateStyle='short' timeStyle='short' /></dd>
		<dt>Prijs:</dt><dd>€ ${voorstelling.prijs}</dd>
		<dt>Vrije Plaatsen:</dt><dd>${voorstelling.vrijeplaatsen}</dd>
		
<%-- 			<c:url var="urlsubmit" value="/reserveren.htm">
				<c:param name="voorstellingID" value="${voorstelling.id}" />
			</c:url> --%>
			
			<form name="reserveerform" method="post"  <%-- action="${urlsubmit}" --%>>
				<div>
					<label>Plaatsen:<span>${fouten.aantal}</span><input
						name="aantal" type="number" min='1'
						max='${voorstelling.vrijeplaatsen}' autofocus value='${aantal}' required /></label>
				</div>
				<div>
					<input name="reserveerknop" type="submit" value="Reserveren" />
				</div>
			</form>
	</dl>
	
	
	
<%-- 	
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
						<c:if test='${voorstelling.vrijeplaatsen > 0}'>
							<td><c:url value='/reserveren.htm' var='reserverenURL'>
									<c:param name='voorstellingid' value="${voorstelling.id}" />
								</c:url> <a href="<c:out value='${reserverenURL}'/>">Reserveren</a></td>
						</c:if>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</c:if>
	
	 --%>
	
	
	<script>
		document.getElementById('reserveerform').onsubmit = function() {
			document.getElementById('reserveerknop').disabled = true;
		};
	</script>
</body>
</html>




