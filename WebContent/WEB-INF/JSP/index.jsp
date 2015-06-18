<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
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
	<vdab:menu/>
</body>
</html>