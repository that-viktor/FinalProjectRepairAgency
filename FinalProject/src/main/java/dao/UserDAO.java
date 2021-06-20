package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.DBManager;
import database.SQLConstants;
import database.SQLQueries;
import entities.User;
import exceptions.DAOException;

/***
 * The UserDAO class provides the API for manipulating User objects. With this
 * class you can put and retrieve User object to the DB and vice versa.
 * 
 * @author Viktor
 */
public class UserDAO {
	private static final Logger logger = LogManager.getLogger(UserDAO.class);

	private static DBManager manager;

	/**
	 * Retrieves all users records from the DB and puts it to the List
	 * 
	 * @return ArrayList of Users or empty list
	 * @throws DAOException if error getting users from DB
	 */
	public static List<User> getAllUsers() throws DAOException {
		manager = DBManager.getInstance();
		List<User> users = new ArrayList<>();
		try (Connection connection = manager.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(SQLQueries.GET_ALL_USERS)) {
			while (rs.next()) {
				users.add(mapUser(rs));
			}
		} catch (SQLException sql) {
			logger.error("Error getting users", sql);
			throw new DAOException("Error getting users!");
		}
		return users;
	}

	/**
	 * Retrieves a user from DB by id
	 * 
	 * @param id - user id by what you can retrieve a user from DB
	 * @return User object, null
	 * @throws DAOException if there is no user with such id
	 */
	public static User getUserById(long id) throws DAOException {
		manager = DBManager.getInstance();
		User u = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_USER_BY_ID)) {
			pst.setLong(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				rs.next();
				u = mapUser(rs);
			}
		} catch (SQLException e) {
			logger.error("User not found!", e);
			throw new DAOException("User not found!");
		}
		return u;
	}

	/**
	 * Retrieves a user from DB by login
	 * 
	 * @param login
	 * @return User object, null
	 * @throws DAOException if there is error finding such a user
	 */
	public static User getUserByLogin(String login) throws DAOException {
		manager = DBManager.getInstance();
		User u = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_USER_BY_LOGIN)) {
			pst.setString(1, login);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					u = mapUser(rs);
				}
			}
		} catch (SQLException e) {
			logger.error("User with login = " + login + " not found!", e);
			throw new DAOException("User with login = " + login + " not found!");
		}
		return u;
	}

	/**
	 * Retrieves all users with specified role
	 * 
	 * @param roleId
	 * @return List of users, empty list
	 * @throws DAOException if there is an error getting users with this role
	 */
	public static List<User> getUsersByRole(int roleId) throws DAOException {
		manager = DBManager.getInstance();
		List<User> users = new ArrayList<>();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_USERS_BY_ROLE)) {
			pst.setInt(1, roleId);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					users.add(mapUser(rs));
				}
			}
		} catch (SQLException sql) {
			logger.error("Users with role = " + roleId + " not found!", sql);
			throw new DAOException("Users with role = " + roleId + " not found!");
		}
		return users;
	}

	/**
	 * Utility method for mapping ResultSet to User object
	 * @param rs
	 * @return mapped user
	 * @throws SQLException
	 */
	public static User mapUser(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getLong(SQLConstants.ID_USER));
		u.setLogin(rs.getString(SQLConstants.LOGIN));
		u.setPassword(rs.getString(SQLConstants.PASSWORD));
		u.setRoleId(rs.getLong(SQLConstants.ROLE));
		u.setAccountId(rs.getLong(SQLConstants.ACCOUNT));
		u.setFirstName(rs.getString(SQLConstants.FIRST_NAME));
		u.setSurname(rs.getString(SQLConstants.SURNAME));
		u.setLastName(rs.getString(SQLConstants.LAST_NAME));
		u.setEmail(rs.getString(SQLConstants.EMAIL));
		u.setPhoneNum(rs.getString(SQLConstants.PHONE_NUM));
		return u;
	}

	/**
	 * Inserts the specified user to the DB
	 * @param u
	 * @throws DAOException
	 */
	public static void insertUser(User u) throws DAOException {
		manager = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = manager.getConnection();
			connection.setAutoCommit(false);
			pst = setUser(u, connection);
			connection.commit();
		} catch (SQLException e) {
			DBManager.doRollback(connection);
			logger.error("Error inserting user " + u);
			throw new DAOException("Error inserting user " + u);
		} finally {
			DBManager.close(pst);
			DBManager.close(connection);
		}
	}

	private static PreparedStatement setUser(User u, Connection connection) throws SQLException, DAOException {
		int counter = 0;
		PreparedStatement pst = null;
		pst = connection.prepareStatement(SQLQueries.INSERT_USER);
		pst.setString(++counter, u.getLogin());
		pst.setString(++counter, u.getPassword());
		pst.setLong(++counter, u.getRoleId());
		pst.setInt(++counter, AccountDAO.createNewAccount(connection));
		pst.setString(++counter, u.getFirstName());
		pst.setString(++counter, u.getSurname());
		pst.setString(++counter, u.getLastName());
		pst.setString(++counter, u.getEmail());
		pst.setString(++counter, u.getPhoneNum());
		pst.executeUpdate();
		return pst;
	}

}
