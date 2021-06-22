package servlets.receipt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.MySQLReceiptDAO;
import entities.ReceiptService;
import entities.User;
import exceptions.DAOException;
import servlets.register.RegistrationServlet;

/**
 * SubmitReceiptServlet submits receipt formation by a client and sends it to
 * the DB
 * 
 * @author Viktor
 *
 */
@WebServlet("/submit")
public class SubmitReceiptServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(SubmitReceiptServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ReceiptService> servicesAsList = (List<ReceiptService>) req.getSession().getAttribute("receiptServices");
		ReceiptService[] receiptServices = castListToArray(servicesAsList);
		User client = (User) req.getSession().getAttribute("client");
		try {
			MySQLReceiptDAO.addReceipt(client, receiptServices);
		} catch (DAOException e) {
			logger.error("Error while adding a new receipt with services = " + receiptServices, e);
		}
		req.getSession().removeAttribute("receiptServices");
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
