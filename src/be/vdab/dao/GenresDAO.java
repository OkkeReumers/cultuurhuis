package be.vdab.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import be.vdab.entities.Genre;

public class GenresDAO extends AbstractDAO {
	private static final String SELECT_ALL = "Select id,naam from genres ";

	// zoek alle genres op die zich in de database bevinden
	public Iterable<Genre> findAll() {

		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_ALL
						+ "ORDER BY naam ASC");) {
			List<Genre> genrelijst = new ArrayList<>();
			while (resultSet.next()) {
				genrelijst.add(resultSetGenre(resultSet));
			}
			return genrelijst;
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}

	
	private Genre resultSetGenre(ResultSet resultSet) throws SQLException {
		return new Genre(resultSet.getInt("id"), resultSet.getString("naam"));
	}
}