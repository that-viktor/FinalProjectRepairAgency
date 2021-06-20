package tags;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.UserDAO;
import entities.Comment;
import entities.User;
import exceptions.DAOException;
import servlets.filters.DateFilterServlet;

/**
 * AdminCommentTag class represents the custom JSTL tag, which functionality is
 * to print out to the JSP page the content of the comment, passed as an
 * argument to the tag
 * 
 * @author Viktor
 *
 */
public class AdminCommentTag extends TagSupport {
	private static final Logger logger = LogManager.getLogger(AdminCommentTag.class);
	private Comment comment;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	/**
	 * This method defines the functionality of the start tag 'comment' and prints
	 * out the content of the comment argument, passed as an attribute on the JSP
	 * page
	 */
	@Override
	public int doStartTag() throws JspException {
		try {
			String date = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(comment.getCommentDate());
			User commentAuthor = UserDAO.getUserById(comment.getUserId());
			pageContext.getOut().append("<b>").append(commentAuthor.getFirstName()).append(" ")
					.append(commentAuthor.getLastName()).append("</b><br>").append(comment.getCommentText())
					.append("<br>").append(date).append("<br>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			logger.error("Error getting user by id = " + comment.getUserId() + "!", e);
		}
		return SKIP_BODY;
	}
}
