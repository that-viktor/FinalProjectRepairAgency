package servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import database.SQLConstants;
import exceptions.UserException;

@WebServlet("/clients-list")
public class ClientsListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("clients",UserDAO.getUsersByRole(SQLConstants.CLIENT_ROLE_ID));
			req.getRequestDispatcher("view/client/clients-list.jsp").forward(req, resp);
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
}
