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
import database.SQLConstants;
import exceptions.DAOException;
/**
 * 
 * @author Viktor
 *
 */
@WebServlet("/remove")
public class RemoveReceiptServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(RemoveReceiptServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long receiptId = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		try {
			MySQLReceiptDAO.updateReceiptStatusByReceiptId(receiptId, SQLConstants.STATUS_CANCELLED_ID);
			resp.sendRedirect("/FinalProject/receipts");
		} catch (DAOException e) {
			logger.error("Error updating receipt id = " + receiptId + " status to " +  SQLConstants.STATUS_CANCELLED_ID, e);
		}

	}
}
