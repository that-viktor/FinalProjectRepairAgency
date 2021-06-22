package servlets.master;

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
import entities.Receipt;
import entities.User;
import exceptions.DAOException;

/**
 * MasterServlet class provides access to the master's main page
 * 
 * @author Viktor
 *
 */
@WebServlet("/master")
public class MasterServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(MasterServlet.class);

	/**
	 * This method implements the functionality of the displaying master's main page
	 * with all receipts, where he was set. The method displays only receipts with
	 * status "payed" and "processing"
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User master = (User) req.getSession().getAttribute("master");
		if (master == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			try {
				List<Receipt> masterReceipts = MySQLReceiptDAO.getMasterReceipts(master.getId());
				req.getSession().setAttribute("masterReceipts", masterReceipts);
				req.getSession().setAttribute("receiptsCount", masterReceipts.size());
			} catch (DAOException e) {
				logger.error("Error getting all the receipts for the master id = " + master.getId() + "!", e);
			}
			req.getRequestDispatcher("view/master/master.jsp").forward(req, resp);
		}

	}
}
