<%@tag description='head onderdeel van pagina' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
	<h2>Genres</h2>
	<nav>
	<ul>
		<c:forEach var='genre' items='${genres}'> 							<!-- voor elke item dat uit de database gevonden wordt,via genresDAO en indexservlet wordt de link getoond -->
			<c:url value='/index.htm' var='detailURL'>
					<c:param name='genreid' value="${genre.id}"/>
				</c:url>
				<li><a href="<c:out value='${detailURL}'/>">${genre.naam }</a>	
			</li>
		</c:forEach>
		</ul>
	</nav>