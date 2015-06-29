package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.KlantenDAO;
import be.vdab.entities.Klant;


@WebServlet("/bevestiging.htm")
public class BevestigingServlet extends HttpServlet {
private static final long serialVersionUID= 1L;
private final transient KlantenDAO klantenDAO = new KlantenDAO();
private static final String VIEW = "/WEB-INF/JSP/bevestiging.jsp";

@Resource(name = KlantenDAO.JNDI_NAME) 
void setDataSource(DataSource dataSource) {
	klantenDAO.setDataSource(dataSource); 
}

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	
	
	Klant klant = klantenDAO.findByGebruikersnaam("alexandra","bolle");
	request.setAttribute("klant",
			klant);
	request.getRequestDispatcher(VIEW).forward(request, response);
	}

}

