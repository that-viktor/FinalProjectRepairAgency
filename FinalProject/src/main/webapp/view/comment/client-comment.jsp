<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="comments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Comments</title>
</head>
<body>
	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title"><fmt:message
						key="title" /></a>
			</div>
			<a href="/FinalProject/client" class="a"><fmt:message
						key="services" /></a>
			 <a	href="/FinalProject/client-profile" class="a"><fmt:message
					key="profile" /></a> 
			 <a href="/FinalProject/client-comments" class="a"><fmt:message
					key="comments" /></a>
			<fmt:message key="balance" /> ${account.balance} UAH
		</div>
	</div>
	<h1 class="page_header"><fmt:message key="comments_title" /></h1>
	<div class="content">
		<comments:commentTag />

		<br>

		<form action="/FinalProject/add-comment" class="center_form"
			method="POST">
			<textarea rows="15" cols="70" required name="commentText"
				placeholder="<fmt:message
					key="comment_placeholder" />"></textarea>
			<button class="ref_button"><fmt:message
					key="comments" /></button>
		</form>

		<form action="/FinalProject/client" class="page_header">
			<button class="ref_button"><fmt:message
					key="back" /></button>
		</form>

	</div>

</body>
</html>