package servlets.receipt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.MySQLServiceDAO;
import entities.ReceiptService;
import exceptions.DAOException;

/**
 * AddServiceToReceiptServlet class provides method for adding selected services
 * to the particular receipt
 * 
 * @author Viktor
 *
 */
@WebServlet("/add-service")
public class AddServiceToReceiptServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(AddServiceToReceiptServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<ReceiptService> receiptServices;
		List<Long> selectedServices;
		if (req.getSession().getAttribute("receiptServices") == null) {
			receiptServices = new ArrayList<>();
		} else {
			receiptServices = (List<ReceiptService>) req.getSession().getAttribute("receiptServices");
		}
		if (req.getSession().getAttribute("selectedServices") == null) {
			selectedServices = new ArrayList<>();
		} else {
			selectedServices = (List<Long>) req.getSession().getAttribute("selectedServices");
		}
		try {
			double servicePrice;
			long serviceId = Long.parseLong(req.getParameter("idservice"));
			if (selectedServices.contains(serviceId)) {
				resp.sendRedirect("/FinalProject/client");
			} else {
				ReceiptService service = new ReceiptService();
				service.setServiceId(serviceId);
				servicePrice = MySQLServiceDAO.getServicePriceById(serviceId);
				service.setServicePrice(servicePrice);
				receiptServices.add(service);
				selectedServices.add(serviceId);
				req.getSession().setAttribute("receiptServices", receiptServices);
				req.getSession().setAttribute("selectedServices", selectedServices);
				req.getSession().setAttribute("servicesCount", receiptServices.size());
				resp.sendRedirect("/FinalProject/client");
			}
		} catch (DAOException e) {
			logger.error("Error adding service to the receipt services = " + receiptServices
					+ " with selected services = " + selectedServices, e);
		}
	}

}
