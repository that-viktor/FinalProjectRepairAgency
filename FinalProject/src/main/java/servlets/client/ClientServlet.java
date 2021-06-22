package servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.MySQLAccountDAO;
import dao.MySQLReceiptDAO;
import dao.MySQLServiceDAO;
import database.SQLConstants;
import entities.User;
import exceptions.DAOException;

/**
 * ClientServlet provides access to the client's main page.
 * 
 * @author Viktor
 *
 */
@WebServlet("/client")
public class ClientServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ClientServlet.class);

	/**
	 * This method gets all the receipts from the DB and displays it using
	 * pagination mechanism. The offset calculates via special function via the
	 * special formula. If non-authorized client tries to access this page, he/she
	 * will be redirected to the login page.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("client") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			String stringPage = req.getParameter("page");
			User client = (User) req.getSession().getAttribute("client");
			Integer page;
			int offset;
			if (stringPage == null) {
				page = 1;
			} else {
				page = Integer.parseInt(stringPage);
			}
			offset = calculateOffset(page);

			int[] pages;
			try {
				pages = createPagesArray();
				req.setAttribute("pages", pages);
			} catch (DAOException e1) {
				logger.error("Error creating pages array!", e1);
			}
			try {
				req.setAttribute("services", MySQLServiceDAO.getServicesLimited(offset));
			} catch (DAOException e1) {
				logger.error("Error getting services limited by offset " + offset, e1);
			}
			try {
				req.getSession().setAttribute("account", MySQLAccountDAO.getAccountById(client.getAccountId()));
			} catch (DAOException e1) {
				logger.error("Error getting account by id " + client.getAccountId(), e1);
			}
			try {
				req.getSession().setAttribute("clientReceipts", MySQLReceiptDAO.getClientReceipts(client.getId()));
			} catch (DAOException e) {
				logger.error("Error getting client " + client + " receipts!", e);
			}
			req.getRequestDispatcher("view/client/client.jsp").forward(req, resp);
		}

	}

	private int calculateOffset(Integer page) {
		int offset;
		offset = page * SQLConstants.LIMIT - SQLConstants.LIMIT;
		return offset;
	}

	private int[] createPagesArray() throws DAOException {
		int pagesCount = (MySQLServiceDAO.getAllServices().size() / SQLConstants.LIMIT) + 1;
		int[] pages = new int[pagesCount];
		for (int i = 0; i < pages.length; i++) {
			pages[i] = i + 1;
		}
		return pages;
	}
}
