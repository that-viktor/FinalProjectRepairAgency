package servlets.receipt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReceiptDAO;
import dao.ServiceDAO;
import database.SQLConstants;
import entities.Receipt;
import entities.User;
import exceptions.DAOException;

@WebServlet("/approve")
public class ApproveSevlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession();
		User u = (User) s.getAttribute("admin");
		Receipt r = null;
		long idReceipt = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		try {
			r = ReceiptDAO.getReceiptById(idReceipt);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			ReceiptDAO.setAdminForReceipt(u, r);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			req.setAttribute("receipt", ReceiptDAO.getReceiptById(idReceipt));
			req.setAttribute("client", ReceiptDAO.getReceiptClient(r.getId()));
			req.setAttribute("admin", ReceiptDAO.getReceiptAdmin(r.getId()));
			req.setAttribute("master", ReceiptDAO.getReceiptMaster(r.getId()));
			req.setAttribute("services", ServiceDAO.getReceiptServices(idReceipt));
			req.getRequestDispatcher("view/admin/receiptsInfo.jsp").forward(req, resp);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
}
