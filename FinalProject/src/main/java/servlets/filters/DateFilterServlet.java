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
import servlets.comment.DeleteCommentServlet;

/**
 * The DateFilerServlet implements functionality of filtering all the receipts
 * by date
 * 
 * @author Viktor
 *
 */
@WebServlet("/date-filter")
public class DateFilterServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(DateFilterServlet.class);

	/**
	 * This method executes the filtering process by date. Ascending or descending
	 * order depends on the request boolean parameter DESC
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filter = req.getParameter(SQLConstants.REC_DATE);
		if (filter.equals(SQLConstants.ASC)) {
			try {
				req.setAttribute("receipts", MySQLReceiptDAO.getAllReceiptsOrderedByDate(false));
			} catch (DAOException e) {
				logger.error("Error getting all the receipts ordered by date ascending!", e);
			}
		}
		if (filter.equals(SQLConstants.DESC)) {
			try {
				req.setAttribute("receipts", MySQLReceiptDAO.getAllReceiptsOrderedByDate(true));
			} catch (DAOException e) {
				logger.error("Error getting all the receipts ordered by date descending!", e);
			}
		}
		try {
			req.setAttribute("statuses", MySQLStatusDAO.getAllStatuses());
		} catch (DAOException e) {
			logger.error("Error getting all the statuses after filtering by date!", e);
		}
		try {
			req.setAttribute("masters", MySQLUserDAO.getUsersByRole(SQLConstants.MASTER_ROLE_ID));
		} catch (DAOException e) {
			logger.error(
					"Error getting all users by role id " + SQLConstants.MASTER_ROLE_ID + " after filtering by date!",
					e);
		}
		req.getRequestDispatcher("view/admin/receipts.jsp").forward(req, resp);
	}
}
