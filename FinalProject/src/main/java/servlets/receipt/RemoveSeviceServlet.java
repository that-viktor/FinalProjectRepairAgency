package servlets.receipt;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.ReceiptService;
import entities.Service;

/**
 * RemoveSeviceServlet provides functionality of deleting the specified service
 * from the particular receipt
 * 
 * @author Viktor
 *
 */
@WebServlet("/remove-service")
public class RemoveSeviceServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long serviceId = Long.parseLong(req.getParameter("idservice"));
		List<ReceiptService> receiptServices = (List<ReceiptService>) req.getSession().getAttribute("receiptServices");
		List<Long> selectedServices = (List<Long>) req.getSession().getAttribute("selectedServices");
		removeServiceFromLists(serviceId, receiptServices, selectedServices);
		req.getSession().setAttribute("selectedServices", selectedServices);
		req.getSession().setAttribute("receiptServices", receiptServices);
		req.getSession().setAttribute("servicesCount", receiptServices.size());
		resp.sendRedirect("/FinalProject/add-receipt");
	}

	private void removeServiceFromLists(long serviceId, List<ReceiptService> receiptServices, List<Long> services) {
		for (Iterator<Long> i = services.iterator(); i.hasNext();) {
			Long s = i.next();
			if (serviceId == s) {
				i.remove();
			}
		}
		for (Iterator<ReceiptService> i = receiptServices.iterator(); i.hasNext();) {
			ReceiptService rs = i.next();
			if (rs.getServiceId() == serviceId) {
				i.remove();
			}
		}

	}
}
