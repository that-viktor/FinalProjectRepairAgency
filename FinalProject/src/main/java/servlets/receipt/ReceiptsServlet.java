package servlets.receipt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReceiptDAO;
import dao.StatusDAO;
import dao.UserDAO;
import database.SQLConstants;
import exceptions.ReceiptException;
import exceptions.StatusException;
import exceptions.UserException;

@WebServlet("/receipts")
public class ReceiptsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("statuses", StatusDAO.getAllStatuses());
			req.setAttribute("receipts", ReceiptDAO.getAllReceipts());
			req.setAttribute("masters", UserDAO.getUsersByRole(SQLConstants.MASTER_ROLE_ID));
			req.getRequestDispatcher("view/admin/receipts.jsp").forward(req, resp);
		} catch (ReceiptException e) {
			e.printStackTrace();
		} catch (StatusException e) {
			e.printStackTrace();
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
}
