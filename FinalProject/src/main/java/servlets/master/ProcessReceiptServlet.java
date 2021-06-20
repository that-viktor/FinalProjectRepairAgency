package servlets.master;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ReceiptDAO;
import database.SQLConstants;
import entities.Receipt;
import exceptions.DAOException;

/**
 * The ProcessReceiptServlet class implements the functionality of changing
 * receipt status from "payed" to "processing" and from "processing" to
 * "processed". The next status depends on the previous
 * 
 * @author Viktor
 *
 */
@WebServlet("/process-receipt")
public class ProcessReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ProcessReceiptServlet.class);

	/**
	 * This method executes the status-changing procedure for the particular
	 * receipt. The next status depends on the previous status of the receipt.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("master") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			Receipt receiptToProcess = (Receipt) req.getSession().getAttribute("masterReceiptDetails");
			if (receiptToProcess.getStatus() == SQLConstants.STATUS_PAYED_ID) {
				try {
					ReceiptDAO.updateReceiptStatusByReceiptId(receiptToProcess.getId(),
							SQLConstants.STATUS_PROCESSING_ID);
					receiptToProcess.setStatus(SQLConstants.STATUS_PROCESSING_ID);
				} catch (DAOException e) {
					logger.error("Error updating status " + receiptToProcess.getStatus() + " to " + SQLConstants.STATUS_PROCESSING);
				}
			} else {
				try {
					ReceiptDAO.updateReceiptStatusByReceiptId(receiptToProcess.getId(),
							SQLConstants.STATUS_PROCESSED_ID);
					receiptToProcess.setStatus(SQLConstants.STATUS_PROCESSED_ID);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
			req.getSession().setAttribute("masterReceiptDetails", receiptToProcess);
			resp.sendRedirect("/FinalProject/master-receipt-details");
		}

	}
}
