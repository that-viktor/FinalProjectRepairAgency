package servlets.filters;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.MySQLReceiptDAO;
import dao.MySQLStatusDAO;
import dao.MySQLUserDAO;
import database.SQLConstants;
import exceptions.DAOException;

/**
 * The MasterFilterServlet implements functionality of filtering all the
 * receipts by master processing them
 * 
 * @author Viktor
 *
 */
@WebServlet("/master-filter")
public class MasterFilterServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(MasterFilterServlet.class);

	/**
	 * This method executes the filtering process by master. The order depends on
	 * master unique identifier (id) in the DB
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter(SQLConstants.ID_MASTER));
		try {
			req.setAttribute("receipts", MySQLReceiptDAO.getAllReceiptsFilteredByMaster(id));
		} catch (DAOException e2) {
			logger.error("Error getting all the receipts filtered by master id = " + id + "!", e2);
		}
		try {
			req.setAttribute("statuses", MySQLStatusDAO.getAllStatuses());
		} catch (DAOException e1) {
			logger.error("Error getting all the statuses after filtering all the receipts by master id = " + id + "!",
					e1);
		}
		try {
			req.setAttribute("masters", MySQLUserDAO.getUsersByRole(SQLConstants.MASTER_ROLE_ID));

		} catch (DAOException e) {
			logger.error("Error getting all the users by role id = " + SQLConstants.MASTER_ROLE_ID
					+ " after filtering all the receipts by master!", e);
		}
		req.getRequestDispatcher("view/admin/receipts.jsp").forward(req, resp);
	}
}
