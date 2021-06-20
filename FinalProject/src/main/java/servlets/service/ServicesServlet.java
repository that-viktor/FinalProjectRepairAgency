package servlets.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ServiceDAO;
import exceptions.DAOException;

@WebServlet("/services")
public class ServicesServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("services", ServiceDAO.getReceiptServices(40));
			req.getRequestDispatcher("view/services/services.jsp").forward(req, resp);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
