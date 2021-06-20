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
import database.SQLConstants;
import entities.User;
import exceptions.DAOException;
/**
 * AddFundsSevlet processes client account by adding funds to the specified account
 * @author Viktor
 *
 */
@WebServlet("/add-funds")
public class AddFundsServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(AddFundsServlet.class);
	
	/**
	 * This method receives an amount of money (sum) to be added to the client's
	 * account. It receives an client account from session and increases its balance
	 * on the specified sum. If non-authorized administrator tries to access this
	 * page, he/she will be redirected to the login page.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			double sum = Double.parseDouble(req.getParameter(SQLConstants.SUM_TO_INCREASE_BALANCE));
			User client = (User) req.getSession().getAttribute("accountClient");
			try {
				AccountDAO.topUpBalance(client, sum);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			try {
				req.getSession().setAttribute("account", AccountDAO.getAccountById(client.getAccountId()));
			} catch (DAOException e) {
				req.setAttribute("error_message", "Invalid nubmer format. Sum must be > 0");
				req.getRequestDispatcher("view/error/error.jsp").forward(req, resp);
				e.printStackTrace();
			}
			resp.sendRedirect("/FinalProject/account-info");
		}

	}
}
