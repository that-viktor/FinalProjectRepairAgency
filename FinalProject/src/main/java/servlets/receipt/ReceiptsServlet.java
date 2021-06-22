package servlets.receipt;

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
 * ReceiptsServlet class provides access to the JSP page that contains all the
 * receipts. This access could be provided to the administrator only
 * 
 * @author Viktor
 *
 */
@WebServlet("/receipts")
public class ReceiptsServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ReceiptsServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			try {
				req.setAttribute("statuses", MySQLStatusDAO.getAllStatuses());
				req.setAttribute("receipts", MySQLReceiptDAO.getAllReceipts());
				req.setAttribute("masters", MySQLUserDAO.getUsersByRole(SQLConstants.MASTER_ROLE_ID));
				req.getRequestDispatcher("view/admin/receipts.jsp").forward(req, resp);
			} catch (DAOException e) {
				logger.error("Error getting receipts!", e);
			}
		}

	}
}
