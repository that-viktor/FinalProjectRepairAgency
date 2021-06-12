package servlets.master;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReceiptDAO;
import dao.UserDAO;
import database.SQLConstants;
import entities.Receipt;
import exceptions.ReceiptException;
import exceptions.UserException;

@WebServlet("/setmaster")
public class SetMasterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		Receipt r = (Receipt) s.getAttribute("receipt_for_master");
		long idreceipt = r.getId();
		long idmaster = Long.parseLong(req.getParameter(SQLConstants.ID_MASTER));
		try {
			ReceiptDAO.setMasterForReceipt(UserDAO.getUserById(idmaster), r);
			req.setAttribute("receipt", ReceiptDAO.getReceiptById(idreceipt));
			req.setAttribute("client", ReceiptDAO.getReceiptUser(idreceipt));
			req.setAttribute("admin", ReceiptDAO.getReceiptAdmin(idreceipt));
			req.setAttribute("master", ReceiptDAO.getReceiptMaster(idreceipt));
			req.getRequestDispatcher("view/admin/receiptsInfo.jsp").forward(req, resp);
		} catch (ReceiptException e) {
			e.printStackTrace();
		} catch (UserException e) {
			e.printStackTrace();
		}
	}

}