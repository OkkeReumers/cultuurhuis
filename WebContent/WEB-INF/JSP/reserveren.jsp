<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Reserveren' />
</head>
<body>
	<header>
		<h1>
			Het Cultuurhuis:reserveren<img
				src="<c:url value='/images/reserveren.png'/>" alt="reserveren logo" />
		</h1>
	</header>
<vdab:menu currentpage="reserveren"/>
	<dl>
		<dt>Voorstelling :</dt><dd>${voorstelling.titel}</dd>
		<dt>Uitvoerders:</dt><dd>${voorstelling.uitvoerders}</dd>
		<dt>Datum:</dt><dd>
			<fmt:formatDate value="${voorstelling.datum}" type="both"
				dateStyle='short' timeStyle='short' /></dd>
		<dt>Prijs:</dt><dd>€ ${voorstelling.prijs}</dd>
		<dt>Vrije Plaatsen:</dt><dd>${voorstelling.vrijeplaatsen}</dd>
			
			<form name="reserveerform" method="post" id="reserveerform">
				<div>
					<label>Plaatsen:<span>${fout}</span><input
						name="aantalPlaatsen" type="number" min='1'
						 max='${voorstelling.vrijeplaatsen}' autofocus value='${aantalPlaatsen}' required /></label>
				</div>
				<div>
					<input name="reserveerknop" type="submit" value="Reserveren" />
				</div>
			</form>
	</dl>
		
	<script>
		document.getElementById('reserveerform').onsubmit = function() {
			document.getElementById('reserveerknop').disabled = true;
		};
	</script>
</body>
</html>




