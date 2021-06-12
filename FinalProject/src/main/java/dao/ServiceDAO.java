package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DBManager;
import database.SQLConstants;
import database.SQLQueries;
import entities.Receipt;
import entities.Service;
import exceptions.ReceiptException;
import exceptions.ServiceException;

public class ServiceDAO {
	private static DBManager connector;

	public static List<Service> getAllServices() throws ServiceException {
		List<Service> services = new ArrayList<>();
		connector = DBManager.getInstance();
		try (Connection connection = connector.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_SERVICES)) {
			while (rs.next()) {
				services.add(mapService(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("Error while recieving a list of services!");
		}
		return services;
	}

	public static Service getServiceById(long id) throws ServiceException {
		connector = DBManager.getInstance();
		Service s = new Service();
		ResultSet rs = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_SERVICE_BY_ID)) {
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				s.setId(rs.getLong(SQLConstants.ID_SERVICE));
				s.setName(rs.getString(SQLConstants.SERVICE_NAME));
				s.setPrice(rs.getDouble(SQLConstants.PRICE_PER_UNIT));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("Error searching service!");
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

	public static List<Service> getReceiptServices(long id) throws ServiceException {
		connector = DBManager.getInstance();
		List<Service> services = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_RECEIPT_SERVICES);) {
			pst.setLong(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				services.add(mapService(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("Error getting receipt id = " + id + " services!");
		}
		return services;
	}
	
	public static List<Service> getServicesLimited(int offset) throws ServiceException {
		connector = DBManager.getInstance();
		List<Service> receipts = new ArrayList<>();
		ResultSet rs = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_SERVICES_LIMITED)) {
			pst.setInt(1, SQLConstants.LIMIT);
			pst.setInt(2, offset);
			rs = pst.executeQuery();
			while (rs.next()) {
				receipts.add(mapService(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("Error getting receipts limited by " + SQLConstants.LIMIT + " with offset " + offset);
		}
		return receipts;
	}
}
