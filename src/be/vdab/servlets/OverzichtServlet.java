package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.dao.VoorstellingenDAO;
import be.vdab.entities.Reservatie;
import be.vdab.entities.Voorstelling;

@WebServlet("/overzicht.htm")
public class OverzichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/overzicht.jsp";
	private static final transient VoorstellingenDAO voorstellingenDAO = new VoorstellingenDAO();

	@Resource(name = VoorstellingenDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenDAO.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session != null) {
			@SuppressWarnings("unchecked")
			List<Reservatie> geluktmandje = (List<Reservatie>) session
					.getAttribute("gelukt");

			if (geluktmandje != null) {
				request.setAttribute("geluktmandje", geluktmandje);
			}

			List<Reservatie> misluktmandje = (List<Reservatie>) session
					.getAttribute("mislukt");

			if (misluktmandje != null) {
				request.setAttribute("misluktmandje", misluktmandje);
			}

		}
		request.getRequestDispatcher(VIEW).forward(request, response);

	}

}
