package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.Voorstelling;

public class VoorstellingenDAO extends AbstractDAO {

	private static final String SELECT_ALL = "Select id,titel,uitvoerders,datum,genreid,prijs"
			+ ",vrijeplaatsen from voorstellingen ";

	private static final String SELECT_BY_GENRE = SELECT_ALL
			+ "where genreid = ? AND datum >= CURDATE()";

	private static final String READ_SQL = SELECT_ALL + "where id=?";

	private final static Logger logger = Logger
			.getLogger(VoorstellingenDAO.class.getName());

	// ****************LAAT ALLE VOORSTELLINGEN ZIEN****************//
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

	// ****************LAAT ALLE VOORSTELLINGEN PER GENRE ZIEN****************//
	public List<Voorstelling> findAllByGenre(int genreid) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedstatement = connection
						.prepareStatement(SELECT_BY_GENRE)) {
			preparedstatement.setInt(1, genreid);
			try (ResultSet resultSet = preparedstatement.executeQuery()) {
				List<Voorstelling> voorstellinglijstgenre = new ArrayList<Voorstelling>();
				while (resultSet.next()) {

					voorstellinglijstgenre
							.add(resultSetVoorstelling(resultSet));
				}
				return voorstellinglijstgenre;
			} catch (SQLException ex) {
				throw new DAOException(ex);
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}

	}

	// ****************DE GEGEVENS VAN DE GEKOZEN VOORSTELLING OPHALEN****************//
	public Voorstelling read(int voorstellingid) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(READ_SQL)) {
			statement.setInt(1, voorstellingid);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSetVoorstelling(resultSet);
				}
				return null;
			}
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, "Probleem met database cultuurhuis", ex);
			throw new DAOException(ex);
		}
	}

	private Voorstelling resultSetVoorstelling(ResultSet resultSet)
			throws SQLException {

		return new Voorstelling(resultSet.getInt("id"),
				resultSet.getString("titel"),
				resultSet.getString("uitvoerders"),
				resultSet.getTimestamp("datum"), resultSet.getInt("genreid"),
				resultSet.getBigDecimal("prijs"),
				resultSet.getInt("vrijeplaatsen"));
	}
}
