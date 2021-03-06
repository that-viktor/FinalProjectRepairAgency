package servlets.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.SQLConstants;

/**
 * LogoutServlet provides functionality of disconnection user from session and
 * deleting its object so that re-login needed
 * 
 * @author Viktor
 *
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	/**
	 * This method implements the functionality of disconnecting user and cleaning
	 * session with further redirection to login page
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long idrole = Long.parseLong(req.getParameter("user_role"));
		HttpSession session = req.getSession();
		if (idrole == SQLConstants.ADMIN_ROLE_ID) {
			session.removeAttribute("admin");
		}
		if (idrole == SQLConstants.CLIENT_ROLE_ID) {
			session.removeAttribute("client");
		}
		if (idrole == SQLConstants.MASTER_ROLE_ID) {
			session.removeAttribute("master");
		}
		session.removeAttribute("receiptServices");
		session.removeAttribute("selectedServices");
		session.removeAttribute("servicesCount");
		resp.sendRedirect("/FinalProject/main");
	}
}
