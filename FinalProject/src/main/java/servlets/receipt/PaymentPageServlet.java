package servlets.receipt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.MySQLAccountDAO;
import dao.MySQLReceiptDAO;
import dao.MySQLServiceDAO;
import database.SQLConstants;
import entities.Account;
import entities.Receipt;
import entities.User;
import exceptions.DAOException;

/**
 * PaymentPageServlet class provides methods for displaying the payment form and
 * processing the payment for the particular receipt
 * 
 * @author Viktor
 *
 */
@WebServlet("/pay")
public class PaymentPageServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(PaymentPageServlet.class);

	/**
	 * This method shows the payment form with all necessary information
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("client") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			long receiptId = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
			List<Receipt> clientReceipts = (List<Receipt>) req.getSession().getAttribute("clientReceipts");
			Receipt receipt = null;
			for (Receipt r : clientReceipts) {
				if (r.getId() == receiptId) {
					receipt = r;
				}
			}
			req.getSession().setAttribute("receiptToPay", receipt);
			try {
				req.setAttribute("services", MySQLServiceDAO.getReceiptServices(receiptId));
			} catch (DAOException e) {
				logger.error("Error getting all the services by receipt id = " + receiptId, e);
			}
			req.getRequestDispatcher("view/client/pay.jsp").forward(req, resp);
		}

	}

	/**
	 * This method executes the payment process
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long receiptId = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		try {
			Receipt receiptToPay = MySQLReceiptDAO.getReceiptById(receiptId);
		} catch (DAOException e) {
			logger.error("Error getting receipt by id = " + receiptId, e);
		}
		User client = (User) req.getSession().getAttribute("client");
		Receipt receipt = (Receipt) req.getSession().getAttribute("receiptToPay");

		try {
			MySQLAccountDAO.debitTheAccount(client, receipt.getTotalSum());
			MySQLReceiptDAO.updateReceiptStatusByReceiptId(receiptId, SQLConstants.STATUS_PAYED_ID);
			List<Receipt> clientReceipts = (List<Receipt>) req.getSession().getAttribute("clientReceipts");
			for (Receipt r : clientReceipts) {
				if (r.getId() == receiptId) {
					r.setStatus(SQLConstants.STATUS_PAYED_ID);
				}
			}
			req.getSession().setAttribute("clientReceipts", clientReceipts);
			Account updateAccount = MySQLAccountDAO.getAccountById(client.getAccountId());
			req.getSession().setAttribute("account", updateAccount);
			resp.sendRedirect("/FinalProject/client-profile");
		} catch (DAOException e) {
			req.setAttribute("error_message",
					"Insufficient funds on your account! Please, contact to admin to top up balance!");
			req.getRequestDispatcher("view/error/error.jsp").forward(req, resp);
			logger.error("Error making a payment for the receipt id =" + receiptId, e);
		}
	}
}
