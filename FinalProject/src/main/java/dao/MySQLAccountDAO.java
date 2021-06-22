package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.DBManager;
import database.SQLConstants;
import database.SQLQueries;
import entities.Account;
import entities.User;
import exceptions.DAOException;

/**
 * MySQLAccountDAO class provides API for manipulating users accounts entities like a
 * Java objects
 * 
 * @author Viktor
 *
 */
public class MySQLAccountDAO {
	private static DBManager manager;
	private static final Logger logger = LogManager.getLogger(MySQLAccountDAO.class);

	/**
	 * Creates new account for hot-registered user
	 * 
	 * @param connection
	 * @return generated account id
	 * @throws DAOException
	 */
	public static int createNewAccount(Connection connection) throws DAOException {
		int accountId = 0;
		try (PreparedStatement pst = connection.prepareStatement(SQLQueries.INSERT_NEW_ACCOUNT,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			pst.setDouble(1, 0);
			pst.executeUpdate();
			try (ResultSet rs = pst.getGeneratedKeys()) {
				if (rs.next()) {
					accountId = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error creating new account!", e);
			throw new DAOException("Error creating new account!");
		}
		return accountId;
	}

	/**
	 * Ups user account balance for the specified sum > 0
	 * 
	 * @param u
	 * @param sum
	 * @return true if account balance was successfully increased
	 * @throws DAOException
	 */
	public static boolean topUpBalance(User u, double sum) throws DAOException {
		manager = DBManager.getInstance();
		if (sum <= 0) {
			throw new DAOException("Invalid operation. Sum to increase must be > 0");
		}
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.TOP_UP_BALANCE)) {
			pst.setDouble(1, sum);
			pst.setLong(2, u.getAccountId());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Error increasing user" + u + " account balance by " + sum, e);
			throw new DAOException("Error increasing user" + u + " account balance by " + sum);
		}
	}

	private static double getAccountBalance(User u, Connection connection) throws DAOException {
		double balance = 0;
		ResultSet rs = null;
		try (PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_ACCOUNT_BALANCE)) {
			pst.setLong(1, u.getAccountId());
			rs = pst.executeQuery();
			if (rs.next()) {
				balance = rs.getDouble(SQLConstants.BALANCE);
			}
		} catch (SQLException ex) {
			logger.error("Error getting " + u + " account balance", ex);
			throw new DAOException("Error getting " + u + " account balance");
		} finally {
			DBManager.close(rs);
		}
		return balance;
	}

	/**
	 * Debts the specified user's account for a specified sum
	 * 
	 * @param u
	 * @param sum
	 * @return true, if operation was successful
	 * @throws DAOException
	 */
	public static boolean debitTheAccount(User u, double sum) throws DAOException {
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.TOP_UP_BALANCE)) {
			if (sum > getAccountBalance(u, connection)) {
				logger.error("Insufficient funds for the users " + u + " account!");
				throw new DAOException("Insufficient funds for the users " + u + " account!");
			}
			pst.setDouble(1, -sum);
			pst.setLong(2, u.getAccountId());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Error debiting user " + u + " account by " + sum, e);
			throw new DAOException("Error debiting balance");
		}
	}

	/**
	 * Retrieves account from DB by its id
	 * 
	 * @param id
	 * @return Account object
	 * @throws DAOException
	 */
	public static Account getAccountById(long id) throws DAOException {
		manager = DBManager.getInstance();
		Account account = new Account();
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_ACCOUNT_BY_ID)) {
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				account.setAccountId(rs.getLong(SQLConstants.ACCOUNT));
				account.setBalance(rs.getDouble(SQLConstants.BALANCE));
			}
			return account;
		} catch (SQLException e) {
			logger.error("Error getting account with id = " + id, e);
			throw new DAOException("Error getting account with id = ");
		} finally {
			DBManager.close(rs);
		}
	}
}
