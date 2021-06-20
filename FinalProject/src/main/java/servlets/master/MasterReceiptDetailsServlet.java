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

import dao.ReceiptDAO;
import dao.ServiceDAO;
import dao.StatusDAO;
import database.SQLConstants;
import entities.Receipt;
import entities.Service;
import exceptions.DAOException;

/**
 * The MasterReceiptDetailsServlet class implements the functionality of
 * providing master role with the details of the particular receipt
 * 
 * @author Viktor
 *
 */
@WebServlet("/master-receipt-details")
public class MasterReceiptDetailsServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(MasterReceiptDetailsServlet.class);

	/**
	 * This method provides the details of the chosen receipt to the master by
	 * getting the receipt id parameter from the request. If there is no such
	 * paramenter in the request, the session value will be used
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idAsString = req.getParameter(SQLConstants.ID_RECEIPT);
		Long id;
		if (idAsString == null) {
			Receipt r = (Receipt) req.getSession().getAttribute("masterReceiptDetails");
			id = r.getId();
		} else {
			id = Long.parseLong(idAsString);
		}
		List<Service> receiptServices = null;
		try {
			receiptServices = ServiceDAO.getReceiptServices(id);
		} catch (DAOException e1) {
			logger.error("Error gettins services of the receipt  with id = " + id + "!", e1);
		}
		Receipt masterReceipt = null;
		try {
			masterReceipt = ReceiptDAO.getReceiptById(id);
		} catch (DAOException e1) {
			logger.error("Error gettins receipt with id = " + id + "!", e1);
		}
		try {
			req.getSession().setAttribute("masterReceiptDetails", masterReceipt);
			req.getSession().setAttribute("masterReceiptServices", receiptServices);
			req.getSession().setAttribute("masterDetailsIdreceipt", id);
			req.getSession().setAttribute("masterReceiptStatus", StatusDAO.getReceiptStatusAsString(masterReceipt));
			req.getRequestDispatcher("/view/master/master-receipt-details.jsp").forward(req, resp);
		} catch (DAOException e) {
			logger.error("Error gettins receipt status as string   with receipt id = " + id + "!", e);
		}
	}
}
