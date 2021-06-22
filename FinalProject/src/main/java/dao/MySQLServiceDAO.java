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
import entities.Receipt;
import entities.Service;
import exceptions.DAOException;
/**
 * MySQLServiceDAO provides API for manipulating DB entity Service like Java object
 * @author Viktor
 *
 */

public class MySQLServiceDAO {
	private static final Logger logger = LogManager.getLogger(MySQLServiceDAO.class);
	private static DBManager manager;
	
	/**
	 * Retrieves all the services from the DB
	 * @return List of services
	 * @throws DAOException
	 */
	public static List<Service> getAllServices() throws DAOException {
		List<Service> services = new ArrayList<>();
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_SERVICES)) {
			while (rs.next()) {
				services.add(mapService(rs));
			}
		} catch (SQLException e) {
			logger.error("Error while retrieving a list of services!", e);
			throw new DAOException("Error while retrieving a list of services!");
		}
		return services;
	}
	
	/**
	 * Retrieves a service from DB by a specified id
	 * @param service id
	 * @return Service object
	 * @throws DAOException
	 */
	public static Service getServiceById(long id) throws DAOException {
		manager = DBManager.getInstance();
		Service s = new Service();
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_SERVICE_BY_ID)) {
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				s.setId(rs.getLong(SQLConstants.ID_SERVICE));
				s.setName(rs.getString(SQLConstants.SERVICE_NAME));
				s.setPrice(rs.getDouble(SQLConstants.PRICE_PER_UNIT));
			}
		} catch (SQLException e) {
			logger.error("Error getting service by id " + id, e);
			throw new DAOException("Error getting service by id " + id);
		} finally {
			DBManager.close(rs);
		}
		return s;
	}

	private static Service mapService(ResultSet rs) throws SQLException {
		Service s = new Service();
		s.setId(rs.getLong(SQLConstants.ID_SERVICE));
		s.setName(rs.getString(SQLConstants.SERVICE_NAME));
		s.setPrice(rs.getDouble(SQLConstants.PRICE_PER_UNIT));
		return s;
	}
	
	/**
	 * Returns all the services of the specified receipt
	 * @param receipt id
	 * @return List of services
	 * @throws DAOException
	 */
	public static List<Service> getReceiptServices(long id) throws DAOException {
		manager = DBManager.getInstance();
		List<Service> services = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_RECEIPT_SERVICES);) {
			pst.setLong(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				services.add(mapService(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error getting receipt id = " + id + " services!");
		}
		return services;
	}
	
	/**
	 * Retrieves services from the DB limited by parameter LIMIT (default LIMIT = 5) using offset
	 * @param offset
	 * @return List of services limited by LIMIT
	 * @throws DAOException
	 */
	public static List<Service> getServicesLimited(int offset) throws DAOException {
		manager = DBManager.getInstance();
		List<Service> receipts = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_SERVICES_LIMITED)) {
			pst.setInt(1, SQLConstants.LIMIT);
			pst.setInt(2, offset);
			rs = pst.executeQuery();
			while (rs.next()) {
				receipts.add(mapService(rs));
			}
		} catch (SQLException e) {
			logger.error("Error getting receipts limited by " + SQLConstants.LIMIT + " with offset " + offset + "!", e);
			throw new DAOException("Error getting receipts limited by " + SQLConstants.LIMIT + " with offset " + offset);
		}
		return receipts;
	}
	
	/**
	 * Returns the price of the service with specified id
	 * @param service id
	 * @return Service price (double)
	 * @throws DAOException
	 */
	public static double getServicePriceById(long id) throws DAOException {
		double price = 0;
		manager = DBManager.getInstance();
		ResultSet rs = null;
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_SERVICE_PRICE_BY_ID)) {
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				price = rs.getDouble(SQLConstants.PRICE_PER_UNIT);
			}
		} catch (SQLException e) {
			logger.error("Error gettting price of service with id = " + id, e);
			throw new DAOException("Error gettting price of service  with id = " + id);
		}
		return price;
	}
}
