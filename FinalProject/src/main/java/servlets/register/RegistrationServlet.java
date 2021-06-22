package servlets.register;

import java.io.IOException;

import javax.security.auth.login.AccountException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cryptho.SimplePasswordEncoder;
import dao.MySQLAccountDAO;
import dao.MySQLUserDAO;
import database.SQLConstants;
import entities.User;
import exceptions.DAOException;
import servlets.service.ServiceSumServlet;

/**
 * RegistrationServlet class processes the data entered to the registration form
 * and tries to register a new account
 * 
 * @author Viktor
 *
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);

	/**
	 * This method tries to register a new user with the data from the request. If
	 * success, new user is added to the session. Otherwise error page is displayed
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean notExist = true;
		try {
			User loginTest = MySQLUserDAO.getUserByLogin(req.getParameter(SQLConstants.LOGIN));
			if (loginTest != null) {
				notExist = false;
				req.setAttribute("error_message", "User with this login already exists!");
			}
			User phoneNumTest = MySQLUserDAO.getUserByPhoneNum(SQLConstants.PHONE_NUM);
			if (phoneNumTest != null) {
				notExist = false;
				req.setAttribute("error_message", "User with this phone number already exists!");
			}
			
			User emailTest = MySQLUserDAO.getUserByEmail(SQLConstants.EMAIL);
			if (emailTest != null) {
				notExist = false;
				req.setAttribute("error_message", "User with this email already exists!");
			}
			if (notExist == true) {
				User u = new User();
				u.setLogin(req.getParameter(SQLConstants.LOGIN));
				u.setPassword(SimplePasswordEncoder.doEncoding(req.getParameter(SQLConstants.PASSWORD)));
				u.setFirstName(req.getParameter(SQLConstants.FIRST_NAME));
				u.setSurname(req.getParameter(SQLConstants.SURNAME));
				u.setLastName(req.getParameter(SQLConstants.LAST_NAME));
				u.setRoleId(SQLConstants.CLIENT_ROLE_ID);
				u.setEmail(req.getParameter(SQLConstants.EMAIL));
				u.setPhoneNum(req.getParameter(SQLConstants.PHONE_NUM));
				MySQLUserDAO.insertUser(u);
				req.getSession().setAttribute("client", MySQLUserDAO.getUserByLogin(u.getLogin()));
				resp.sendRedirect("/FinalProject/client");
			} else {
				req.getRequestDispatcher("view/error/error.jsp").forward(req, resp);
			}
		} catch (DAOException e) {
			logger.error("Error registering a new user!");
		}

	}
}
