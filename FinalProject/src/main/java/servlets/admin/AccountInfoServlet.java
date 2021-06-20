package servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.AccountDAO;
import dao.UserDAO;
import database.DBManager;
import database.SQLConstants;
import entities.Account;
import entities.User;
import exceptions.DAOException;

/**
 * AccountInfoSevlet gives the information about user account and profile
 * details
 * 
 * @author Viktor
 *
 */
@WebServlet("/account-info")
public class AccountInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(AccountInfoServlet.class);
	
	/**
	 * This method receives a user id by getting it from request or session scope.
	 * By this id method retrieves user from DB. With this user object method
	 * retrieves user's account and puts it to the session as well as the client of
	 * this account. If non-authorized administrator tries to access this page,
	 * he/she will be redirected to the login page.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			String idToParse = req.getParameter(SQLConstants.ID_USER);
			Long id = null;
			User client = null;
			if (idToParse != null) {
				id = Long.parseLong(idToParse);
				try {
					client = UserDAO.getUserById(id);
				} catch (DAOException e) {
					logger.error("Error getting client by id " + id, e);
				}
			} else {
				client = (User) req.getSession().getAttribute("accountClient");
				id = client.getId();
			}
			Account account = null;
			try {
				account = AccountDAO.getAccountById(client.getAccountId());
			} catch (DAOException e) {
				logger.error("Error getting account by id " + client.getAccountId(), e);
			}
			try {
				req.getSession().setAttribute("accountClient", UserDAO.getUserById(id));
				req.getSession().setAttribute("account", account);
				req.getRequestDispatcher("view/client/client-info.jsp").forward(req, resp);
			} catch (DAOException e) {
				logger.error("Error getting client by id " + id, e);
			}
		}

	}
}
