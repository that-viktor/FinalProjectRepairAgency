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
import exceptions.AccountException;
import exceptions.UserException;

public class UserDAO {
	private static final Logger userLog = LogManager.getLogger(UserDAO.class);
	
	private static DBManager connector;
	
	public static List<User> getAllUsers() throws UserException {
		connector = DBManager.getInstance();
		List<User> users = new ArrayList<>();
		try (Connection connection = connector.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(SQLQueries.GET_ALL_USERS)) {
			while (rs.next()) {
				users.add(mapUser(rs));
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
			throw new UserException("Error getting users!");
		}
		return users;
	}

	public static User getUserById(long id) throws UserException {
		connector = DBManager.getInstance();
		User u;
		try (Connection connection = connector.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_USER_BY_ID)) {
			pst.setLong(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				rs.next();
				u = mapUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("User not found!");
		}
		return u;
	}

	public static User getUserByLogin(String login) throws UserException {
		connector = DBManager.getInstance();
		User u = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_USER_BY_LOGIN)) {
			pst.setString(1, login);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					u = mapUser(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Error getting user!");
		}
		return u;
	}

	public static List<User> getUsersByRole(int roleId) throws UserException {
		connector = DBManager.getInstance();
		List<User> users = new ArrayList<>();
		try (Connection connection = connector.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_USERS_BY_ROLE)) {
			pst.setInt(1, roleId);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					users.add(mapUser(rs));
				}
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
			throw new UserException("Error selecting users");
		}
		return users;
	}

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

	public static void insertUser(User u) throws UserException, AccountException {
		connector = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = connector.getConnection();
			connection.setAutoCommit(false);
			pst = setUser(u, connection);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBManager.doRollback(connection);
			throw new UserException("Error inserting the user");
		} finally {
			DBManager.close(pst);
			DBManager.close(connection);
		}
	}

	private static PreparedStatement setUser(User u, Connection connection)
			throws SQLException, AccountException {
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
