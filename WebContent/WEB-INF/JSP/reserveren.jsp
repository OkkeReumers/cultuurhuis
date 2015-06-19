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
		<dt>Plaatsen:</dt>
		<dd>
			<form>
			<div>
				<label><span>${fouten.van}</span> <input name='plaatsen'
					value='${param.plaatsen}' class='plaatsen' autofocus type='number'
					min='0' required></label></div>
				<!-- de invoervakken client sided valideren -->
				<div>
				<input type='submit' value='Reserveren' class='reserveren'>
				</div>
			</form>
		</dd>
	</dl>
</body>
</html>