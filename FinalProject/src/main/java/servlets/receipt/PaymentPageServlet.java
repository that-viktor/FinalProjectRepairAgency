package servlets.receipt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import dao.ReceiptDAO;
import dao.ServiceDAO;
import database.SQLConstants;
import entities.Receipt;
import entities.User;
import exceptions.DAOException;

@WebServlet("/pay")
public class PaymentPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			req.setAttribute("services", ServiceDAO.getReceiptServices(receiptId));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("view/client/pay.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long receiptId = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		try {
			Receipt receiptToPay = ReceiptDAO.getReceiptById(receiptId);
		} catch (DAOException e) {
			e.printStackTrace();
		} 
		User client = (User) req.getSession().getAttribute("client");
		Receipt receipt = (Receipt) req.getSession().getAttribute("receiptToPay");
		
		try {
			AccountDAO.debitTheAccount(client, receipt.getTotalSum());
			ReceiptDAO.updateReceiptStatusByReceiptId(receiptId,SQLConstants.STATUS_PAYED_ID);
			List<Receipt> clientReceipts = (List<Receipt>) req.getSession().getAttribute("clientReceipts");
			for (Receipt r : clientReceipts) {
				if (r.getId() == receiptId) {
					r.setStatus(SQLConstants.STATUS_PAYED_ID);
				}
			}
			req.getSession().setAttribute("clientReceipts", clientReceipts);
			resp.sendRedirect("/FinalProject/client-profile");
		} catch (DAOException e) {
			req.setAttribute("error_message", "Insufficient funds on your account! Please, contact to admin to top up balance!");
			req.getRequestDispatcher("view/error/error.jsp").forward(req, resp);
		}
	}
}
