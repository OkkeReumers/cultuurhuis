package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservatieDAO extends AbstractDAO {
	private static final String INSERT = "INSERT INTO reservaties(klantid,voorstellingsid,plaatsen) values(?,?,?)";
	private static final String UPDATE = "UPDATE voorstellingen SET vrijeplaatsen = ? WHERE id=? ";

	public boolean bestel(int klantId, int voorstellingId, int plaatsen,
			int vrijePlaatsen) {
		try (Connection connection = dataSource.getConnection()) {
			connection.setAutoCommit(false);
			if (vrijePlaatsen > plaatsen) {
				PreparedStatement preparedStatement = connection
						.prepareStatement(UPDATE);
				preparedStatement.setInt(1, vrijePlaatsen - plaatsen);
				preparedStatement.setInt(2, voorstellingId);
				if (preparedStatement.executeUpdate() != 0) {
					PreparedStatement preparedStatement2 = connection
							.prepareStatement(INSERT);
					preparedStatement2.setInt(1, klantId);
					preparedStatement2.setInt(2, voorstellingId);
					preparedStatement2.setInt(3, plaatsen);
					if (preparedStatement2.executeUpdate() != 0) {
						connection.commit();
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}

			} else {
				return false;
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}

	}

}
