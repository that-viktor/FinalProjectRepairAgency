<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="comments" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Comments</title>
</head>
<body>
<h1 class="page_header">Comments</h1>
	<div class="content">
	<comments:commentTag/>
	<br>
	
	<form action="/FinalProject/add-comment" class="center_header" method="POST">
		<textarea rows="15" cols="70" required name="commentText" placeholder="Type your comment here"></textarea>
		<button class="center_button">Leave a comment</button>
	</form>
	
	<form action="/FinalProject/client" class="page_header">
		<button class="button">Back</button>
	</form>
	
	</div>
	
</body>
</html>