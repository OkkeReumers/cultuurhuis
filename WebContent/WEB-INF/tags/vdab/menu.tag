<%@tag description='head onderdeel van pagina' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@attribute name='currentpage' required='true' type='java.lang.String'%>
<nav id="menu">
	<ul>
		<c:if test="${currentpage != 'index'}">
			<li><c:url value='/index.htm' var='indexURL'></c:url> <a
				href="<c:out value='${indexURL}'/>">Voorstellingen</a></li>
		</c:if>
		<c:if test='${not empty mandje}'>
			<c:if test="${currentpage != 'bevestiging' && currentpage != 'overzicht' }">
			<li><c:url value='/bevestiging.htm'
						var='bevestigingURL'></c:url> <a
					href="<c:out value='${bevestigingURL }'/>">Bevestiging reservatie</a></li>
					</c:if>
			<c:if test="${currentpage != 'mandje' && currentpage != 'overzicht'}">
				<li><c:url value='/reservatiemandje.htm'
						var='reservatiemandjeURL'></c:url> <a
					href="<c:out value='${reservatiemandjeURL }'/>">Reservatiemandje</a></li>
			</c:if>
		</c:if>
	</ul>
</nav>

