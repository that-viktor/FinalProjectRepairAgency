package entities;

import java.sql.Timestamp;

/**
 * Comment class is a class that describes the DB entity - Comment
 * 
 * @author Viktor
 *
 */
public class Comment {
	private long commentId;
	private long userId;
	private String commentText;
	private Timestamp commentDate;

	public Comment() {

	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", userId=" + userId + ", commentText=" + commentText + "]";
	}

}
