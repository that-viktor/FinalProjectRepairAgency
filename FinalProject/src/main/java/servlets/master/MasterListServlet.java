package servlets.master;

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
 * MasterListServlet class provides access to a list of all masters stored in
 * the DB
 * 
 * @author Viktor
 *
 */
@WebServlet("/masterList")
public class MasterListServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(MasterListServlet.class);

	/**
	 * This method extracts all the masters from the DB. Receipt id is used to
	 * remember the receipt, that needs to be filled with master
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long idreceipt = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		try {
			req.setAttribute("masters", MySQLUserDAO.getUsersByRole(SQLConstants.MASTER_ROLE_ID));
			req.setAttribute("idreceipt", idreceipt);
			req.getRequestDispatcher("view/master/masters.jsp").forward(req, resp);
		} catch (DAOException e) {
			logger.error("Error getting users by role id = " + SQLConstants.MASTER_ROLE_ID + "!", e);
		}
	}
}
