package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import be.vdab.entities.Voorstelling;

public class VoorstellingenDAO extends AbstractDAO{

	private static final String SELECT_ALL= "Select id,titel,uitvoerders,datum,genreid,prijs"
			+ ",vrijeplaatsen from voorstellingen ";
	
	private static final String SELECT_BY_GENRE= "Select id,titel,uitvoerders,datum,genreid,prijs"
			+ ",vrijeplaatsen from voorstellingen where genreid = ? AND datum >= CURDATE()";
	
	
//****************LAAT ALLE VOORSTELLINGEN ZIEN****************//
	public Iterable<Voorstelling> findAll() {
	try (Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL
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
	
	//****************LAAT ALLE VOORSTELLINGEN PER GENRE ZIEN****************//
	public List<Voorstelling> findAllByGenre(int genreid) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(SELECT_BY_GENRE)) {
				preparedstatement.setInt(1, genreid);
				try (ResultSet resultSet = preparedstatement.executeQuery()) {
					List<Voorstelling> voorstellinglijstgenre = new ArrayList<Voorstelling>();
					while (resultSet.next()) {
						
						voorstellinglijstgenre.add(resultSetVoorstelling(resultSet));						
					}
					return voorstellinglijstgenre;
				} catch (SQLException ex) {
					throw new DAOException(ex);
				}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
		
	}
	
	private Voorstelling resultSetVoorstelling(ResultSet resultSet) throws SQLException {

		return new Voorstelling(resultSet.getInt("id"), resultSet.getString("titel"), 
				resultSet.getString("uitvoerders"), resultSet.getTimestamp("datum"), resultSet.getInt("genreid"), 
				resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijeplaatsen"));
	}
}

