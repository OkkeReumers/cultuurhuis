<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Nieuwe klant' />
<!-- tag head inladen -->
</head>
<body>
	<header>
		<h1>
			Het Cultuurhuis:reservatiemandje<img
				src="<c:url value='/images/mandje.png'/>" alt="mandje logo" />
		</h1>
	</header>
	<menu>
		<ul>
			<li><c:url value='/index.htm' var='indexURL'></c:url> <a
				href="<c:out value='${indexURL}'/>">Voorstellingen</a></li>
		</ul>
	</menu>
</body>
</html>