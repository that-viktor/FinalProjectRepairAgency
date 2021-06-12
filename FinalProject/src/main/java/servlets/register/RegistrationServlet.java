package servlets.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import database.SQLConstants;
import entities.User;
import exceptions.AccountException;
import exceptions.UserException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User u = UserDAO.getUserByLogin(req.getParameter(SQLConstants.LOGIN));
			if (u == null) {
				u = new User();
				u.setLogin(req.getParameter(SQLConstants.LOGIN));
				u.setPassword(req.getParameter(SQLConstants.PASSWORD));
				u.setFirstName(req.getParameter(SQLConstants.FIRST_NAME));
				u.setSurname(req.getParameter(SQLConstants.SURNAME));
				u.setLastName(req.getParameter(SQLConstants.LAST_NAME));
				u.setRoleId(SQLConstants.CLIENT_ROLE_ID);
				u.setEmail(req.getParameter(SQLConstants.EMAIL));
				u.setPhoneNum(req.getParameter(SQLConstants.PHONE_NUM));
				UserDAO.insertUser(u);
				req.getSession().setAttribute("client", u);
				resp.sendRedirect("view/client/client.jsp");
			}
		} catch (UserException e) {
			e.printStackTrace();
		} catch (AccountException e) {
			e.printStackTrace();
		}
		
	}
}
