<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${commentsCount == 0}">
	<h1>No comments for now</h1>
</c:if>
<c:if test="${commentsCount != 0}">
<div class="comments">
		<c:forEach items="${comments}" var="comment">
			<div class="comment_block">
				<h3>Anonymous</h3>
				<p>${comment.commentText}</p>
				<p>
					<small><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
							type="date" value="${comment.commentDate}" /> </small>
				</p>
				<hr>
				<br>
			</div>

		</c:forEach>
	</div>
</c:if>
