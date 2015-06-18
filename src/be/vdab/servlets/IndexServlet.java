package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.GenresDAO;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private final transient GenresDAO genresDAO = new GenresDAO();

	@Resource(name = GenresDAO.JNDI_NAME) 
	void setDataSource(DataSource dataSource) {
	genresDAO.setDataSource(dataSource); 
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("genres", genresDAO.findAll()); 						//alle items die via genresDAO gevonden worden in de database doorgeven aan de jsp		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
