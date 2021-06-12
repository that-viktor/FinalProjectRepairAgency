package servlets.filters;

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

@WebServlet("/sum-filter")
public class ReceiptSumFilterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filter = req.getParameter(SQLConstants.TOTAL_SUM);
		if (filter.equals(SQLConstants.ASC)) {
			try {
				req.setAttribute("receipts", ReceiptDAO.getAllReceiptsOrderedByReceiptSum(false));
			} catch (ReceiptException e) {
				e.printStackTrace();
			}
		}
		if (filter.equals(SQLConstants.DESC)) {
			try {
				req.setAttribute("receipts", ReceiptDAO.getAllReceiptsOrderedByReceiptSum(true));
			} catch (ReceiptException e) {
				e.printStackTrace();
			}
		}
		try {
			req.setAttribute("statuses", StatusDAO.getAllStatuses());
		} catch (StatusException e) {
			e.printStackTrace();
		}
		try {
			req.setAttribute("masters", UserDAO.getUsersByRole(SQLConstants.MASTER_ROLE_ID));
		} catch (UserException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("view/admin/receipts.jsp").forward(req, resp);
	}
}
