package servlets.receipt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReceiptDAO;
import entities.ReceiptService;
import entities.User;
import exceptions.DAOException;

@WebServlet("/submit")
public class SubmitReceiptServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ReceiptService> servicesAsList = (List<ReceiptService>)req.getSession().getAttribute("receiptServices");
		ReceiptService[] receiptServices = castListToArray(servicesAsList);
		User client = (User) req.getSession().getAttribute("client");
		try {
			ReceiptDAO.addReceipt(client, receiptServices);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		req.getSession().removeAttribute("receiptService");
		req.getSession().removeAttribute("services");
		req.getSession().removeAttribute("selectedServices");
		req.getSession().removeAttribute("servicesCount");
		resp.sendRedirect("/FinalProject/success");
	}
	
	private ReceiptService[] castListToArray(List<ReceiptService> servicesAsList) {
		ReceiptService[] services = new ReceiptService[servicesAsList.size()];
		for (int i = 0; i < services.length; i++) {
			services[i] = servicesAsList.get(i);
		}
		return services;
	}
}
