<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" />
<c:if test="${commentsCount == 0}">
	<h1>No comments for now</h1>
</c:if>
<c:if test="${commentsCount != 0}">
	<c:forEach items="${comments}" var="comment">
		<div class="comment_wrapper">
		<div class="center_content">
				<h3><fmt:message key="anonym" /></h3><hr>
				<p>${comment.commentText}</p>
				<p>
					<small><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
							type="date" value="${comment.commentDate}" /> </small>
				</p>
				<br>
			</div>
			
		</div>
	</c:forEach>
</c:if>
