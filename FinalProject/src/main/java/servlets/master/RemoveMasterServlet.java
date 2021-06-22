package servlets.master;

import java.io.IOException;

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
import exceptions.DAOException;

/**
 * The RemoveMasterServlet class implements functionality of removing the master
 * from the particular receipt
 * 
 * @author Viktor
 *
 */
@WebServlet("/remove-master")
public class RemoveMasterServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(RemoveMasterServlet.class);

	/**
	 * This method deletes the master from the specific receipt. Receipt it is
	 * coming from the request parameter
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		
			try {
				MySQLReceiptDAO.removeMasterByReceiptId(id);
			} catch (DAOException e1) {
				logger.error("Error removing master from receipt id = " + id, e1);
			}
			try {
				req.setAttribute("receipt", MySQLReceiptDAO.getReceiptById(id));
			} catch (DAOException e1) {
				logger.error("Error getting receipt by receipt id = " + id, e1);
			}
			try {
				req.setAttribute("client", MySQLReceiptDAO.getReceiptClient(id));
			} catch (DAOException e1) {
				logger.error("Error getting receipt client by receipt id = " + id, e1);
			}
			try {
				req.setAttribute("admin", MySQLReceiptDAO.getReceiptAdmin(id));
			} catch (DAOException e1) {
				logger.error("Error getting receipt admin by receipt id = " + id, e1);
			}
			try {
				req.setAttribute("master", MySQLReceiptDAO.getReceiptMaster(id));
			} catch (DAOException e1) {
				logger.error("Error getting receipt master by receipt id = " + id, e1);
			}
			try {req.setAttribute("services", MySQLServiceDAO.getReceiptServices(id));
			req.getRequestDispatcher("view/admin/receiptsInfo.jsp").forward(req, resp);
		} catch (DAOException e) {
			logger.error("Error getting receipt services by receipt id = " + id, e);
		}
	}
}
