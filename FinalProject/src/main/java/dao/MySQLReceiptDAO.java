package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.DBManager;
import database.SQLConstants;
import database.SQLQueries;
import entities.Receipt;
import entities.ReceiptService;
import entities.User;
import exceptions.DAOException;

/**
 * @author Viktor Shutko
 * @description class that implements special API for working with database
 *              entity 'Receipt'
 * @provides static methods for manipulating 'Receipt' entity
 * @uses Receipt.class, User.class, ReceiptService.class
 */
public class MySQLReceiptDAO {
	private static final Logger logger = LogManager.getLogger(MySQLReceiptDAO.class);
	private static DBManager manager;

	/**
	 * Adds a receipt based on a array of services
	 * 
	 * @param u               - client
	 * @param receiptServices - ReceiptServices.class
	 * @return receiptId, generated by DBMS
	 * @throws DAOException
	 */
	public static long addReceipt(User client, ReceiptService... receiptServices) throws DAOException {
		manager = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement receiptPst = null;
		PreparedStatement receiptServicePst = null;
		ResultSet rs = null;
		Timestamp date = new Timestamp(new Date().getTime());
		long generatedId = 0;
		int k = 0;
		try {
			connection = manager.getConnection();
			connection.setAutoCommit(false);
			receiptPst = connection.prepareStatement(SQLQueries.INSERT_NEW_RECEIPT,
					PreparedStatement.RETURN_GENERATED_KEYS);
			receiptPst.setLong(++k, client.getId());
			receiptPst.setTimestamp(++k, date, DBManager.getCalendar());
			receiptPst.setLong(++k, SQLConstants.INITIAL_STATUS);
			receiptPst.executeUpdate();
			rs = receiptPst.getGeneratedKeys();
			if (rs.next()) {
				generatedId = rs.getLong(1);
			}
			receiptServicePst = connection.prepareStatement(SQLQueries.INSERT_RECEIPT_SERVICE);
			for (int i = 0; i < receiptServices.length; i++) {
				receiptServices[i].setReceiptId(generatedId);
				receiptServicePst.setLong(1, receiptServices[i].getReceiptId());
				receiptServicePst.setLong(2, receiptServices[i].getServiceId());
				receiptServicePst.setDouble(3, receiptServices[i].getServicePrice());
				receiptServicePst.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			DBManager.doRollback(connection);
			logger.error("Error creating a receipt with further rollback!", e);
			throw new DAOException("Error creating a receipt");
		} finally {
			DBManager.close(rs);
			DBManager.close(receiptPst);
			DBManager.close(receiptServicePst);
			DBManager.close(connection);
		}
		return generatedId;
	}

	/**
	 * Sets the admin for the specified receipt
	 * 
	 * @param admin
	 * @param receipt
	 * @throws DAOException
	 */
	public static void setAdminForReceipt(User admin, Receipt receipt) throws DAOException {
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.SET_ADMIN_FOR_RECEIPT);) {
			pst.setLong(1, admin.getId());
			pst.setLong(2, receipt.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error setting the admin " + admin + " for receipt " + receipt + "!", e);
			throw new DAOException("Error setting the admin " + admin + " for receipt " + receipt + "!");
		}
	}

