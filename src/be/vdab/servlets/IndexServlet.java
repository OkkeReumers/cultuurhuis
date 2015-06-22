package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.GenresDAO;
import be.vdab.dao.VoorstellingenDAO;
import be.vdab.entities.Voorstelling;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private final transient GenresDAO genresDAO = new GenresDAO();
	private final transient VoorstellingenDAO voorstellingenDAO = new VoorstellingenDAO();

	@Resource(name = GenresDAO.JNDI_NAME) 
	void setDataSource(DataSource dataSource) {
	genresDAO.setDataSource(dataSource); 
	voorstellingenDAO.setDataSource(dataSource);
	}
	



	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("genres", genresDAO.findAll()); 						//alle items die via genresDAO gevonden worden in de database doorgeven aan de jsp		
		request.setAttribute("voorstellingen", voorstellingenDAO.findAll());
		List<String> fouten = new ArrayList<String>();
		if (request.getParameterValues("genreid") != null) {
			try {
				int genreid =Integer.parseInt(request.getParameter("genreid"));
				List<Voorstelling> voorstellinglijstgenre = voorstellingenDAO
						.findAllByGenre(genreid);
				if (voorstellinglijstgenre.isEmpty()) {			
					fouten.add("Geen voorstellingen");
				} else {
					request.setAttribute("voorstellingengenre", voorstellinglijstgenre);
				}
			} catch (NumberFormatException ex) {
				fouten.add("Ongeldig genre.");
			}
		}
		request.setAttribute("fouten", fouten);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
