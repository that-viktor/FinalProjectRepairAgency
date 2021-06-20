package servlets.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.CommentDAO;
import entities.Comment;
import entities.User;
import exceptions.DAOException;

/**
 * ClientCommentsServlet provides access to the comments page as a client. All
 * other user's names will be hidden
 * 
 * @author Viktor
 *
 */
@WebServlet("/client-comments")
public class ClientCommentsServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ClientCommentsServlet.class);

	/**
	 * This method retrieves all the comments from the database and put it to the
	 * session as well as their count. If non-authorized client tries to access this
	 * page, he/she will be redirected to the login page.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("client") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			HttpSession s = req.getSession();
			try {
				List<Comment> comments = CommentDAO.getAllComments();
				s.setAttribute("comments", comments);
				s.setAttribute("commentsCount", comments.size());
				req.getRequestDispatcher("view/comment/client-comment.jsp").forward(req, resp);
			} catch (DAOException e) {
				logger.error("Error getting all the comments from the client side!");
			}
		}

	}
}
