package servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.CommentDAO;
import entities.Comment;
import exceptions.DAOException;

/**
 * AdminCommentsServlet provides access to comments page from the side of an
 * administrator
 * 
 * @author Viktor
 *
 */
@WebServlet("/admin-comments")
public class AdminCommentServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(AdminCommentServlet.class);

	/**
	 * This method uses retrieves all the user's comments from the DB and put's them
	 * to the session as a list. If non-authorized administrator tries to access
	 * this page, he/she will be redirected to the login page.
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			try {
				List<Comment> comments = CommentDAO.getAllComments();
				req.getSession().setAttribute("adminComments", comments);
			} catch (DAOException e) {
				logger.error("Error getting the comments page from the admin side!", e);
			}
			req.getRequestDispatcher("view/comment/admin-comments.jsp").forward(req, resp);
		}
	}
}
