package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * This is MainPageServlet that represents the main page of the application and sets the default locale as EN
 * @author Viktor
 *
 */
@WebServlet("/main")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String locale = (String) req.getSession().getAttribute("lang");
		if (locale == null) {
			req.getSession().setAttribute("lang", "en");
		}
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
