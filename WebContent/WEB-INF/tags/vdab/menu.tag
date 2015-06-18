<%@tag description='head onderdeel van pagina' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
	<h2>Genres</h2>
	<nav>
	<ul>
		<c:forEach var='genre' items='${genres}'> 							<!-- voor elke item dat uit de database gevonden wordt,via genresDAO en indexservlet wordt de link getoond -->
			<c:url value='/voorstellingen/detail.htm' var='detailURL'>
				<c:param name='id' value="${genre.id}" />
			</c:url>
			<li><a href="<c:out value='${genre.naam}'/>">${genre.naam }</a> <!-- de naam die uit de database genomen wordt tonen als link -->
			</li>
		</c:forEach>
		</ul>
	</nav>