package servlets.receipt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.protobuf.ServiceException;

import dao.ReceiptDAO;
import dao.ServiceDAO;
import database.SQLConstants;
import exceptions.DAOException;

@WebServlet("/details")
public class ReceiptDetailsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		try {
			req.setAttribute("services", ServiceDAO.getReceiptServices(id));
			req.getSession().setAttribute("receipt_for_master", ReceiptDAO.getReceiptById(id));
			req.setAttribute("receipt", ReceiptDAO.getReceiptById(id));
			req.setAttribute("client", ReceiptDAO.getReceiptClient(id));
			req.setAttribute("admin", ReceiptDAO.getReceiptAdmin(id));
			req.setAttribute("master", ReceiptDAO.getReceiptMaster(id));
			req.getRequestDispatcher("view/admin/receiptsInfo.jsp").forward(req, resp);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
