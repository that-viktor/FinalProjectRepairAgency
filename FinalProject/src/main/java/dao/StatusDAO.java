package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.DBManager;
import database.SQLConstants;
import database.SQLQueries;
import entities.Receipt;
import entities.Status;
import exceptions.DAOException;

/**
 * StatusDAO class provides API for manipulating DB entity REC_STATUS like Java
 * objects
 * 
 * @author Viktor
 *
 */
public class StatusDAO {
	private static DBManager manager;
	private static final Logger logger = LogManager.getLogger(StatusDAO.class);

	/**
	 * Retrieves all the statuses from the DB
	 * 
	 * @return List of statuses
	 * @throws DAOException
	 */
	public static List<Status> getAllStatuses() throws DAOException {
		manager = DBManager.getInstance();
		List<Status> statuses = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_ALL_STATUSES)) {
			rs = pst.executeQuery();
			while (rs.next()) {
				statuses.add(mapStatus(rs));
			}
		} catch (SQLException e) {
			logger.error("Error getting all the statuses", e);
			throw new DAOException("Error getting all statuses");
		}
		return statuses;
	}

	/**
	 * Maps the result set to the Status object
	 * 
	 * @param result set
	 * @return Status object
	 * @throws SQLException
	 */
	public static Status mapStatus(ResultSet rs) throws SQLException {
		Status s = new Status();
		s.setId(rs.getLong(SQLConstants.ID_STATUS));
		s.setName(rs.getString(SQLConstants.STATUS_NAME));
		return s;
	}

	/**
	 * Converts the receipt numerical status representation (status id) to the string one in order to its name in DB
	 * @param masterReceipt
	 * @return Status name as string
	 * @throws DAOException
	 */
	public static String getReceiptStatusAsString(Receipt masterReceipt) throws DAOException {
		if (masterReceipt.getStatus() == SQLConstants.STATUS_WAITING_FOR_PAYMENT_ID) {
			return SQLConstants.STATUS_WAITING_FOR_PAYMENT;
		}
		if (masterReceipt.getStatus() == SQLConstants.STATUS_PAYED_ID) {
			return SQLConstants.STATUS_PAYED;
		}
		if (masterReceipt.getStatus() == SQLConstants.STATUS_CANCELLED_ID) {
			return SQLConstants.STATUS_CANCELLED;
		}
		if (masterReceipt.getStatus() == SQLConstants.STATUS_PROCESSING_ID) {
			return SQLConstants.STATUS_PROCESSING;
		}
		if (masterReceipt.getStatus() == SQLConstants.STATUS_PROCESSED_ID) {
			return SQLConstants.STATUS_PROCESSED;
		} else {
			logger.error("Unknown status for receipt " + masterReceipt);
			throw new DAOException("Unknown status for receipt " + masterReceipt);
		}
	}
}
