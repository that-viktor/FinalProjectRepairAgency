<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="comment" uri="myURI"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comments</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title">Electronic
					repair agency website</a>
			</div>
			<a href="/FinalProject/receipts" class="a">View Receipts</a> <a
				href="/FinalProject/clients-list" class="a">Add funds to user</a> <a
				href="/FinalProject/admin-comments" class="a">Check the comments</a>
		</div>
	</div>
	<br>
	<h1 class="page_header">Users' comments</h1>
	<br>
	<div class="content">
		<div class="wrapper">
			<c:forEach items="${adminComments}" var="adminComment">
				<comment:admin-comment comment="${adminComment}" />
				<br>
				<form action="/FinalProject/delete-comment">
					<button name="idcomment" value="${adminComment.commentId}"
						class="cancel_button">Delete</button>
				</form>
				<br>
				<hr>
				<br>
			</c:forEach>
		</div>


	</div>

	<form action="/FinalProject/admin" class="page_header">
		<button class="ref_button">Back</button>
	</form>
</body>
</html>