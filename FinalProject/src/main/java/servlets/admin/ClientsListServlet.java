package servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.MySQLUserDAO;
import database.SQLConstants;
import exceptions.DAOException;

/**
 * ClientsListServlet provides access to the clients list
 * 
 * @author Viktor
 *
 */
@WebServlet("/clients-list")
public class ClientsListServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ClientsListServlet.class);

	/**
	 * This method retrieves list of all clients and sets it to the request. If
	 * non-authorized administrator tries to access this page, he/she will be
	 * redirected to the login page.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			try {
				req.setAttribute("clients", MySQLUserDAO.getUsersByRole(SQLConstants.CLIENT_ROLE_ID));
				req.getRequestDispatcher("view/client/clients-list.jsp").forward(req, resp);
			} catch (DAOException e) {
				logger.error("Error getting users by role!", e);
			}
		}

	}
}
