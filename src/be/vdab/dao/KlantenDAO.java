package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import be.vdab.entities.Klant;

public class KlantenDAO extends AbstractDAO {
	private static final String SELECT_KLANT = "SELECT id, voornaam,familienaam,straat,huisnr,postcode,gemeente,gebruikersnaam,paswoord FROM klanten WHERE gebruikersnaam = ? AND paswoord = ? ";
	private static final String BESTAAT_KLANT = "SELECT id FROM klanten WHERE gebruikersnaam = ? ";
	private static final String INSERT = "INSERT INTO klanten(Voornaam,Familienaam,Straat,HuisNr,Postcode,Gemeente,GebruikersNaam,Paswoord) values(?,?,?,?,?,?,?,?)";

	// ****************LAAT DE KLANT MET PASSENDE GEBRUIKERSNAAM EN WACHTWOORD ZIEN****************//
	public Klant findByGebruikersnaam(String gebruikersnaam, String paswoord) {

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(SELECT_KLANT);) {
			preparedStatement.setString(1, gebruikersnaam);
			preparedStatement.setString(2, paswoord);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				Klant klant = null;
				if (resultSet.next()) {
					klant = resultSetKlanten(resultSet);
				}
				return klant;
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}

	// ****************VOEG KLANT TOE****************//
	public void insert(String voornaam, String familienaam, String straat,
			String huisNr, String postcode, String gemeente,
			String gebruikersNaam, String paswoord) {

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(INSERT);) {
			preparedStatement.setString(1, voornaam);
			preparedStatement.setString(2, familienaam);
			preparedStatement.setString(3, straat);
			preparedStatement.setString(4, huisNr);
			preparedStatement.setString(5, postcode);
			preparedStatement.setString(6, gemeente);
			preparedStatement.setString(7, gebruikersNaam);
			preparedStatement.setString(8, paswoord);
			preparedStatement.execute();
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}

	}

	// ****************CONTROLEREN OF KLANTE BESTAAT****************//
	public boolean bestaatKlant(String gebruikersnaam) {
		boolean bestaand = false;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(BESTAAT_KLANT);) {
			statement.setString(1, gebruikersnaam);
			try (ResultSet resultset = statement.executeQuery()) {
				if (resultset.next()) {
					bestaand = true;
				}
			}
			return bestaand;
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}

	public Klant resultSetKlanten(ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getInt("id"),
				resultSet.getString("voornaam"),
				resultSet.getString("familienaam"),
				resultSet.getString("straat"), resultSet.getString("huisnr"),
				resultSet.getString("postcode"),
				resultSet.getString("gemeente"),
				resultSet.getString("gebruikersnaam"),
				resultSet.getString("paswoord"));
	}
}
