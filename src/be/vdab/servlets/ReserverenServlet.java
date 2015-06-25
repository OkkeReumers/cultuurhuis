package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.dao.VoorstellingenDAO;
import be.vdab.entities.Voorstelling;



@WebServlet("/reserveren.htm")
public class ReserverenServlet extends HttpServlet {
private static final long serialVersionUID= 1L;
private static final String VIEW = "/WEB-INF/JSP/reserveren.jsp";
private final transient VoorstellingenDAO voorstellingenDAO = new VoorstellingenDAO();
private static final String MANDJE = "mandje";
private static final String REDIRECT_URL = "%s/reservatiemandje.htm";

@Resource(name = VoorstellingenDAO.JNDI_NAME) 
void setDataSource(DataSource dataSource) {
	voorstellingenDAO.setDataSource(dataSource); 
}

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	
	Voorstelling voorstelling = voorstellingenDAO.read(Integer.parseInt(request.getParameter("voorstellingid")));
	int voorstellingid = voorstelling.getId();
//	VOORSTELLING MET HET JUISTE ID LATEN ZIEN
		request.setAttribute("voorstelling",voorstelling);

	HttpSession session = request.getSession();
	
	if (session.getAttribute("mandje") != null) {
		@SuppressWarnings("unchecked")
	Map<Integer,Integer> mandje = ((Map<Integer,Integer>) session.getAttribute("mandje"));
	if (mandje.containsKey(voorstellingid)) {
		request.setAttribute("aantalPlaatsen", mandje.get(voorstellingid));
		
	}
		
	}
	request.getRequestDispatcher(VIEW).forward(request, response);
	}

@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException {
	HttpSession session = request.getSession();
	Voorstelling voorstelling = voorstellingenDAO.read(Integer.parseInt(request.getParameter("voorstellingid")));
	request.setAttribute("voorstelling",
			voorstelling);
	if (voorstelling != null) {
		int aantalPlaatsen=Integer.parseInt( request.getParameter("aantalPlaatsen"));
		if (aantalPlaatsen >= 1 && aantalPlaatsen<= voorstelling.getVrijeplaatsen()) {
			@SuppressWarnings("unchecked")
			Map <Integer,Integer> mandje = (Map<Integer,Integer>)session.getAttribute(MANDJE); 
			if (mandje == null) { 
				mandje = new HashMap<>(); }
				mandje.put(voorstelling.getId(), aantalPlaatsen); 
				session.setAttribute(MANDJE, mandje);
				response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
			} else{ 
				request.setAttribute("fout", "Tik een getal tussen 1 en "+voorstelling.getVrijeplaatsen());
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		
		}
	}
}



