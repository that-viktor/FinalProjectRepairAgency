package servlets.receipt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReceiptDAO;
import database.SQLConstants;
import exceptions.ReceiptException;

@WebServlet("/remove")
public class RemoveReceiptServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long receiptId = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		try {
			ReceiptDAO.updateReceiptStatusByReceiptId(receiptId, SQLConstants.STATUS_CANCELLED_ID);
			resp.sendRedirect("/FinalProject/receipts");
		} catch (ReceiptException e) {
			e.printStackTrace();
		}
		
	}
}
