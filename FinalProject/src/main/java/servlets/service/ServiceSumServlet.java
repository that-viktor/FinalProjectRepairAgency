package servlets.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReceiptDAO;
import dao.ServiceDAO;
import database.SQLConstants;
import entities.Service;
import exceptions.DAOException;

@WebServlet("/count")
public class ServiceSumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			long id = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
			List<Service> services = ServiceDAO.getReceiptServices(id);
			req.setAttribute("services", services);
			double totalSum = 0;
			for (Service s : ServiceDAO.getReceiptServices(id)) {
				totalSum += s.getPrice(); 
			}
			ReceiptDAO.setReceiptTotalSum(ReceiptDAO.getReceiptById(id), totalSum);
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
