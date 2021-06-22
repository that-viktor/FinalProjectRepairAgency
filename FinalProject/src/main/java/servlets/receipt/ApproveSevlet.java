package servlets.receipt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.MySQLReceiptDAO;
import dao.MySQLServiceDAO;
import database.SQLConstants;
import entities.Receipt;
import entities.User;
import exceptions.DAOException;

/**
 * The ApproveSevlet class provides method for approving a particular receipt
 * 
 * @author Viktor
 *
 */
@WebServlet("/approve")
public class ApproveSevlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ApproveSevlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		User u = (User) s.getAttribute("admin");
		Receipt r = null;
		long idReceipt = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		try {
			r = MySQLReceiptDAO.getReceiptById(idReceipt);
		} catch (DAOException e) {
			logger.error("Error getting receipt by id = " + idReceipt, e);
		}
		try {
			MySQLReceiptDAO.setAdminForReceipt(u, r);
		} catch (DAOException e) {
			logger.error("Error setting admin " + u + " for the receipt " + r, e);
		}
		try {
			req.setAttribute("receipt", MySQLReceiptDAO.getReceiptById(idReceipt));
			req.setAttribute("client", MySQLReceiptDAO.getReceiptClient(r.getId()));
			req.setAttribute("admin", MySQLReceiptDAO.getReceiptAdmin(r.getId()));
			req.setAttribute("master", MySQLReceiptDAO.getReceiptMaster(r.getId()));
			req.setAttribute("services", MySQLServiceDAO.getReceiptServices(idReceipt));
			req.getRequestDispatcher("view/admin/receiptsInfo.jsp").forward(req, resp);
		} catch (DAOException e) {
			logger.error("Error setting necessary info for the JSP page", e);
		}
	}

}
