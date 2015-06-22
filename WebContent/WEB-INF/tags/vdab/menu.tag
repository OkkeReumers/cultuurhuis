<%@tag description='head onderdeel van pagina' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
	<nav id="menu">
		<ul>
			<li><c:url value='/index.htm' var='indexURL'></c:url> <a
				href="<c:out value='${indexURL}'/>">Voorstellingen</a></li>
			<c:if test='${not empty mandje}'>
			<li><c:url value='/bevestiging.htm' var='bevestigingURL'></c:url> <a
				href="<c:out value='${bevestingingURL }'/>">Bevestiging reservatie</a></li>
			<li><c:url value='/reservatiemandje.htm' var='reservatiemandjeURL'></c:url> <a
				href="<c:out value='${reservatiemandjeURL }'/>">Reservatiemandje</a></li>
			</c:if>
		</ul>
	</nav>
	
	