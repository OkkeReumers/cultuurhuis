package be.vdab.servlets;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.VoorstellingenDAO;

@WebServlet("/nieuweklant.htm")
public class NieuweKlantServlet extends HttpServlet {
private static final long serialVersionUID= 1L;
private static final String VIEW = "/WEB-INF/JSP/nieuweklant.jsp";
private final transient VoorstellingenDAO voorstellingenDAO = new VoorstellingenDAO();

@Resource(name = VoorstellingenDAO.JNDI_NAME) 
void setDataSource(DataSource dataSource) {
	voorstellingenDAO.setDataSource(dataSource); 
}

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	request.getRequestDispatcher(VIEW).forward(request, response);
	}
}