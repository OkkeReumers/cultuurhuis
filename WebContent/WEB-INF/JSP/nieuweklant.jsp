<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Nieuwe klant' />
</head>
<body>
	<header>
		<h1>
			Het Cultuurhuis:nieuwe klant<img
				src="<c:url value='/images/nieuweklant.png'/>"
				alt="nieuwe klant logo" />
		</h1>
	</header>
	<vdab:menu currentpage="nieuweklant" />
	<form name="nieuweklantform" method="post" id="nieuweklantform">
<dl>
			<dt>Voornaam:</dt>
			<dd>
				<input type="text" name="voornaam" required="required" autofocus>
			</dd>
			<dt>Familienaam:</dt>
			<dd>
				<input type="text" name="familienaam" ><!-- required="required" -->
			</dd>
			<dt>Straat:</dt>
			<dd>
				<input type="text" name="straat" required="required">
			</dd>
			<dt>Huisnr:</dt>
			<dd>
				<input type="text" name="huisnr" required="required">
			</dd>
			<dt>Postcode:</dt>
			<dd>
				<input type="text" name="postcode" required="required">
			</dd>
			<dt>Gemeente:</dt>
			<dd>
				<input type="text" name="gemeente" required="required">
			</dd>
			<dt>Gebruikersnaam:</dt>
			<dd>
				<input type="text" name="gebruikersnaam" required="required">
			</dd>
			<dt>Paswoord:</dt>
			<dd>
				<input type="password" name="paswoord" required="required">
			</dd>
			<dt>Herhaal paswoord:</dt>
			<dd>
				<input type="password" name="herhaaldPaswoord" required="required">
			</dd>
		</dl>
			<input name="okKnop" type="submit" value="OK" />
	</form>
	<c:if test="${not empty fouten}">
		<ul>
			<c:forEach items="${fouten}" var="fout">
				<li>${fout}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>