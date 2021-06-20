package servlets.receipt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ServiceDAO;
import entities.ReceiptService;
import exceptions.DAOException;

@WebServlet("/add-service")
public class AddServiceToReceiptServlet extends HttpServlet {
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
				servicePrice = ServiceDAO.getServicePriceById(serviceId);
				service.setServicePrice(servicePrice);
				receiptServices.add(service);
				selectedServices.add(serviceId);
				req.getSession().setAttribute("receiptServices", receiptServices);
				req.getSession().setAttribute("selectedServices", selectedServices);
				req.getSession().setAttribute("servicesCount", receiptServices.size());
				System.out.println(receiptServices);
				System.out.println(selectedServices);
				resp.sendRedirect("/FinalProject/client");
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
