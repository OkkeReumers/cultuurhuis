package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.vdab.entities.Klant;

public class KlantenDAO extends AbstractDAO {
	private static final String SELECT_KLANT = "SELECT id, voornaam,familienaam,straat,huisnr,postcode,gemeente,gebruikersnaam,paswoord FROM klanten WHERE gebruikersnaam = ? AND paswoord = ? ";

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
	
	public Klant resultSetKlanten(ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getInt("id"),
				resultSet.getString("voornaam"),
				resultSet.getString("familienaam"),
				resultSet.getString("straat"), 
				resultSet.getString("huisnr"),
				resultSet.getString("postcode"), 
				resultSet.getString("gemeente"),
				resultSet.getString("gebruikersnaam"),
				resultSet.getString("paswoord"));
	}
}
