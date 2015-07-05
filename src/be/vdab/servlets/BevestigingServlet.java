package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.dao.KlantenDAO;
import be.vdab.dao.ReservatieDAO;
import be.vdab.dao.VoorstellingenDAO;
import be.vdab.entities.Klant;
import be.vdab.entities.Reservatie;
import be.vdab.entities.Voorstelling;

@WebServlet("/bevestiging.htm")
public class BevestigingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final transient KlantenDAO klantenDAO = new KlantenDAO();
	private static final transient VoorstellingenDAO voorstellingenDAO = new VoorstellingenDAO();
	private static final ReservatieDAO reservatieDAO = new ReservatieDAO();
	private static final String VIEW = "/WEB-INF/JSP/bevestiging.jsp";
	private static final String REDIRECT_URL = "/bevestiging.htm";
	private static final String REDIRECT_OVERZICHT = "%s/overzicht.htm";

	@Resource(name = KlantenDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantenDAO.setDataSource(dataSource);
		voorstellingenDAO.setDataSource(dataSource);
		reservatieDAO.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		/*controleren of ingegeven gebruikersnaam en paswoord bestaan als op de zoek me op knop wordt geduwd
		zoniet, foutmelding tonen,
		anders klantengegevens tonen die bij gebruikersnaam horen*/
		
		if (request.getParameterValues("zoekMeOpKnop") != null) {
			String gebruikersnaam = request.getParameter("gebruikersnaam");
			String paswoord = request.getParameter("paswoord");
			Klant klant = klantenDAO.findByGebruikersnaam(gebruikersnaam,
					paswoord);
			if (klant == null) {
				request.setAttribute("fout",
						"Verkeerde gebruikersnaam of paswoord");
				request.getRequestDispatcher(VIEW).forward(request, response);
			} else {
				session.setAttribute("klant", klant);
				response.sendRedirect(response.encodeURL(request
						.getContextPath() + REDIRECT_URL));
			}
		}

		/* als bevestigknop wordt ingeduwd roep klant id op, roep voorstellingen in mandje op */
		if (request.getParameterValues("bevestigKnop") != null) {
			Klant klant = (Klant) session.getAttribute("klant");

			int klantId = klantenDAO.findByGebruikersnaam(
					klant.getGebruikersnaam(), klant.getPaswoord()).getId();

			Map<Integer, Integer> mandje = (Map<Integer, Integer>) session
					.getAttribute("mandje");
			
			/* lijst gelukte en mislukte reservaties maken*/
			List<Reservatie> geluktereservatie = new ArrayList<Reservatie>();
			List<Reservatie> misluktereservatie = new ArrayList<Reservatie>();

			/*reservaties in de database zetten en als dat lukt ook in de lijst gelukte reservaties*/
			for (Map.Entry<Integer, Integer> reservatie : mandje.entrySet()) {
				Voorstelling voorstelling = voorstellingenDAO.read(reservatie
						.getKey());
				if (reservatieDAO.bestel(klantId, voorstelling.getId(),
						reservatie.getValue(), voorstelling.getVrijeplaatsen())) {
					geluktereservatie.add(new Reservatie(voorstelling,
							reservatie.getValue()));

					/*mislukt de reservatie, komt hij in de mislukte reservaties lijst */
				} else {
					misluktereservatie.add(new Reservatie(voorstelling,
							reservatie.getValue()));
				}

				session.setAttribute("gelukt", geluktereservatie);
				session.setAttribute("mislukt", misluktereservatie);

			}
			/*mandje leegmaken*/
			mandje.clear();
			session.setAttribute("mandje", mandje);
			response.sendRedirect(response.encodeURL(String.format(
					REDIRECT_OVERZICHT, request.getContextPath())));
		}

	}
}
