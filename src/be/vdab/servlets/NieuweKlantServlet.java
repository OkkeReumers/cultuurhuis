package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.KlantenDAO;
import be.vdab.entities.Klant;

@WebServlet("/nieuweklant.htm")
public class NieuweKlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/nieuweklant.jsp";
	private static final KlantenDAO klantenDAO = new KlantenDAO();
	private static final String REDIRECT_URL = "/bevestiging.htm";

	@Resource(name = KlantenDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantenDAO.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/*Formulier posten, met de foutmeldingen en controles*/
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> fouten = new ArrayList<>();

		String voornaam = request.getParameter("voornaam");
		if (voornaam.length() == 0) {
			fouten.add("Voornaam niet ingevuld");
		}
		String familienaam = request.getParameter("familienaam");
		if (familienaam.length() == 0) {
			fouten.add("Familienaam niet ingevuld");
		}
		String straat = request.getParameter("straat");
		if (straat.length() == 0) {
			fouten.add("Straat niet ingevuld");
		}
		String huisnr = request.getParameter("huisnr");
		if (huisnr.length() == 0) {
			fouten.add("Huisnr niet ingevuld");
		}
		String postcode = request.getParameter("postcode");
		if (postcode.length() == 0) {
			fouten.add("Postcode niet ingevuld");
		}
		String gemeente = request.getParameter("gemeente");
		if (gemeente.length() == 0) {
			fouten.add("Gemeente niet ingevuld");
		}
		String gebruikersnaam = request.getParameter("gebruikersnaam");
		if (gebruikersnaam.length() == 0) {
			fouten.add("Gebruikersnaam niet ingevuld");
		}
		String paswoord = request.getParameter("paswoord");
		if (paswoord.length() == 0) {
			fouten.add("Paswoord niet ingevuld");
		}
		String herhaaldPaswoord = request.getParameter("herhaaldPaswoord");
		if (herhaaldPaswoord.length() == 0) {
			fouten.add("Herhaal paswoord niet ingevuld");
		}
		if (!paswoord.equals(herhaaldPaswoord)) {
			fouten.add("Paswoord en herhaal paswoord zijn verschillend");
		}
		if (klantenDAO.bestaatKlant(gebruikersnaam)) {
			fouten.add("Gebruikersnaam bestaat al");
		}
		/* eventuele fouten laten zien*/
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		} else {
			/*als alles ok is de klant in de database zetten*/
			klantenDAO.insert(voornaam, familienaam, straat, huisnr, postcode,
					gemeente, gebruikersnaam, paswoord);
			Klant klant = klantenDAO.findByGebruikersnaam(gebruikersnaam,
					paswoord);
			request.getSession().setAttribute("klant", klant);
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath() + REDIRECT_URL));
		}
	}
}