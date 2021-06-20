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
<h1 class="page_header">Users' comments</h1>
	<c:forEach items="${adminComments}" var="adminComment">
		<comment:admin-comment comment="${adminComment}" />
		<form action="/FinalProject/delete-comment">
			<button name="idcomment" value="${adminComment.commentId}" class="button">Delete</button>
		</form>
		<hr>
		<br>
	</c:forEach>
	<form action="/FinalProject/admin" class="page_header">
		<button class="button">Back</button>
	</form>
</body>
</html>