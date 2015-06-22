package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
//	HttpSession session = request.getSession(false); 
//	if (session != null) {
//	@SuppressWarnings("unchecked") 
//	Set<Integer> mandje = (Set<Integer>) session.getAttribute(MANDJE); 
//	if (mandje != null) { 
//	List<Voorstelling> voorstellingInMandje = new ArrayList<>();
//	for (int voorstellingid : mandje) { 
//	voorstellingInMandje.add(voorstellingenDAO.read(voorstellingid));
//	}
//	request.setAttribute("voorstellingInMandje", voorstellingInMandje); 
//	}

//	}
	request.getRequestDispatcher(VIEW).forward(request, response);
	}


@Override
protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException {
	if (request.getParameterValues("voorstellingid") != null) {
		HttpSession session = request.getSession(); 
		@SuppressWarnings("unchecked")
		Set<Integer> mandje = (Set<Integer>)session.getAttribute(MANDJE); 
		if (mandje == null) { 
			mandje = new LinkedHashSet<>(); 
		}
		for (String voorstellingid : request.getParameterValues("voorstellingid")) {
			mandje.add(Integer.parseInt(voorstellingid)); 
		}
		session.setAttribute(MANDJE, mandje); 
	}
	response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
}
}