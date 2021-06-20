package servlets.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.CommentDAO;
import database.SQLConstants;
import exceptions.DAOException;
import servlets.client.ClientServlet;

/**
 * The DeleteCommentServlet implements the functionality of comments deleting
 * for admin. page
 * 
 * @author Viktor
 *
 */
@WebServlet("/delete-comment")
public class DeleteCommentServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger(DeleteCommentServlet.class);

	/**
	 * This method provides deleting the comment by specified id from the request.
	 * If non-authorized administrator tries to access this page, he/she will be
	 * redirected to the login page.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect("/FinalProject/login");
		} else {
			long id = Long.parseLong(req.getParameter(SQLConstants.ID_COMMENT));
			try {
				CommentDAO.deleteCommentById(id);
			} catch (DAOException e) {
				logger.error("Error deleting comment by id " + id, e);
			}
			try {
				req.getSession().setAttribute("adminComments", CommentDAO.getAllComments());
				resp.sendRedirect("/FinalProject/admin-comments");
			} catch (DAOException e) {
				logger.error("Error getting all the comments after deleting one!", e);
			}
		}
		
	}
}
