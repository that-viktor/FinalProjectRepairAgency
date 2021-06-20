package servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AdminServlet provides access to the administrator's main page after
 * authorization
 * 
 * @author Viktor
 *
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	/**
	 * This method forwards you to the admin JSP page. If non-authorized
	 * administrator tries to access this page, he/she will be redirected to the
	 * login page.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			req.getRequestDispatcher("view/admin/admin.jsp").forward(req, resp);
		}
	}

}
