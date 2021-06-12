package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBManager;
import database.SQLConstants;
import database.SQLQueries;
import entities.Status;
import exceptions.StatusException;

public class StatusDAO {
	private static DBManager connector;
	
	public static List<Status> getAllStatuses() throws StatusException {
		connector = DBManager.getInstance();
		List<Status> statuses = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_ALL_STATUSES)) {
			rs = pst.executeQuery();
			while (rs.next()) {
				statuses.add(mapStatus(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StatusException("Error getting all statuses");
		}
		return statuses;
	}
	
	public static Status mapStatus(ResultSet rs) {
		Status s = new Status();
		try {
			s.setId(rs.getLong(SQLConstants.ID_STATUS));
			s.setName(rs.getString(SQLConstants.STATUS_NAME));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
}
