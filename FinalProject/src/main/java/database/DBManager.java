package database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
	private static Connection connection = null;
	private static DBManager instance = null;
	private DataSource ds = null;
	
	public synchronized Connection getConnection() throws SQLException {
		connection = ds.getConnection();
		return connection;
	}
	
	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new  DBManager();
		}
		return instance;
	}

	private DBManager() {
		try {
			Context context = (Context) new InitialContext().lookup("java:/comp/env");
			ds = (DataSource) context.lookup("jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static void doRollback(Connection connection) {
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(AutoCloseable closeable) {
		try {
			closeable.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}