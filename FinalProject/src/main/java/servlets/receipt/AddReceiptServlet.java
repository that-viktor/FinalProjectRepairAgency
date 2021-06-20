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
import exceptions.DAOException;

@WebServlet("/add-receipt")
public class AddReceiptServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("client") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			List<ReceiptService> receiptServices = (List<ReceiptService>)req.getSession().getAttribute("receiptServices");
			List<Service> services = new ArrayList<>();
			for (int i = 0; i < receiptServices.size(); i++) {
				try {
					services.add(ServiceDAO.getServiceById(receiptServices.get(i).getServiceId()));
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
			req.getSession().setAttribute("services", services);
			req.getRequestDispatcher("view/client/submit.jsp").forward(req, resp);
		}
	}

}
