package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.DBManager;
import database.SQLConstants;
import database.SQLQueries;
import entities.Comment;
import entities.User;
import exceptions.DAOException;
/**
 * CommentDAO provides API for manipulating Comment objects from DB
 * @author Viktor
 *
 */
public class CommentDAO {
	private static final Logger logger = LogManager.getLogger(CommentDAO.class);
	private static DBManager manager;

	/**
	 * Retrieves all the comments from DB
	 * @return List of comments
	 * @throws DAOException
	 */
	public static List<Comment> getAllComments() throws DAOException {
		manager = DBManager.getInstance();
		List<Comment> comments = new ArrayList<>();
		try (Connection connection = manager.getConnection();
				Statement smt = connection.createStatement();
				ResultSet rs = smt.executeQuery(SQLQueries.GET_ALL_COMMENTS)) {
			while (rs.next()) {
				comments.add(mapComment(rs));
			}
		} catch (SQLException e) {
			logger.error("Error getting all the comments", e);
			throw new DAOException("Error getting all the comments!");
		}
		return comments;
	}

	/**
	 * Creates comment with specified params
	 * @param u
	 * @param commentText
	 * @throws DAOException
	 */
	public static void createComment(User u, String commentText) throws DAOException {
		manager = DBManager.getInstance();
		Timestamp date = new Timestamp(new Date().getTime());
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.CREATE_COMMENT)) {
			pst.setLong(1, u.getId());
			pst.setString(2, commentText);
			pst.setTimestamp(3, date, DBManager.getCalendar());
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error inserting comment " + commentText + " left by user " + u, e);
			throw new DAOException("Error inserting comment " + commentText + " left by user " + u);
		}
	}
	
	/**
	 * Deletes the comment with specified id
	 * @param commentId
	 * @throws DAOException
	 */
	public static void deleteCommentById(long commentId) throws DAOException {
		manager = DBManager.getInstance();
		try (Connection connection = manager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQLQueries.DELETE_COMMENT_BY_ID)) {
			pst.setLong(1, commentId);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error deliting comment by id =" + commentId, e);
			throw new DAOException("Error deliting comment by id =" + commentId);
		}
	}

	private static Comment mapComment(ResultSet rs) throws SQLException {
		Comment c = new Comment();
		c.setCommentId(rs.getLong(SQLConstants.ID_COMMENT));
		c.setUserId(rs.getLong(SQLConstants.ID_USER));
		c.setCommentText(rs.getString(SQLConstants.COMMENT_TEXT));
		c.setCommentDate(rs.getTimestamp(SQLConstants.COMMENT_DATE,DBManager.getCalendar()));
		return c;
	}
}
