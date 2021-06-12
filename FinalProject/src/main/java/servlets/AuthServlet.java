package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReceiptDAO;
import dao.UserDAO;
import database.SQLConstants;
import entities.User;
import exceptions.ReceiptException;
import exceptions.UserException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter(SQLConstants.LOGIN);
		String pass = req.getParameter(SQLConstants.PASSWORD);
		try {
			User u = UserDAO.getUserByLogin(login);
			System.out.println(u);
			if (u != null) {
				redirectUser(req, resp, pass, u);
			} else {
				resp.sendRedirect("/FinalProject/login");
			}
		} catch (UserException e) {
			e.printStackTrace();
		}
	}

	private void redirectUser(HttpServletRequest req, HttpServletResponse resp, String pass, User u)
			throws IOException, ServletException {
		if (u.getPassword().equals(pass)) {
			checkUserRoleAndRedirect(req, resp, u);
		} else {
			req.setAttribute("error_message", "Wrong password!");
			req.getRequestDispatcher("view/error/error.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute("user");
		System.out.println(u);
		if (u == null) {
			resp.sendRedirect("view/login.jsp");
		} else {
			checkUserRoleAndRedirect(req, resp, u);
		}
	}

	private void checkUserRoleAndRedirect(HttpServletRequest req, HttpServletResponse resp, User u) throws IOException {
		if (u.getRoleId() == SQLConstants.ADMIN_ROLE_ID) {
			req.getSession().setAttribute("admin", u);
			resp.sendRedirect("/FinalProject/admin");
		}
		if (u.getRoleId() == SQLConstants.CLIENT_ROLE_ID) {
			req.getSession().setAttribute("client", u);
			resp.sendRedirect("/FinalProject/client");
		}
		if (u.getRoleId() == SQLConstants.MASTER_ROLE_ID) {
			req.getSession().setAttribute("master", u);
			resp.sendRedirect("/FinalProject/master");
		}
	}
}
