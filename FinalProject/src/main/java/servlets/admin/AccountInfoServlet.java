package servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import dao.UserDAO;
import database.SQLConstants;
import entities.Account;
import entities.User;
import exceptions.AccountException;
import exceptions.UserException;

@WebServlet("/account-info")
public class AccountInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idToParse = req.getParameter(SQLConstants.ID_USER);
		Long id = null;
		User client = null;
		if (idToParse != null) {
			id = Long.parseLong(idToParse);
			try {
				client = UserDAO.getUserById(id);
			} catch (UserException e) {
				e.printStackTrace();
			}
		}
		else {
			client = (User) req.getSession().getAttribute("accountClient");
			id = client.getId();
		}
		Account account = null;
		try {
			account = AccountDAO.getAccountById(client.getAccountId());
		} catch (AccountException e) {
			e.printStackTrace();
		}
		try {
			req.getSession().setAttribute("accountClient", UserDAO.getUserById(id));
			req.getSession().setAttribute("account", account);
			req.getRequestDispatcher("view/client/client-info.jsp").forward(req, resp);
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
}
