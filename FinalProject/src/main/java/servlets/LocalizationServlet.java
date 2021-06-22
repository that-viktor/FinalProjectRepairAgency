package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/locale")
public class LocalizationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String locale = req.getParameter("lang");
		if (locale.equals("ru")) {
			req.getSession().setAttribute("lang", "ru");
		}
		if (locale.equals("en")) {
			req.getSession().setAttribute("lang", "en");
		}
		resp.sendRedirect("/FinalProject/main");
	}
}
