package servlets.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.MySQLReceiptDAO;
import dao.MySQLServiceDAO;
import database.SQLConstants;
import entities.Service;
import exceptions.DAOException;

/**
 * ServiceSumServlet class provides functionality of defining receipt's sum
 * depending on receipt services
 * 
 * @author Viktor
 *
 */
@WebServlet("/count")
public class ServiceSumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ServiceSumServlet.class);

	/**
	 * This method retrieves a list of services got by receipt id from the request.
	 * Iterating over this list and counting receipt total sum
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = 0;
		try {
			id = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
			List<Service> services = MySQLServiceDAO.getReceiptServices(id);
			req.setAttribute("services", services);
			double totalSum = 0;
			for (Service s : MySQLServiceDAO.getReceiptServices(id)) {
				totalSum += s.getPrice();
			}
			MySQLReceiptDAO.setReceiptTotalSum(MySQLReceiptDAO.getReceiptById(id), totalSum);
			req.setAttribute("receipt", MySQLReceiptDAO.getReceiptById(id));
			req.setAttribute("client", MySQLReceiptDAO.getReceiptClient(id));
			req.setAttribute("admin", MySQLReceiptDAO.getReceiptAdmin(id));
			req.setAttribute("master", MySQLReceiptDAO.getReceiptMaster(id));
			req.getRequestDispatcher("view/admin/receiptsInfo.jsp").forward(req, resp);
		} catch (DAOException e) {
			logger.error("Error defining receipt " + id + " total sum", e);
		}
	}

}
