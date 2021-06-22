package servlets.master;

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
import dao.MySQLUserDAO;
import database.SQLConstants;
import entities.Receipt;
import exceptions.DAOException;

/**
 * SetMasterServlet class implements functionality of setting chosen master to
 * the particular receipt
 * 
 * @author Viktor
 *
 */
@WebServlet("/setmaster")
public class SetMasterServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(SetMasterServlet.class);

	/**
	 * This method sets the master, got by id passed as a request parameter, to the
	 * particular receipt
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		Receipt r = (Receipt) s.getAttribute("receipt_for_master");
		long idreceipt = r.getId();
		long idmaster = Long.parseLong(req.getParameter(SQLConstants.ID_MASTER));
		
			try {
				MySQLReceiptDAO.setMasterForReceipt(MySQLUserDAO.getUserById(idmaster), r);
			} catch (DAOException e1) {
				logger.error("Error setting master from receipt id = " + r.getId() + " and master id = " + idmaster, e1);
			}
			try {
				req.setAttribute("receipt", MySQLReceiptDAO.getReceiptById(idreceipt));
			} catch (DAOException e1) {
				logger.error("Error getting receipt by receipt id = " + idreceipt, e1);
			}
			try {
				req.setAttribute("client", MySQLReceiptDAO.getReceiptClient(idreceipt));
			} catch (DAOException e1) {
				logger.error("Error getting receipt client by receipt id = " + idreceipt, e1);
			}
			try {
				req.setAttribute("admin", MySQLReceiptDAO.getReceiptAdmin(idreceipt));
			} catch (DAOException e1) {
				logger.error("Error getting receipt admin by receipt id = " + idreceipt, e1);
			}
			try {
				req.setAttribute("master", MySQLReceiptDAO.getReceiptMaster(idreceipt));
			} catch (DAOException e1) {
				logger.error("Error getting receipt master by receipt id = " + idreceipt, e1);
			}
			try {req.setAttribute("services", MySQLServiceDAO.getReceiptServices(idreceipt));
			req.getRequestDispatcher("view/admin/receiptsInfo.jsp").forward(req, resp);
		} catch (DAOException e) {
			logger.error("Error getting receipt services by receipt id = " + idreceipt, e);
		}
	}

}
