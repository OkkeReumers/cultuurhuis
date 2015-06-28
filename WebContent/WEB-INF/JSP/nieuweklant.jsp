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
		<div>
			<label>Voornaam:<span>${fout}</span><input
				name="voornaam" autofocus required /></label>
		</div>
		<div>
			<label>Familienaam:<span>${fout}</span><input
				name="familienaam" required /></label>
		</div>
		<div>
			<label>Straat:<span>${fout}</span><input
				name="straat" required /></label>
		</div>
		<div>
			<label>Huisnr:<span>${fout}</span><input
				name="huisnr" required /></label>
		</div>
		<div>
			<label>Postcode:<span>${fout}</span><input
				name="postcode" required /></label>
		</div>
		<div>
			<label>Gemeente:<span>${fout}</span><input
				name="gemeente" required /></label>
		</div>
		<div>
			<label>Gebruikersnaam:<span>${fout}</span><input
				name="gebruikersnaam" required /></label>
		</div>
		<div>
			<label>Paswoord:<span>${fout}</span><input
				name="paswoord" required /></label>
		</div>
		<div>
			<label>Herhaal paswoord:<span>${fout}</span><input
				name="herhaalpaswoord" required /></label>
		</div>
		<div>
			<input name="okKnop" type="submit" value="OK" />
		</div>
	</form>
</body>
</html>