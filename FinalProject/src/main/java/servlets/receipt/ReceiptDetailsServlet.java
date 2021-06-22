package servlets.receipt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.protobuf.ServiceException;

import dao.MySQLReceiptDAO;
import dao.MySQLServiceDAO;
import database.SQLConstants;
import exceptions.DAOException;
/**
 * ReceiptDetailsServlet class provides access to the detail info of the chosen receipt
 * @author Viktor
 *
 */
@WebServlet("/details")
public class ReceiptDetailsServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ReceiptDetailsServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		try {
			req.setAttribute("services", MySQLServiceDAO.getReceiptServices(id));
			req.getSession().setAttribute("receipt_for_master", MySQLReceiptDAO.getReceiptById(id));
			req.setAttribute("receipt", MySQLReceiptDAO.getReceiptById(id));
			req.setAttribute("client", MySQLReceiptDAO.getReceiptClient(id));
			req.setAttribute("admin", MySQLReceiptDAO.getReceiptAdmin(id));
			req.setAttribute("master", MySQLReceiptDAO.getReceiptMaster(id));
			req.getRequestDispatcher("view/admin/receiptsInfo.jsp").forward(req, resp);
		} catch (DAOException e) {
			logger.error("Error getting receipt details by receipt id = " + id, e);
		}
	}
}
