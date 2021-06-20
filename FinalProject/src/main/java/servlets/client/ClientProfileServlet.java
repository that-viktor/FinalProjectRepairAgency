package servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClientProfileServlet provides access to the client's main profile with some personal info.
 * 
 * @author Viktor
 *
 */
@WebServlet("/client-profile")
public class ClientProfileServlet extends HttpServlet {
	/**
	 * This method provides access to the client's JSP page. If non-authorized
	 * client tries to access this page, he/she will be redirected to the login
	 * page.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("client") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			req.getRequestDispatcher("view/client/client-profile.jsp").forward(req, resp);
		}
	}
}
