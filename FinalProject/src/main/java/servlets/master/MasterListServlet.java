package servlets.master;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import database.SQLConstants;
import exceptions.UserException;

@WebServlet("/masterList")
public class MasterListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long idreceipt = Long.parseLong(req.getParameter(SQLConstants.ID_RECEIPT));
		System.out.println(req.getParameter(SQLConstants.ID_RECEIPT));
		try {
			req.setAttribute("masters", UserDAO.getUsersByRole(SQLConstants.MASTER_ROLE_ID));
			req.setAttribute("idreceipt", idreceipt);
			req.getRequestDispatcher("view/master/masters.jsp").forward(req, resp);
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
}
