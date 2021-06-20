package servlets.client;

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
import entities.User;
import exceptions.DAOException;
import servlets.admin.ClientsListServlet;

/**
 * The AddClientCommentServlet processes the data, needed to insert the comment
 * into DB, creates it and inserts
 * 
 * @author Viktor
 *
 */

@WebServlet("/add-comment")
public class AddClientCommentServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(AddClientCommentServlet.class);

	/**
	 * This method receives comment text and client parameters. Then it inserts a
	 * record to the comment entity in the DB. After that it updates all the
	 * comments in the session and their count. If non-authorized client tries to
	 * access this page, he/she will be redirected to the login page.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("client") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			String commentText = req.getParameter("commentText");
			User client = (User) req.getSession().getAttribute("client");
			try {
				CommentDAO.createComment(client, commentText);
			} catch (DAOException e1) {
				logger.error("Error while creating a comment!", e1);
			}
			try {
				List<Comment> comments = CommentDAO.getAllComments();
				req.getSession().setAttribute("comments", comments);
				req.getSession().setAttribute("commentsCount", comments.size());
				resp.sendRedirect("/FinalProject/client-comments");
			} catch (DAOException e) {
				logger.error("Error getting all the comments!", e);
			}
		}

	}

}
