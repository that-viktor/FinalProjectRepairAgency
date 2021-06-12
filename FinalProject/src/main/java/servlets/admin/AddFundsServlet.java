package servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import database.SQLConstants;
import entities.User;
import exceptions.AccountException;

@WebServlet("/add-funds")
public class AddFundsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double sum = Double.parseDouble(req.getParameter(SQLConstants.SUM_TO_INCREASE_BALANCE));
		User client = (User) req.getSession().getAttribute("accountClient");
		try {
			AccountDAO.topUpBalance(client, sum);
		} catch (AccountException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Account id is " + client.getAccountId());
			req.getSession().setAttribute("account", AccountDAO.getAccountById(client.getAccountId()));
		} catch (AccountException e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/FinalProject/account-info");
	}
}
