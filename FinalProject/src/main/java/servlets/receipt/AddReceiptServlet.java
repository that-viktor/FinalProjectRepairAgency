package servlets.receipt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.MySQLServiceDAO;
import entities.ReceiptService;
import entities.Service;
import exceptions.DAOException;
import servlets.master.SetMasterServlet;

/**
 * The AddReceiptServlet provides method for displaying all receipt info as a
 * form. The submit will be provided in another servlet
 * 
 * @author Viktor
 *
 */
@WebServlet("/add-receipt")
public class AddReceiptServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(AddReceiptServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("client") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			List<ReceiptService> receiptServices = (List<ReceiptService>) req.getSession()
					.getAttribute("receiptServices");
			List<Service> services = new ArrayList<>();
			for (int i = 0; i < receiptServices.size(); i++) {
				try {
					services.add(MySQLServiceDAO.getServiceById(receiptServices.get(i).getServiceId()));
				} catch (DAOException e) {
					logger.error("Error adding services to the list of services", e);
				}
			}
			req.getSession().setAttribute("services", services);
			req.getRequestDispatcher("view/client/submit.jsp").forward(req, resp);
		}
	}

}
