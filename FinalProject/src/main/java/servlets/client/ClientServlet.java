package servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ServiceDAO;
import database.SQLConstants;
import exceptions.ServiceException;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer page = (Integer) req.getSession().getAttribute("page");
		if (page == null) {
			page = 0;
		}
		int offset = page * SQLConstants.LIMIT;
		try {
			int[] pages = createPagesArray();
			req.getSession().setAttribute("page", page);
			req.setAttribute("services", ServiceDAO.getServicesLimited(offset));
			req.setAttribute("pages", pages);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("view/client/client.jsp").forward(req, resp);
	}

	private int[] createPagesArray() throws ServiceException {
		int pagesCount = (ServiceDAO.getAllServices().size()/SQLConstants.LIMIT) + 1;
		int[] pages = new int[pagesCount];
		for (int i = 0; i < pages.length; i++) {
			pages[i] = i + 1;
		}
		return pages;
	}
}
