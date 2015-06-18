package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Genre;
import be.vdab.entities.Voorstelling;

public class VoorstellingenDAO extends AbstractDAO{

	private static final String SELECT_ALL_GENRE= "Select id,titel,uitvoerders,datum,genreid,prijs"
			+ ",vrijeplaatsen from voorstellingen ";

	public Iterable<Voorstelling> findAllGenre() {
	try (Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_GENRE
					+ "ORDER BY datum ASC");) {
		List<Voorstelling> voorstellinglijst = new ArrayList<>();
		while (resultSet.next()) {
			voorstellinglijst.add(resultSetVoorstelling(resultSet));
		}
		return voorstellinglijst;
	} catch (SQLException ex) {
		throw new DAOException(ex);
	}
}

	
	private Voorstelling resultSetVoorstelling(ResultSet resultSet) throws SQLException {

		return new Voorstelling(resultSet.getInt("id"), resultSet.getString("titel"), 
				resultSet.getString("uitvoerders"), resultSet.getDate("datum"), resultSet.getInt("genreid"), 
				resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijeplaatsen"));
	}
}
