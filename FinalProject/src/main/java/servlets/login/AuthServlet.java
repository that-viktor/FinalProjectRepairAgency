package servlets.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cryptho.SimplePasswordEncoder;
import dao.UserDAO;
import database.SQLConstants;
import entities.User;
import exceptions.DAOException;
import servlets.filters.StatusFilterServlet;

/**
 * AuthServlet class processes the data from the login form and executes the
 * authentication process to this data
 * 
 * @author Viktor
 *
 */
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(StatusFilterServlet.class);

	/**
	 * This method tries to retrieve a user from DB by the data, passed in the
	 * request. If there is such a user, it will be authorized by his role in the
	 * system. If there is no such user, an error page will be given.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter(SQLConstants.LOGIN);
		String pass = req.getParameter(SQLConstants.PASSWORD);
		try {
			User u = UserDAO.getUserByLogin(login);
			if (u != null) {
				redirectUser(req, resp, pass, u);
			} else {
				resp.sendRedirect("/FinalProject/login");
			}
		} catch (DAOException e) {
			logger.error("Error getting user by login " + login + "!", e);
		}
	}

	private void redirectUser(HttpServletRequest req, HttpServletResponse resp, String pass, User u)
			throws IOException, ServletException {
		if (SimplePasswordEncoder.doEncoding(pass).equals(u.getPassword())) {
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
