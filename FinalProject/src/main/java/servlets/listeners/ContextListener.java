package servlets.listeners;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.DBManager;

/**
 * ContextListener class is used for checking if connection is OK. Otherwise the
 * application won't start
 * 
 * @author Viktor
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger(ContextListener.class);

	/**
	 * This method checks if connection is valid. Otherwise RuntimeException is
	 * thrown.
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DBManager manager = DBManager.getInstance();
		Connection con = null;
		try {
			con = manager.getConnection();
		} catch (SQLException e) {
			logger.error("No DB connection");
			throw new RuntimeException();
		}
	}
}
