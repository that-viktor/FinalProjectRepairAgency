package database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.AccountDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * The DBManager class provides util methods for DB interconnection
 * @author Viktor
 *
 */
public class DBManager {
	private static Connection connection = null;
	private static DBManager instance = null;
	private DataSource ds = null;
	private static final String actualTimezone = "GMT+3";
	private static final Logger logger = LogManager.getLogger(DBManager.class);
	
	/**
	 * A method that provides synchronized connection to the MySQL database (DB)
	 * @return Connection object
	 * @throws SQLException
	 */
	public synchronized Connection getConnection() throws SQLException {
		connection = ds.getConnection();
		return connection;
	}
	
	/**
	 * A factory method that returns an instance of DBManager class as a singletone
	 * @return DBManager instance
	 */
	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	private DBManager() {
		try {
			Context context = (Context) new InitialContext().lookup("java:/comp/env");
			ds = (DataSource) context.lookup("jdbc/mysql");
		} catch (NamingException e) {
			logger.error(e);
		}
	}
	
	/**
	 * A wrapper on the {@link Connection#rollback()} method that handles the SQLException
	 * @param connection
	 */
	public static void doRollback(Connection connection) {
		try {
			connection.rollback();
		} catch (SQLException e) {
			logger.error("Error doing a rollback", e);
		}
	}
	
	/**
	 * A wrapper on the {@link AutoCloseable#close()} method that handles SQLException
	 * @param closeable
	 */
	public static void close(AutoCloseable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}
	
	/**
	 * A method that returns calendar of the actual time zone used in DB
	 * @return Calendar
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance(TimeZone.getTimeZone(actualTimezone));
	}

}