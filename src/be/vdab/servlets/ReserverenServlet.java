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

@WebServlet("/reserveren.htm")
public class ReserverenServlet extends HttpServlet {
private static final long serialVersionUID= 1L;
private static final String VIEW = "/WEB-INF/JSP/reserveren.jsp";
private final transient VoorstellingenDAO voorstellingenDAO = new VoorstellingenDAO();

@Resource(name = VoorstellingenDAO.JNDI_NAME) 
void setDataSource(DataSource dataSource) {
	voorstellingenDAO.setDataSource(dataSource); 
}

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	try {
		request.setAttribute("voorstelling",
			voorstellingenDAO.read(Integer.parseInt(request.getParameter("voorstellingid"))));
	} catch (NumberFormatException ex) { // request param bevat geen getal
		request.setAttribute("fout", "Nummer niet correct");
	}
	request.getRequestDispatcher(VIEW).forward(request, response);
	}
}