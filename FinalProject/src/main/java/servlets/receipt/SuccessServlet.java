package servlets.receipt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SuccessServlet provides success page after forming a receipt by client
 * 
 * @author Viktor
 *
 */
@WebServlet("/success")
public class SuccessServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("view/client/success.jsp").forward(req, resp);
	}
}
