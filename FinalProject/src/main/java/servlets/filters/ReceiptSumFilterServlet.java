package servlets.filters;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ReceiptDAO;
import dao.StatusDAO;
import dao.UserDAO;
import database.SQLConstants;
import exceptions.DAOException;

/**
 * The DateFilerServlet implements functionality of filtering all the receipts
 * by receipt total sum
 * 
 * @author Viktor
 *
 */
@WebServlet("/sum-filter")
public class ReceiptSumFilterServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ReceiptSumFilterServlet.class);

	/**
	 * This method executes the filtering process by receipt total sum. Ascending or
	 * descending order depends on the request boolean parameter DESC
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filter = req.getParameter(SQLConstants.TOTAL_SUM);
		if (filter.equals(SQLConstants.ASC)) {
			try {
				req.setAttribute("receipts", ReceiptDAO.getAllReceiptsOrderedByReceiptSum(false));
			} catch (DAOException e) {
				logger.error("Error getting all the receipts filtered by receipt sum ascending!", e);
			}
		}
		if (filter.equals(SQLConstants.DESC)) {
			try {
				req.setAttribute("receipts", ReceiptDAO.getAllReceiptsOrderedByReceiptSum(true));
			} catch (DAOException e) {
				logger.error("Error getting all the receipts filtered by receipt sum descending!", e);
			}
		}
		try {
			req.setAttribute("statuses", StatusDAO.getAllStatuses());
		} catch (DAOException e) {
			logger.error("Error getting all the statuses after filtering all the receipts by receipt sum!", e);
		}
		try {
			req.setAttribute("masters", UserDAO.getUsersByRole(SQLConstants.MASTER_ROLE_ID));
		} catch (DAOException e) {
			logger.error("Error getting all the masters after filtering all the receipts by receipt sum!", e);
		}
		req.getRequestDispatcher("view/admin/receipts.jsp").forward(req, resp);
	}
}
