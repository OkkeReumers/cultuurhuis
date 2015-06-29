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
import be.vdab.dao.VoorstellingenDAO;
import be.vdab.entities.Klant;


@WebServlet("/bevestiging.htm")
public class BevestigingServlet extends HttpServlet {
private static final long serialVersionUID= 1L;
private final transient KlantenDAO klantenDAO = new KlantenDAO();
private static final transient VoorstellingenDAO voorstellingenDAO = new VoorstellingenDAO();
private static final String VIEW = "/WEB-INF/JSP/bevestiging.jsp";
private static final String REDIRECT_URL = "/bevestiging.htm";

@Resource(name = KlantenDAO.JNDI_NAME) 
void setDataSource(DataSource dataSource) {
	klantenDAO.setDataSource(dataSource);
	voorstellingenDAO.setDataSource(dataSource);
}

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	request.getRequestDispatcher(VIEW).forward(request, response);
	}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String gebruikersnaam=request.getParameter("gebruikersnaam");
	String paswoord=request.getParameter("paswoord");
	Klant klant=klantenDAO.findByGebruikersnaam(gebruikersnaam, paswoord);
	if(klant==null){
		request.setAttribute("fout", "Verkeerde gebruikersnaam of paswoord");
		request.getRequestDispatcher(VIEW).forward(request, response);
	}else{
		request.getSession().setAttribute("klant", klant);
		response.sendRedirect(response.encodeURL(request.getContextPath()+ REDIRECT_URL));
	}
}

}