	/**
	 * Sets the master for the specified receipt
	 * 
	 * @param master
	 * @param receipt
	 * @throws DAOException
	 */
	public static void setMasterForReceipt(User master, Receipt receipt) throws DAOException {
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.SET_MASTER_FOR_RECEIPT);) {
			pst.setLong(1, master.getId());
			pst.setLong(2, receipt.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error setting the master " + master + " for receipt " + receipt + "!", e);
			throw new DAOException("Error adding master to receipt");
		}
	}

	/**
	 * Retrieves a receipt from the DB with the specified id
	 * 
	 * @param receipt id
	 * @return Receipt object
	 * @throws DAOException
	 */
	public static Receipt getReceiptById(long id) throws DAOException {
		manager = DBManager.getInstance();
		Receipt r = null;
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_RECEIPT_BY_ID)) {
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				r = mapReceipt(rs);
			} else {
				logger.error("No such receipt with id = " + id);
				throw new DAOException("No such receipt with id = " + id);
			}
		} catch (SQLException e) {
			logger.error("Error getting receipt with id " + id, e);
			throw new DAOException("Error getting receipt");
		} finally {
			DBManager.close(rs);
		}
		return r;
	}

	/**
	 * Retrieves all the receipts from the DB
	 * 
	 * @return List of receipts
	 * @throws DAOException
	 */
	public static List<Receipt> getAllReceipts() throws DAOException {
		manager = DBManager.getInstance();
		List<Receipt> receipts = new ArrayList<>();
		try (Connection connection = manager.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(SQLQueries.GET_ALL_RECEIPTS)) {
			while (rs.next()) {
				receipts.add(mapReceipt(rs));
			}
		} catch (SQLException e) {
			logger.error("Error getting list of receipts!", e);
			throw new DAOException("Error getting list of receipts!");
		}
		return receipts;
	}

	/**
	 * Sets the total sum for all of the services in the receipt
	 * 
	 * @param receipt
	 * @param sum
	 * @throws DAOException
	 */
	public static void setReceiptTotalSum(Receipt r, double sum) throws DAOException {
		if (sum < 0) {
			throw new DAOException("Error. Sum cannot be negative!");
		}
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.SET_RECEIPT_TOTAL_SUM)) {
			pst.setDouble(1, sum);
			pst.setLong(2, r.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error defining receipt " + r + " sum with sum = " + sum, e);
			throw new DAOException("Error defining receipt " + r + " sum with sum = " + sum);
		}
	}

	/**
	 * Retrieves total services sum of the specified receipt
	 * 
	 * @param receipt
	 * @return Sum of the receipt (double)
	 * @throws DAOException
	 */
	public static double getReceiptTotalSum(Receipt r) throws DAOException {
		manager = DBManager.getInstance();
		double totalSum = -1;
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_RECEIPT_TOTAL_SUM)) {
			pst.setLong(1, r.getId());
			rs = pst.executeQuery();
			if (rs.next()) {
				totalSum = rs.getDouble(SQLConstants.TOTAL_SUM);
			}
		} catch (SQLException e) {
			logger.error("Error getting receipt " + r + " total sum!", e);
			throw new DAOException("Error getting receipt " + r.getId() + " total sum!");
		} finally {
			DBManager.close(rs);
		}
		return totalSum;
	}

	private static Receipt mapReceipt(ResultSet rs) throws SQLException {
		Receipt r = new Receipt();
		r.setId(rs.getLong(SQLConstants.ID_RECEIPT));
		r.setAdminId(rs.getLong(SQLConstants.ID_ADMIN));
		r.setMasterId(rs.getLong(SQLConstants.ID_MASTER));
		r.setTotalSum(rs.getDouble(SQLConstants.TOTAL_SUM));
		r.setStatus(rs.getLong(SQLConstants.ID_STATUS));
		r.setUserId(rs.getLong(SQLConstants.ID_USER));
		r.setDate(rs.getTimestamp(SQLConstants.REC_DATE, DBManager.getCalendar()));
		return r;
	}

	/**
	 * Retrieves a client from the specified receipt from DB
	 * 
	 * @param receiptId
	 * @return User (client)
	 * @throws DAOException
	 */
	public static User getReceiptClient(long receiptId) throws DAOException {
		User u = null;
		ResultSet rs = null;
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_RECEIPT_USER)) {
			pst.setLong(1, receiptId);
			rs = pst.executeQuery();
			if (rs.next()) {
				u = MySQLUserDAO.mapUser(rs);
			}
		} catch (SQLException e) {
			logger.error("Error getting receipt " + receiptId + " client!", e);
			throw new DAOException("Error getting receipt " + receiptId + " client!");
		} finally {
			DBManager.close(rs);
		}
		return u;
	}

	/**
	 * Retrieves a admin from the specified receipt from DB
	 * 
	 * @param receiptId
	 * @return User (admin)
	 * @throws DAOException
	 */
	public static User getReceiptAdmin(long receiptId) throws DAOException {
		User u = null;
		ResultSet rs = null;
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_RECEIPT_ADMIN)) {
			pst.setLong(1, receiptId);
			rs = pst.executeQuery();
			if (rs.next()) {
				u = MySQLUserDAO.mapUser(rs);
			}
		} catch (SQLException e) {
			logger.error("Error getting receipt " + receiptId + " admin!", e);
			throw new DAOException("Error getting receipt " + receiptId + " admin!");
		} finally {
			DBManager.close(rs);
		}
		return u;
	}

	/**
	 * Retrieves a master from the specified receipt from DB
	 * 
	 * @param receiptId
	 * @return User (master)
	 * @throws DAOException
	 */
	public static User getReceiptMaster(long receiptId) throws DAOException {
		User u = null;
		ResultSet rs = null;
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_RECEIPT_MASTER)) {
			pst.setLong(1, receiptId);
			rs = pst.executeQuery();
			if (rs.next()) {
				u = MySQLUserDAO.mapUser(rs);
			}
		} catch (SQLException e) {
			logger.error("Error getting receipt " + receiptId + " master!", e);
			throw new DAOException("Error getting receipt " + receiptId + " master!");
		} finally {
			DBManager.close(rs);
		}
		return u;
	}

	/**
	 * Deletes the receipt with specified id
	 * 
	 * @param receipt id
	 * @throws DAOException
	 */
	public static void removeReceiptById(long id) throws DAOException {
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.REMOVE_RECEIPT)) {
			pst.setLong(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error deleting receipt " + id, e);
			throw new DAOException("Error removing receipt with id = " + id + "!");
		}
	}

	/**
	 * Retrieves all the receipts from the DB with specified status
	 * 
	 * @param idstatus
	 * @return List of receipts with the specified status
	 * @throws DAOException
	 */
	public static List<Receipt> getReceiptsByStatus(long idstatus) throws DAOException {
		manager = DBManager.getInstance();
		List<Receipt> receipts = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_RECEIPTS_BY_STATUS)) {
			pst.setLong(1, idstatus);
			rs = pst.executeQuery();
			while (rs.next()) {
				receipts.add(mapReceipt(rs));
			}
		} catch (SQLException e) {
			logger.error("Error getting receipts by status " + idstatus + "!", e);
			throw new DAOException("Error getting receipts by status " + idstatus + "!");
		} finally {
			DBManager.close(rs);
		}
		return receipts;
	}

	/**
	 * Updates the specified receipt by setting the specified status
	 * 
	 * @param idreceipt
	 * @param idstatus
	 * @throws DAOException
	 */
	public static void updateReceiptStatusByReceiptId(long idreceipt, int idstatus) throws DAOException {
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.UPDATE_RECEIPT_STATUS)) {
			pst.setInt(1, idstatus);
			pst.setLong(2, idreceipt);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error updating receipt no " + idreceipt, e);
			throw new DAOException("Error updating receipt no " + idreceipt);
		}
	}

	/**
	 * Retrieves all the receipts form DB ordered by date
	 * 
	 * @param DESC if true, returns the Receipts ordered by date descending
	 * @return Ordered List of receipts
	 * @throws DAOException
	 */
	public static List<Receipt> getAllReceiptsOrderedByDate(boolean DESC) throws DAOException {
		manager = DBManager.getInstance();
		List<Receipt> receipts = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pst = null;
		try (Connection connection = manager.getConnection()) {
			if (DESC) {
				pst = connection.prepareStatement(SQLQueries.GET_RECEIPTS_ORDER_BY_DATE_DESC);
			} else {
				pst = connection.prepareStatement(SQLQueries.GET_RECEIPTS_ORDER_BY_DATE_ASC);
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				receipts.add(mapReceipt(rs));
			}
		} catch (SQLException e) {
			logger.error("Error getting receipts ordered by date!", e);
			throw new DAOException("Error getting receipts ordered by date!");
		} finally {
			DBManager.close(rs);
		}
		return receipts;
	}

	/**
	 * Retrieves all the receipts from DB ordered by receipt total sum
	 * 
	 * @param DESC if true, returns records in the descending order
	 * @return List of receipts ordered by receipt sum
	 * @throws DAOException
	 */
	public static List<Receipt> getAllReceiptsOrderedByReceiptSum(boolean DESC) throws DAOException {
		manager = DBManager.getInstance();
		List<Receipt> receipts = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pst = null;
		try (Connection connection = manager.getConnection()) {
			if (DESC) {
				pst = connection.prepareStatement(SQLQueries.GET_RECEIPTS_ORDER_BY_RECEIPT_SUM_DESC);
			} else {
				pst = connection.prepareStatement(SQLQueries.GET_RECEIPTS_ORDER_BY_RECEIPT_SUM_ASC);
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				receipts.add(mapReceipt(rs));
			}
		} catch (SQLException e) {
			logger.error("\"Error getting receipts ordered by date!", e);
			throw new DAOException("Error getting receipts ordered by date!");
		} finally {
			DBManager.close(pst);
			DBManager.close(rs);
		}
		return receipts;
	}

	/**
	 * Retrieves all the records of the specified master
	 * 
	 * @param masterId
	 * @return List of receipts ordered by master
	 * @throws DAOException
	 */
	public static List<Receipt> getAllReceiptsFilteredByMaster(long masterId) throws DAOException {
		manager = DBManager.getInstance();
		List<Receipt> receipts = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_RECEIPTS_FILTERED_BY_ID_MASTER)) {
			pst.setLong(1, masterId);
			rs = pst.executeQuery();
			while (rs.next()) {
				receipts.add(mapReceipt(rs));
			}
		} catch (SQLException e) {
			logger.error("Error getting receipts by master id = " + masterId, e);
			throw new DAOException("Error getting receipts by master id = " + masterId);
		} finally {
			DBManager.close(rs);
		}
		return receipts;
	}

	/**
	 * Removes the master from the specified receipt
	 * 
	 * @param receipt id
	 * @throws DAOException
	 */
	public static void removeMasterByReceiptId(long id) throws DAOException {
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.REMOVE_MASTER_BY_ID_RECEIPT)) {
			pst.setLong(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error removing master from receipt with id = " + id, e);
			throw new DAOException("Error removing master from receipt with id = " + id);
		}
	}

	/**
	 * Retrieves all client's receipts from DB
	 * 
	 * @param user id
	 * @return List of client's receipts
	 * @throws DAOException
	 */
	public static List<Receipt> getClientReceipts(long id) throws DAOException {
		manager = DBManager.getInstance();
		List<Receipt> receipts = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_USER_RECEIPTS)) {
			pst.setLong(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				receipts.add(mapReceipt(rs));
			}
		} catch (SQLException e) {
			logger.error("Erorr getting receipts by iduser = " + id, e);
			throw new DAOException("Erorr getting receipts by iduser = " + id);
		} finally {
			DBManager.close(rs);
		}
		return receipts;
	}

	/**
	 * Retrieves all the receipts of the specified master from DB
	 * 
	 * @param master id
	 * @return List of master receipts
	 * @throws DAOException
	 */
	public static List<Receipt> getMasterReceipts(long id) throws DAOException {
		manager = DBManager.getInstance();
		List<Receipt> receipts = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_ALL_MASTER_RECEIPTS)) {
			pst.setLong(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				receipts.add(mapReceipt(rs));
			}
		} catch (SQLException e) {
			logger.error("Cannot get all the receipts with master id = " + id + "!", e);
			throw new DAOException("Cannot get all the receipts with master id = " + id + "!");
		}
		return receipts;
	}

}