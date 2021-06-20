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
import entities.Service;

@WebServlet("/remove-service")
public class RemoveSeviceServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long serviceId = Long.parseLong(req.getParameter("idservice"));
		List<ReceiptService> receiptServices = (List<ReceiptService>)req.getSession().getAttribute("receiptServices");
		List<Service> services = (List<Service>) req.getSession().getAttribute("services");
		removeServiceFromLists(serviceId, receiptServices, services);
		req.getSession().setAttribute("services", services);
		req.getSession().setAttribute("receiptServices", receiptServices);
		resp.sendRedirect("/FinalProject/add-receipt");
	}

	private void removeServiceFromLists(long serviceId, List<ReceiptService> receiptServices, List<Service> services) {
		for(ReceiptService rs : receiptServices) {
			if (rs.getServiceId() == serviceId) {
				receiptServices.remove(rs);
			}
		}
		for (Service s : services) {
			if (s.getId() == serviceId) {
				services.remove(s);
			}
		}
	}
}
