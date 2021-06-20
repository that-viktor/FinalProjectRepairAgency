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
 * by receipt status
 * 
 * @author Viktor
 *
 */
@WebServlet("/status-filter")
public class StatusFilterServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(StatusFilterServlet.class);

	/**
	 * This method executes the filtering process by status. The filtering process
	 * depends on chosen status parameter from the request
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		long idstatus = Long.parseLong(req.getParameter(SQLConstants.ID_STATUS));
		if (idstatus == 0) {
			req.getRequestDispatcher("/receipts").forward(req, resp);
		} else {
			try {
				req.setAttribute("receipts", ReceiptDAO.getReceiptsByStatus(idstatus));
			} catch (DAOException e1) {
				logger.error("Error getting all the receipts filtered by status id = " + idstatus + "!", e1);
			}
			try {
				req.setAttribute("statuses", StatusDAO.getAllStatuses());
			} catch (DAOException e1) {
				logger.error("Error getting all the statuses after filtering all the receipts by status id = " + idstatus + "!", e1);
			}
			try {
				req.setAttribute("masters", UserDAO.getUsersByRole(SQLConstants.MASTER_ROLE_ID));
			} catch (DAOException e) {
				logger.error("Error getting all the masters after filtering all the receipts by status id = " + idstatus + "!", e);
			}
			req.getRequestDispatcher("view/admin/receipts.jsp").forward(req, resp);
		}

	}
}
