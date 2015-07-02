package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
import be.vdab.entities.Reservatie;

@WebServlet("/reservatiemandje.htm")
public class ReservatieMandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatiemandje.jsp";
	private final transient VoorstellingenDAO voorstellingenDAO = new VoorstellingenDAO();
	private static final String MANDJE = "mandje";

	@Resource(name = VoorstellingenDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenDAO.setDataSource(dataSource);
	}

	// VOORSTELLINGEN UIT DE MANDJESLIST OPHALEN
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> mandje = (Map<Integer, Integer>) session
				.getAttribute(MANDJE);
		if (mandje != null) {
			List<Reservatie> reservaties = new ArrayList<>();
			BigDecimal totaal = new BigDecimal(0);
			for (Integer key : mandje.keySet()) {
				Reservatie reservatie = new Reservatie(
						voorstellingenDAO.read(key), mandje.get(key));
				reservaties.add(reservatie);
				totaal = totaal.add(reservatie.getVoorstelling().getPrijs()
						.multiply(new BigDecimal(mandje.get(key))));
			}
			request.setAttribute("totaal", totaal);
			request.setAttribute("reservaties", reservaties);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<Integer, Integer> mandje = (Map<Integer, Integer>) session
				.getAttribute(MANDJE);
		if (request.getParameter("verwijder") != null) {
			Set<Integer> verwijderReservaties = new LinkedHashSet<>();
			for (String teVerwijderen : request.getParameterValues("verwijder")) {
				verwijderReservaties.add(Integer.parseInt(teVerwijderen));
			}
			for (Integer reservatie : verwijderReservaties) {
				mandje.remove(reservatie);
			}
		}

		session.setAttribute("mandje", mandje);
		response.sendRedirect(response.encodeRedirectURL(request
				.getRequestURI()));

	}
}